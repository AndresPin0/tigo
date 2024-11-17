import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';
import { getUsers, getRoles } from '../services/managerService';
import { useEffect, useState } from 'react';
import { PersonSearch, SnippetFolderSharp } from '@mui/icons-material';
import { Select, MenuItem, FormControl, InputLabel, OutlinedInput,Button,Checkbox,TextField } from '@mui/material';
import Box from '@mui/material/Box';
import ListItemText from '@mui/material/ListItemText';
import { editUserRole,getPermissions,addRole,removeRolePermissions,updateRolePermissions } from '../services/managerService';
import Chip from '@mui/material/Chip';


export function RoleSelection({ fromRoleIdToRoleName, defaultSelection, userID }) {
    const [selectedRole, setSelectedRole] = useState(0);
    useEffect(() => {
        setSelectedRole(defaultSelection);
    }, [defaultSelection]);

    const handleChange = async (event) => {
        const newValue = event.target.value;
        setSelectedRole(newValue);
        editUserRole(userID, event.target.value);
    };
    return (
        <FormControl fullWidth variant="outlined">
            <InputLabel htmlFor="role-select">Role</InputLabel>
            <Select
                value={selectedRole}
                onChange={handleChange}
                input={<OutlinedInput label="Role" id="role-select" />}
            >
                {/* Convertir el Map a un array de elementos */}
                {Array.from(fromRoleIdToRoleName.entries()).map(([id, name]) => (
                    <MenuItem key={id} value={id}>
                        {name}
                    </MenuItem>
                ))}
            </Select>
        </FormControl>
    );
}



export function UserTable() {
    const [roleList, setRoleList] = useState([]);
    function addRow(id, name, lastName, role) {
        return { id, name, lastName, role };
    }

    const [rows, setRows] = useState([]);
    useEffect(function () {
        const fetchData = async () => {
            const newUsersInfo = await getUsers();
            const roles = await getRoles();
            const rolesMap = new Map(roles.map((role) => [role.id, role.name]));
            const givenRoleList = roles;

            setRoleList(rolesMap);

            setRows(newUsersInfo.map((row) => {

                row.roleName = rolesMap.get(row.role);
                return row;
            }));

            console.log("USUAIROS ");
        };
        fetchData();
    }, []);

    const handleChange = (event) => {
        event.target.value;
    };


    return (
        <div>
            <Container maxWidth="md" sx={{ mt: 10 }}>


                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: '50%' }} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>ID </TableCell>
                                <TableCell align="right">Name </TableCell>
                                <TableCell align="right"> Lastname </TableCell>
                                <TableCell align="right">Role </TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rows.map((row) => (
                                <TableRow
                                    key={row.id}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                    <TableCell component="th" scope="row">
                                        {row.id}
                                    </TableCell>
                                    <TableCell align="right">{row.name}</TableCell>
                                    <TableCell align="right">{row.lastName}</TableCell>
                                    <TableCell align="right">

                                        <RoleSelection fromRoleIdToRoleName={roleList} defaultSelection={row.role} userID={row.id} />


                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Container>
        </div>
    );
}



export function MultipleSelectCheckmarks4({ roleName, permissions, rolePermissions }) {
  const [personName, setPersonName] = useState([]);

  useEffect(() => {
    const selectedPermissions = permissions
      .filter(permission => rolePermissions.includes(permission.id))
      .map(permission => permission.name);
    setPersonName(selectedPermissions);
  }, [permissions, rolePermissions]);

  const handleChange = async (event) => {
    const {
      target: { value },
    } = event;
    const updatedPersonName = typeof value === 'string' ? value.split(',') : value;
    setPersonName(updatedPersonName);

    const data = {
      roleName: roleName,
      ...updatedPersonName.reduce((acc, perm) => {
        acc[perm] = true;
        return acc;
      }, {})
    };

    try {
      const response = await updateRolePermissions(data);
      console.log('Permisos actualizados:', response);
    } catch (error) {
      console.error('Error al actualizar los permisos del rol:', error);
    }
  };
  return (
    <div>
      <FormControl sx={{ m: 1, width: 300 }}>
        <InputLabel id="demo-multiple-checkbox-label">Tag</InputLabel>
        <Select
          labelId="demo-multiple-checkbox-label"
          id="demo-multiple-checkbox"
          multiple
          value={personName}
          onChange={handleChange}
          input={<OutlinedInput label="Tag" />}
          renderValue={(selected) => selected.join(', ')}
        >
          {permissions.map((permission) => (
            <MenuItem key={permission.id} value={permission.name}>
              <Checkbox checked={personName.includes(permission.name)} />
              <ListItemText primary={permission.name} />
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
}




export const RolesPermissionsTable = () => {
    const [roles, setRoles] = useState([]);
    const [permissions,setPermissions]=useState([]);

    useEffect(() => {
        // Fetch roles when the component mounts
        const fetchData = async () => {
            const rolesData = await getRoles();
            const permissionsData =await getPermissions();
            setRoles(rolesData);
            
            setPermissions(permissionsData);
        
        };

        fetchData();
    }, []);  


    return (
        <div>
            <Container maxWidth="md" sx={{ mt: 10 }}>
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 650 }} size="small" aria-label="roles roles table">
                        <TableHead>
                            <TableRow>
                                <TableCell>Rol</TableCell>
                                <TableCell align="left">Assigned Value</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {roles.map((role) => (
                                <TableRow key={role.id}>
                                    <TableCell component="th" scope="row">
                                        {role.name}
                                    </TableCell>
                                    <TableCell align="left">
                                        {<MultipleSelectCheckmarks4 roleName= {role.name} permissions={permissions} rolePermissions={role.permissionsIds}/>}
                                        
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Container>
        </div>
    );
};




export default function page() {
    return (
        <div>
            <RolesPermissionsTable />
            <UserTable/>
        </div>

    );
}


