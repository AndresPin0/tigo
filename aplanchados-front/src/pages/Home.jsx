import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { usePermissions } from '../context/PermissionsContext'; // Obtener permisos desde el contexto

export default function Home() {
    const navigate = useNavigate();
    const permissions = usePermissions(); // Obtener permisos del contexto
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('access_token');
        if (!token) {
            navigate('/'); // Redirigir al login si no hay token
            return;
        }
        setIsLoading(false); // Terminar la carga cuando el token esté disponible
    }, [navigate]);

    if (isLoading) {
        return <Typography variant="h6">Cargando...</Typography>;
    }

    // Verificar si el usuario tiene un permiso específico
    const hasPermission = (permission) => permissions.includes(permission);

    return (
        <Container
            sx={{
                minHeight: '100vh',
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
                padding: '2rem',
                backgroundImage: 'url(/path/to/your/image.jpg)', // Reemplazar con la ruta de tu imagen
                backgroundSize: 'cover',
                backgroundPosition: 'center',
            }}
        >
            <Box
                sx={{
                    backgroundColor: 'rgba(0, 0, 0, 0.5)', // Fondo oscuro semi-transparente para el texto
                    padding: '2rem',
                    borderRadius: '8px',
                    textAlign: 'center',
                    color: 'white',
                    maxWidth: '600px',
                    width: '100%',
                }}
            >
                <Typography variant="h3" sx={{ fontWeight: 'bold', fontSize: '2.5rem' }}>
                    Bienvenido a Aplanchados
                </Typography>
                <Typography variant="h5" sx={{ mt: 2, fontSize: '1.5rem' }}>
                    Explora nuestros servicios y gestiona tus pedidos
                </Typography>

                {/* Mostrar botones solo si el usuario tiene los permisos correspondientes */}
                <Box sx={{ mt: 4 }}>
                    {hasPermission('MANAGE SYSTEM') && (
                        <button
                            onClick={() => navigate('/manager')}
                            style={buttonStyle}
                        >
                            Configuración de Manager
                        </button>
                    )}
                    {hasPermission('ADD EXPENSE') && (
                        <button
                            onClick={() => navigate('/expense')}
                            style={buttonStyle}
                        >
                            Agregar Salida
                        </button>
                    )}
                    {hasPermission('ADD INCOME') && (
                        <button
                            onClick={() => navigate('/income/create')}
                            style={buttonStyle}
                        >
                            Agregar Entrada
                        </button>
                    )}
                    {hasPermission('GENERATE REPORT') && (
                        <button
                            onClick={() => navigate('/report/upload-excel')}
                            style={buttonStyle}
                        >
                            Generar Reporte Diario
                        </button>
                    )}
                </Box>
            </Box>
        </Container>
    );
}

// Estilos de los botones
const buttonStyle = {
    backgroundColor: '#f57c00', // Naranja
    color: 'white',
    border: 'none',
    padding: '10px 20px',
    fontSize: '1rem',
    cursor: 'pointer',
    marginTop: '10px',
    borderRadius: '25px',
    transition: 'background-color 0.3s',
    marginBottom: '20px', // Espacio entre los botones
};
