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
import { SnippetFolderSharp } from '@mui/icons-material';
import { Select, MenuItem, FormControl, InputLabel, OutlinedInput } from '@mui/material';
import Box from '@mui/material/Box';
import { editUserRole } from '../services/managerService';


export function RoleSelection({ fromRoleIdToRoleName, defaultSelection, userID }) {
  const [selectedRole, setSelectedRole] = useState(0);


  useEffect(() => {
    setSelectedRole(defaultSelection);
  }, [defaultSelection]);

  const handleChange = async (event) => {
    const newValue=event.target.value;
    setSelectedRole(newValue);
    editUserRole(userID,event.target.value);

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


    const[roleList,setRoleList]= useState([]);
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
            
            console.log("LISTA DE ROLES");

            console.log(roleList);

            setRoleList(rolesMap);


            setRows(newUsersInfo.map((row) => {
                
                console.log(rolesMap);
                row.roleName = rolesMap.get(row.role);
                console.log(row);
                return row;
            }));

            // setRows(newUsersInfo);
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

export function PermissionsTable() {

    const fromRoleIdToRoleName = {};

    function addRow(id, name, lastName, role) {
        return { id, name, lastName, role };
    }

    const [rows, setRows] = useState([]);


    useEffect(function () {
        const fetchData = async () => {
            const newUsersInfo = await getUsers();
            setRows(newUsersInfo);
            console.log("USUAIROS ");
        };
        fetchData();
    }, []);


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
                                    key={row.name}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                    <TableCell component="th" scope="row">
                                        {row.id}
                                    </TableCell>
                                    <TableCell align="right">{row.name}</TableCell>
                                    <TableCell align="right">{row.lastName}</TableCell>
                                    <TableCell align="right">{row.role}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Container>
        </div>
    );
}




export default function page() {
    return (
        <div>
            <UserTable />
        </div>

    );
}


