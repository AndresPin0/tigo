import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { usePermissions } from '../context/PermissionsContext'; // Usa el contexto de permisos

export default function Home() {
    const navigate = useNavigate();
    const permissions = usePermissions();
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('access_token');
        if (!token) {
            navigate('/'); // Redirigir al login si no hay token
            return;
        }

        // Verifica si la página ya se recargó antes usando localStorage
        const hasReloaded = localStorage.getItem('hasReloaded');
        if (!hasReloaded) {
            localStorage.setItem('hasReloaded', 'true');  // Marca que la recarga ha ocurrido
            window.location.reload();  // Recargar la página
            return; // Salir para evitar continuar con el código después de la recarga
        }

        // Terminar la carga cuando los permisos estén disponibles
        if (permissions.length > 0) {
            setIsLoading(false); // Terminar la carga cuando los permisos estén disponibles
        }
    }, [permissions, navigate]); // Dependencias para ejecutar correctamente

    if (isLoading) {
        return <Typography variant="h6">Cargando...</Typography>;
    }

    const hasPermission = (permission) => permissions.includes(permission);

    console.log('Permisos del usuario:', permissions);

    return (
        <Container
            sx={{
                minHeight: '100vh',
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
                padding: '2rem',
                backgroundImage: 'url(/images/aplanchado2.jpg)',
                backgroundSize: 'cover',
                backgroundPosition: 'center',
            }}
        >
            <Box
                sx={{
                    backgroundColor: 'rgba(0, 0, 0, 0.5)',
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
                    {hasPermission('GENERATE REPORT') && (
                        <button
                            onClick={() => navigate('/reports/monthly')}
                            style={buttonStyle}
                        >
                            Generar Reporte Mensual
                        </button>
                    )}
                </Box>
            </Box>
        </Container>
    );
}

const buttonStyle = {
    backgroundColor: '#f57c00',
    color: '#fff',
    border: 'none',
    fontSize: '16px',
    padding: '10px 20px',
    cursor: 'pointer',
    margin: '10px',
    borderRadius: '5px',
    transition: 'background-color 0.3s',
};
