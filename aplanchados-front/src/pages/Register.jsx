import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useNavigate } from "react-router-dom";
import { register } from "../services/authService";
import { useState } from "react";
import Modal from "../components/Modal";

export default function Register() {
    const nav = useNavigate();
    const MODAL_TITLE_SUCCESS = "Registrado exitosamente";
    const MODAL_BODY_SUCCESS = "Bienvenido a Aplanchados";
    const MODAL_TITLE_FAILURE = "Registro no exitoso";
    const MODAL_BODY_FAILURE = "Parece que el ID ya fue usado por otra persona";
    const [inputs, setInputs] = useState({});
    const [modal, setModal] = useState({ isOpen: false, handleClose: () => {} });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setInputs(values => ({ ...values, [name]: value }));
    };

    async function onSubmitRegister(e) {
        e.preventDefault();
        try {
            await register(inputs);
            setModal({ title: MODAL_TITLE_SUCCESS, body: MODAL_BODY_SUCCESS, isOpen: true, handleClose: () => setModal({ isOpen: false }) });
            setTimeout(() => nav('/'), 3000);
        } catch (error) {
            setModal({ title: MODAL_TITLE_FAILURE, body: MODAL_BODY_FAILURE, isOpen: true, handleClose: () => setModal({ isOpen: false }) });
        }
    }

    return (
        <Box
            sx={{
                height: '100vh', // Ocupa todo el alto de la ventana
                backgroundImage: 'url("/images/aplanchado.jpg")', // Fondo de la imagen
                backgroundSize: 'cover', // La imagen cubre todo el fondo
                backgroundPosition: 'center', // Centrado de la imagen
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
            }}
        >
            <Container
                maxWidth="xs"
                sx={{
                    backgroundColor: 'white', // Color de fondo blanco para el formulario
                    padding: '2rem',
                    borderRadius: '8px',
                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)', // Sombras para el cuadro del formulario
                }}
            >
                <Box sx={{ textAlign: 'center', mb: 3 }}>
                    <Typography variant="h4" component="h1" sx={{ color: '#f57c00', fontWeight: 'bold' }}>
                        Registro en Aplanchados
                    </Typography>
                </Box>
                <Stack spacing={2}>
                    <form onSubmit={onSubmitRegister}>
                        <TextField
                            fullWidth
                            label="Nombre"
                            required
                            name="name"
                            variant="outlined"
                            onChange={handleChange}
                            sx={{ mb: 2 }}
                        />
                        <TextField
                            fullWidth
                            label="Apellido"
                            required
                            name="lastName"
                            variant="outlined"
                            onChange={handleChange}
                            sx={{ mb: 2 }}
                        />
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
                            Registrarse
                        </Button>
                    </form>
                    <Modal title={modal.title} body={modal.body} handleClose={modal.handleClose} isOpen={modal.isOpen} />
                    <Button
                        variant="outlined"
                        fullWidth
                        onClick={() => nav('/')}
                        sx={{
                            color: '#f57c00',
                            borderColor: '#f57c00',
                            fontWeight: 'bold',
                            '&:hover': { borderColor: '#e64a19', color: '#e64a19' },
                        }}
                    >
                        Iniciar sesión
                    </Button>
                </Stack>
            </Container>
        </Box>
    );
}
