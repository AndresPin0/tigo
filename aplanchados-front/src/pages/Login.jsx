import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';

import Container from '@mui/material/Container';



import { useNavigate } from "react-router-dom";

import { useState } from "react";
import { authenticate,getPermissions } from "../services/authService";


export default function Home() {
    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});
    const handleChange = (e) => {
        const name = e.target.name;
        const value = e.target.value;
        setInputs(values => ({ ...values, [name]: value }));
    };

    async function handleSubmit(e) {
        e.preventDefault();

        try {
            await authenticate(inputs);
            alert("CREDENCIALES CORRECTAS");
            alert("PERMISOS DEL TOKEN"+getPermissions());
            //CONTINUAR AL LA PAGINA PRINCIPAL
        }catch{
            alert("CREDENCIALES INCORRRECTAS");
            //NO HACER NADA
        }finally{
            setInputs({ id: '', password: ''});
        }
    }

    return (
        <div>
            <Container maxWidth="sm">
                <Stack spacing={2}>

                    <form onSubmit={handleSubmit}>
                        <TextField

                            id="outlined-required"
                            label="ID"
                            required
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
                            required
                            onChange={handleChange}
                        />

                        <Button variant="contained" type="submit" > Iniciar sesion</Button>
                    </form>
                    <Button variant="outlined" onClick={() => navigate('register')}> Registrarse</Button>


                </Stack>

            </Container>
        </div>

    );
}
