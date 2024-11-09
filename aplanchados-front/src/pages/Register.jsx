import { TextField } from "@mui/material";

import Stack from '@mui/material/Stack';

import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import { useNavigate } from "react-router-dom";
import { register } from "../services/authService";
import { useState } from "react";
import Modal from "../components/Modal";


function Register() {
    const [inputs, setInputs] = useState({});
    const [modal,setModal]= useState({isOpen:false});

    const navigate = useNavigate();

    async function onSubmitRegister(e) {
        e.preventDefault();
        setModal(prev => ({...prev,title:"Titulo actualizado"}));

        setModal(prev => ({...prev,body:"Cuerpo actualizado"}));
        
        setModal(prev=>({...prev,isOpen:true}) );

        try {
            await register(inputs);
        } catch (error) {
            alert(error);
        } finally {
            navigate('/');
        }
    }
    const handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setInputs(values => ({ ...values, [name]: value }));
    };


    const handleModalClose = ()=>{
        navigate('/');
    };
    return (

        <div>
            <Container maxWidth="sm">
                <Stack spacing={2}>

                    <form onSubmit={onSubmitRegister}>
                        <TextField
                            required
                            id="outlined-required"
                            label="Nombre"
                            name="name"
                            onChange={handleChange}
                        />
                        <TextField
                            required
                            id="outlined-required"
                            label="Apellido"
                            name="lastName"
                            onChange={handleChange}
                        />

                        <TextField
                            required
                            id="outlined-required"
                            label="ID"
                            name="id"

                            onChange={handleChange}
                        />

                        <TextField
                            id="filled-password-input"
                            label="Password"
                            type="password"
                            autoComplete="current-password"
                            variant="filled"
                            name="password"

                            onChange={handleChange}
                            required
                        />
                        <Button variant="outlined" type="submit">Registrarse</Button>
                    </form>
                    <Modal title={modal.title}  body={modal.body} handleClose={handleModalClose} isOpen={modal.isOpen} />


                    <Button variant="contained" onClick={() => navigate('/')}> Iniciar sesion</Button>

                </Stack>

            </Container>


        





        </div>

    );
}
export default Register;