import React, { useState } from 'react';
import {
    Typography,
    Button,
    Container,
    Box,
    Stack,
    Input,
} from '@mui/material';
import { axiosInstance } from '../services/axios';

export default function ReportAndUploadPage() {
    const [file, setFile] = useState(null);
    const [uploadMessage, setUploadMessage] = useState('');
    const [reportMessage, setReportMessage] = useState('');

    // Manejo del archivo para subir
    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    // Subir archivo Excel
    const handleUpload = async () => {
        if (!file) {
            setUploadMessage('Por favor selecciona un archivo.');
            return;
        }

        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await axiosInstance.post('/read-excel', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });

            if (response.status === 200) {
                setUploadMessage('Archivo procesado exitosamente.');
            } else {
                setUploadMessage('Error al procesar el archivo.');
            }
        } catch (error) {
            console.error('Error:', error);
            setUploadMessage('Ocurrió un error al subir el archivo.');
        }
    };

    // Generar reporte diario
    const handleGenerateReport = async () => {
        try {
            const response = await axiosInstance.get('/generate-excel', {
                responseType: 'blob', // Para manejar la descarga del archivo
            });

            // Crear enlace para descargar el archivo
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', `dailyReport-${new Date().toISOString().split('T')[0]}.xlsx`);
            document.body.appendChild(link);
            link.click();

            setReportMessage('Reporte generado exitosamente.');
        } catch (error) {
            console.error('Error:', error);
            setReportMessage('Error al generar el reporte.');
        }
    };

    return (
        <Box
            sx={{
                minHeight: '100vh',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                backgroundImage: 'url("/images/aplanchado.jpg")',
                backgroundSize: 'cover',
                backgroundPosition: 'center',
            }}
        > 
            <Container
                maxWidth="sm"
                sx={{
                    backgroundColor: 'rgba(255, 255, 255, 0.85)',
                    padding: '2rem',
                    borderRadius: '8px',
                    boxShadow: '0px 4px 20px rgba(0, 0, 0, 0.1)',
                }}
            >
                <Box sx={{ textAlign: 'center', mb: 3 }}>
                    <Typography
                        variant="h4"
                        component="h1"
                        sx={{ color: '#f57c00', fontWeight: 'bold' }}
                    >
                        Reportes y Subida de Archivos
                    </Typography>
                </Box>

                <Stack spacing={4}>
                    {/* Sección para Generar Reporte */}
                    <Box sx={{ textAlign: 'center' }}>
                        <Typography variant="h6" sx={{ mb: 2 }}>
                            Generar Reporte Diario
                        </Typography>
                        <Button
                            variant="contained"
                            fullWidth
                            onClick={handleGenerateReport}
                            sx={{
                                backgroundColor: '#f57c00',
                                color: '#fff',
                                '&:hover': { backgroundColor: '#e64a19' },
                                fontWeight: 'bold',
                                fontSize: '1rem',
                            }}
                        >
                            Generar Reporte
                        </Button>
                        {reportMessage && (
                            <Typography variant="body1" color="success" sx={{ mt: 2 }}>
                                {reportMessage}
                            </Typography>
                        )}
                    </Box>

                    {/* Sección para Subir Archivo */}
                    <Box>
                        <Typography variant="h6" sx={{ mb: 2, textAlign: 'center' }}>
                            Subir Archivo Excel
                        </Typography>
                        <Input
                            type="file"
                            inputProps={{ accept: '.xlsx' }}
                            onChange={handleFileChange}
                            fullWidth
                            sx={{ mb: 2 }}
                        />
                        <Button
                            variant="contained"
                            fullWidth
                            onClick={handleUpload}
                            sx={{
                                backgroundColor: '#f57c00',
                                color: '#fff',
                                '&:hover': { backgroundColor: '#e64a19' },
                                fontWeight: 'bold',
                                fontSize: '1rem',
                            }}
                        >
                            Subir y Procesar
                        </Button>
                        {uploadMessage && (
                            <Typography variant="body1" color="error" sx={{ mt: 2 }}>
                                {uploadMessage}
                            </Typography>
                        )}
                    </Box>
                </Stack>
            </Container>
        </Box>
    );
}
