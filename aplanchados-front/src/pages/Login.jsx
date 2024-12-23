import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { authenticate, getPermissions } from "../services/authService";
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useDispatch } from 'react-redux';
import { setPermissions } from '../slices/authSlice';

export default function Login() {
    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});
    const [error, setError] = useState("");  
    const dispatch = useDispatch();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setInputs(values => ({ ...values, [name]: value }));
    };

    async function handleSubmit(e) {
        e.preventDefault();
        try {
            const token = await authenticate(inputs);
            const permissionsFromToken = getPermissions();
            console.log("PERMISOS DEL TOKEN: ", permissionsFromToken);
            dispatch(setPermissions(permissionsFromToken)); // Update Redux store with permissions
            localStorage.setItem('user_id', inputs.id);  // Guarda el ID del usuario en localStorage
            navigate('home');


        } catch {
            setError("Credenciales incorrectas. Intenta nuevamente."); 
        } finally {
            setInputs({ id: '', password: '' });
        }
    }

    return (
        <Box
            sx={{
                minHeight: '100vh',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                backgroundImage: 'url("/images/aplanchadoV2.jpg")',
                backgroundSize: 'cover',
                backgroundPosition: 'center',
            }}
        >
            <Container
                maxWidth="xs"
                sx={{
                    backgroundColor: 'rgba(255, 255, 255, 0.85)', // Fondo semitransparente
                    padding: '2rem',
                    borderRadius: '8px',
                    boxShadow: '0px 4px 20px rgba(0, 0, 0, 0.1)',
                }}
            >
                <Box sx={{ textAlign: 'center', mb: 3 }}>
                    <Typography variant="h4" component="h1" sx={{ color: '#f57c00', fontWeight: 'bold' }}>
                        Login
                    </Typography>
                </Box>
                <Stack spacing={2}>
                    <form onSubmit={handleSubmit}>
                        <TextField
                            fullWidth
                            label="ID"
                            required
                            name="id"
                            variant="outlined"
                            onChange={handleChange}
                            sx={{ mb: 2 }}
                        />
                        <TextField
                            fullWidth
                            label="Password"
                            type="password"
                            required
                            name="password"
                            variant="outlined"
                            onChange={handleChange}
                            sx={{ mb: 2 }}
                        />
                        {/* Mostrar el mensaje de error si existe */}
                        {error && (
                            <Typography variant="body2" color="error" sx={{ textAlign: 'center', mb: 2 }}>
                                {error}
                            </Typography>
                        )}
                        <Button
                            variant="contained"
                            fullWidth
                            type="submit"
                            sx={{
                                backgroundColor: '#f57c00',
                                color: '#fff',
                                '&:hover': { backgroundColor: '#e64a19' },
                                fontWeight: 'bold',
                                fontSize: '1rem',
                            }}
                        >
                            Iniciar Sesión
                        </Button>
                    </form>
                    <Button
                        variant="outlined"
                        fullWidth
                        onClick={() => navigate('register')}
                        sx={{
                            color: '#f57c00',
                            borderColor: '#f57c00',
                            fontWeight: 'bold',
                            '&:hover': { borderColor: '#e64a19', color: '#e64a19' },
                        }}
                    >
                        Registrarse
                    </Button>
                </Stack>
            </Container>
        </Box>
    );
}
