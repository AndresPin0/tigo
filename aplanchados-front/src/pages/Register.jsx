import { TextField } from "@mui/material";

import Stack from '@mui/material/Stack';

import Container from '@mui/material/Container';

import Button from '@mui/material/Button';
function Register() {
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



                    <Button variant="contained"> Registrarse</Button>
                </Stack>

            </Container>
        </div>

    );
}
export default Register;