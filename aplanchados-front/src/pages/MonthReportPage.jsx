import React, { useState } from 'react';
import {
    Typography,
    Button,
    Container,
    Box,
    Stack,
    Select,
    MenuItem,
} from '@mui/material';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';
import { axiosInstance } from '../services/axios';

// Registrar los componentes de Chart.js
ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

export default function MonthlyReportPage() {
    const [month, setMonth] = useState('');
    const [reportData, setReportData] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');

    const handleMonthChange = (event) => {
        setMonth(event.target.value);
    };

    const fetchMonthlyReport = async () => {
        if (!month) {
            setErrorMessage('Por favor selecciona un mes.');
            return;
        }

        try {
            const response = await axiosInstance.get(`/reports/monthly?month=${month}`);
            setReportData(response.data);
            setErrorMessage('');
        } catch (error) {
            console.error('Error al obtener el reporte:', error);
            setErrorMessage('No se pudo obtener el reporte para el mes seleccionado.');
        }
    };

    // Array de nombres de meses
    const months = [
        'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
        'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre',
    ];

    // Configuración del gráfico
    const chartData = reportData
        ? {
              labels: ['Ingresos', 'Gastos'], // Etiquetas de las barras
              datasets: [
                  {
                      label: 'Totales en $',
                      data: [reportData.totalIncome, reportData.totalExpense],
                      backgroundColor: ['#4caf50', '#f44336'], // Colores de las barras
                      borderColor: ['#388e3c', '#d32f2f'],
                      borderWidth: 1,
                  },
              ],
          }
        : null;

    const chartOptions = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: `Reporte del mes: ${months[parseInt(month) - 1] || ''}`,
            },
        },
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
                        Reporte Mensual
                    </Typography>
                </Box>
                <Stack spacing={4}>
                    <Box sx={{ textAlign: 'center' }}>
                        <Typography variant="h6" sx={{ mb: 2 }}>
                            Selecciona un mes
                        </Typography>
                        <Select
                            value={month}
                            onChange={handleMonthChange}
                            displayEmpty
                            sx={{ width: 200, mb: 2 }}
                        >
                            <MenuItem value="" disabled>
                                Selecciona un mes
                            </MenuItem>
                            {months.map((monthName, index) => (
                                <MenuItem key={index} value={index + 1}>
                                    {monthName}
                                </MenuItem>
                            ))}
                        </Select>
                        <Button
                            variant="contained"
                            onClick={fetchMonthlyReport}
                            sx={{
                                backgroundColor: '#f57c00',
                                color: '#fff',
                                '&:hover': { backgroundColor: '#e64a19' },
                                fontWeight: 'bold',
                                fontSize: '1rem',
                            }}
                        >
                            Obtener Reporte
                        </Button>
                    </Box>
    
                    {/* Mostrar el gráfico si hay datos */}
                    {reportData && (
                        <Box sx={{ mt: 4 }}>
                            <Typography variant="h6" sx={{ mb: 2 }}>
                                Resultados del mes: {months[parseInt(month) - 1]}
                            </Typography>
                            <Bar data={chartData} options={chartOptions} />
                        </Box>
                    )}
    
                    {errorMessage && (
                        <Typography color="error" sx={{ mt: 2 }}>
                            {errorMessage}
                        </Typography>
                    )}
                </Stack>
            </Container>
        </Box>
    );
}
