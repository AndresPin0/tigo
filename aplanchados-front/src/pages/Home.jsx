import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';

import Container from '@mui/material/Container';

import {Outlet} from 'react-router-dom';
export default function Home() {
    return (
        <div>
            <Outlet />
            <Container maxWidth="sm">
                <Stack spacing={2}>


                    <TextField
                        
                        id="outlined-required"
                        label="ID"
                        required
                    />
                    <TextField
                        id="filled-password-input"
                        label="Password"
                        type="password"
                        autoComplete="current-password"
                        variant="filled"
                        required
                    />

                    <Button variant="contained"> Iniciar sesion</Button>
                    <Button variant="outlined">Registrarse</Button>
                </Stack>

            </Container>
        </div>

    );
}
