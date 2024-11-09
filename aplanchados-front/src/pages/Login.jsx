import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';

import Container from '@mui/material/Container';



import { useNavigate } from "react-router-dom";




export default function Home() {
    const navigate = useNavigate();
    
    return (
        <div>
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

                    <Button variant="outlined" onClick={() => navigate('register')}> Registrarse</Button>
                       

                </Stack>

            </Container>
        </div>

    );
}
