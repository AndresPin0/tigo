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

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

// Plugin para mostrar etiquetas encima de las barras
const barLabelPlugin = {
    id: 'barLabelPlugin',
    afterDatasetsDraw(chart) {
        const { ctx, data } = chart;
        const dataset = data.datasets[0];
        chart.getDatasetMeta(0).data.forEach((bar, index) => {
            const value = dataset.data[index];
            ctx.save();
            ctx.font = 'bold 12px Arial';
            ctx.textAlign = 'center';
            ctx.fillStyle = '#000';
            ctx.fillText(value, bar.x, bar.y - 10); 
            ctx.restore();
        });
    },
};

export default function MonthlyReportPage() {
    const [month, setMonth] = useState('');
    const [reportData, setReportData] = useState(null);
    const [previousReportData, setPreviousReportData] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');

    const handleMonthChange = (event) => {
        setMonth(event.target.value);
    };

    const fetchMonthlyReports = async () => {
        if (!month) {
            setErrorMessage('Por favor selecciona un mes.');
            return;
        }

        try {
            const currentResponse = await axiosInstance.get(`/reports/monthly?month=${month}`);
            setReportData(currentResponse.data);

            const previousMonth = month === '1' ? '12' : (parseInt(month) - 1).toString();
            const previousResponse = await axiosInstance.get(`/reports/monthly?month=${previousMonth}`);
            setPreviousReportData(previousResponse.data);

            setErrorMessage('');
        } catch (error) {
            console.error('Error al obtener los reportes:', error);
            setErrorMessage('No se pudo obtener los reportes para los meses seleccionados.');
        }
    };

    const months = [
        'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
        'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre',
    ];

    const chartData = reportData
        ? {
              labels: ['Ingresos', 'Gastos'],
              datasets: [
                  {
                      label: 'Totales en $',
                      data: [reportData.totalIncome, reportData.totalExpense],
                      backgroundColor: ['#4caf50', '#f44336'],
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

    const getComparisonText = () => {
        if (!reportData || !previousReportData) return '';

        const incomeDifference =
            reportData.totalIncome - previousReportData.totalIncome;
        const expenseDifference =
            reportData.totalExpense - previousReportData.totalExpense;

        const incomeTrend = incomeDifference > 0 ? 'incrementaron' : 'disminuyeron';
        const expenseTrend = expenseDifference > 0 ? 'incrementaron' : 'disminuyeron';

        return (
            <>
                <Typography variant="body1" sx={{ mt: 2 }}>
                    Comparado con el mes anterior:
                </Typography>
                <Typography variant="body2">
                    - Los ingresos {incomeTrend} en ${Math.abs(incomeDifference)}.
                </Typography>
                <Typography variant="body2">
                    - Los gastos {expenseTrend} en ${Math.abs(expenseDifference)}.
                </Typography>
            </>
        );
    };

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
                            onClick={fetchMonthlyReports}
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

                    {reportData && (
                        <Box sx={{ mt: 4 }}>
                            <Typography variant="h6" sx={{ mb: 2 }}>
                                Resultados del mes: {months[parseInt(month) - 1]}
                            </Typography>
                            <Bar data={chartData} options={chartOptions} plugins={[barLabelPlugin]} />
                            {getComparisonText()}
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
