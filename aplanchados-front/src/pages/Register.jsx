import { TextField } from "@mui/material";

import Stack from '@mui/material/Stack';

import Container from '@mui/material/Container';

import Button from '@mui/material/Button';
import { useNavigate } from "react-router-dom";
function Register() {
    const navigate=useNavigate();
    return (

        <div>
            <Container maxWidth="sm">
                <Stack spacing={2}>


                    <TextField
                        required
                        id="outlined-required"
                        label="Nombre"
                    />
                    <TextField
                        required
                        id="outlined-required"
                        label="Apellido"
                    />

                    <TextField
                        required
                        id="outlined-required"
                        label="ID"
                    />

                    <TextField
                        id="filled-password-input"
                        label="Password"
                        type="password"
                        autoComplete="current-password"
                        variant="filled"
                        required
                    />



                    <Button variant="outlined"> Registrarse</Button>

                    <Button variant="contained" onClick={()=>navigate('/')}> Iniciar sesion</Button>
                </Stack>

            </Container>
        </div>

    );
}
export default Register;