import { useState } from 'react';
import { axiosInstance } from '../services/axios';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';

export default function ReportPage() {
    const [file, setFile] = useState(null);

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleUpload = async () => {
        if (!file) {
            alert('Por favor selecciona un archivo.');
            return;
        }

        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await axiosInstance.post('/report/upload-excel', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            alert('Archivo cargado y procesado con éxito');
            setFile(null);
        } catch (error) {
            console.error('Error subiendo archivo:', error);
            alert('Error durante la carga del archivo');
        }
    };

    const handleDownload = () => {
        window.location.href = `${process.env.REACT_APP_API}report/generate-excel`;
    };

    return (
        <Container maxWidth="sm" sx={{ mt: 5 }}>
            <Box>
                <Typography variant="h4" component="h1" gutterBottom>
                    Cargar Movimientos desde Excel
                </Typography>
                <input type="file" accept=".xls,.xlsx" onChange={handleFileChange} />
                <Button variant="contained" color="primary" onClick={handleUpload} sx={{ ml: 2 }}>
                    Upload
                </Button>
                <Button variant="outlined" color="secondary" onClick={handleDownload} sx={{ ml: 2 }}>
                    Descargar Reporte Diario
                </Button>
            </Box>
        </Container>
    );
}
