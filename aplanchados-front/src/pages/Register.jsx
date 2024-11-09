import { TextField } from "@mui/material";

import Stack from '@mui/material/Stack';

import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import { useNavigate } from "react-router-dom";
import { register } from "../services/authService";
import { useState } from "react";
import Modal from "../components/Modal";


function Register() {

    const nav = useNavigate();
    const navigate = (path) => {
        nav(path);
    };

    const MODAL_TITLE_SUCESS = "Registrado exitosamente";
    const MODAL_BODY_SUCCES = "Bienvenido a aplanchados";
 
    const MODAL_TITLE_FAILURE = "Registro no exitoso";
    const MODAL_BODY_FAILURE = "Parece que el id ya fue usado por otra persona";
   

    const [inputs, setInputs] = useState({});
    const [modal, setModal] = useState({ isOpen: false, handleClose: () => { } });
   
    async function onSubmitRegister(e) {
        e.preventDefault();
        try {
            await register(inputs);
            setModal(prev => ({ ...prev, title: MODAL_TITLE_SUCESS }));
            setModal(prev => ({ ...prev, body: MODAL_BODY_SUCCES }));
            
            setModal(prev => ({ ...prev, handleClose: ()=>{} }));

            setModal(prev => ({ ...prev, isOpen: true}));
            setTimeout(() => {   setModal(prev => ({ ...prev, isOpen: false})); }, 3000);
            setTimeout(() => {  navigate('/'); }, 3000);
          //  nav('/');

        } catch (error) {
            setModal(prev => ({ ...prev, title: MODAL_TITLE_FAILURE }));
            setModal(prev => ({ ...prev, body: MODAL_BODY_FAILURE }));
            setModal(prev => ({ ...prev, handleClose:()=> { }}));
            setModal(prev => ({ ...prev, isOpen: true}));
           setTimeout(() => {   setModal(prev => ({ ...prev, isOpen: false })); }, 3000);
         //  nav('/');
        }

    }
    const handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setInputs(values => ({ ...values, [name]: value }));
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
                    <Modal title={modal.title} body={modal.body} handleClose={modal.handleClose} isOpen={modal.isOpen} />


                    <Button variant="contained" onClick={() => nav('/')}> Iniciar sesion</Button>

                </Stack>

            </Container>








        </div>

    );
}
export default Register;