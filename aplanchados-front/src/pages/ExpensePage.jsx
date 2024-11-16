import React, { useState } from 'react';
import {
    TextField,
    Typography,
    Button,
    Container,
    MenuItem,
    Select,
    FormControl,
    InputLabel,
    Box,
    Stack,
} from '@mui/material';
import { axiosInstance } from '../services/axios';
import { useNavigate } from 'react-router-dom';
import { getUserIdFromToken } from '../services/authService';

export default function ExpensePage() {
    const navigate = useNavigate();
    const [value, setValue] = useState('');
    const [additionalDetail, setAdditionalDetail] = useState('');
    const [personDocumentNumber, setPersonDocumentNumber] = useState('');
    const [date, setDate] = useState('');
    const [paymentMethodCode, setPaymentMethodCode] = useState('');
    const [paymentTypeCode, setPaymentTypeCode] = useState('');
    const [expenseConceptCode, setExpenseConceptCode] = useState('');
    const [error, setError] = useState('');
    const [personName, setPersonName] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const userId = getUserIdFromToken();

        let formattedDate = date;
        if (formattedDate) {
            formattedDate += ':00';
        }

        const expenseData = {
            id: null,
            userId: userId,
            value: parseInt(value),
            additionalDetail: additionalDetail,
            personDocumentNumber: personDocumentNumber,
            personName: personName,
            date: date ? date : null,
            paymentMethodCode: parseInt(paymentMethodCode),
            paymentTypeCode: parseInt(paymentTypeCode),
            expenseConceptCode: parseInt(expenseConceptCode),
        };

        try {
            const response = await axiosInstance.post('/expense/create', expenseData);
            if (response.status === 200 || response.status === 201) {
                alert('Gasto registrado con éxito');
                navigate('/home');
            } else {
                setError('Error durante el registro del gasto');
            }
        } catch (error) {
            console.error('Error:', error);
            setError('Ocurrió un error al registrar el gasto');
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
                        Registrar Gasto
                    </Typography>
                </Box>
                <form onSubmit={handleSubmit}>
                    <Stack spacing={2}>
                        <TextField
                            fullWidth
                            label="Valor"
                            type="number"
                            value={value}
                            onChange={(e) => setValue(e.target.value)}
                            required
                        />
                        <TextField
                            fullWidth
                            label="Detalles adicionales"
                            value={additionalDetail}
                            onChange={(e) => setAdditionalDetail(e.target.value)}
                            multiline
                            rows={3}
                        />
                        <TextField
                            fullWidth
                            label="Número de documento específico (a quien va dirigida la salida)"
                            value={personDocumentNumber}
                            onChange={(e) => setPersonDocumentNumber(e.target.value)}
                            required
                        />
                        <TextField
                            fullWidth
                            label="Nombre de la persona"
                            value={personName}
                            onChange={(e) => setPersonName(e.target.value)}
                            required
                        />
                        <TextField
                            fullWidth
                            label="Fecha"
                            type="datetime-local"
                            value={date}
                            onChange={(e) => setDate(e.target.value)}
                            InputLabelProps={{ shrink: true }}
                            required
                        />
                        <FormControl fullWidth>
                            <InputLabel>Método de pago</InputLabel>
                            <Select
                                value={paymentMethodCode}
                                onChange={(e) => setPaymentMethodCode(e.target.value)}
                                required
                            >
                                <MenuItem value={1}>Tarjeta de Crédito</MenuItem>
                                <MenuItem value={2}>Efectivo</MenuItem>
                                {/* Otros métodos de pago */}
                            </Select>
                        </FormControl>
                        <FormControl fullWidth>
                            <InputLabel>Tipo de pago</InputLabel>
                            <Select
                                value={paymentTypeCode}
                                onChange={(e) => setPaymentTypeCode(e.target.value)}
                                required
                            >
                                <MenuItem value={1}>Contado</MenuItem>
                                <MenuItem value={2}>Crédito</MenuItem>
                                {/* Otros tipos de pago */}
                            </Select>
                        </FormControl>
                        <FormControl fullWidth>
                            <InputLabel>Código del concepto de egreso</InputLabel>
                            <Select
                                value={expenseConceptCode}
                                onChange={(e) => setExpenseConceptCode(e.target.value)}
                                required
                            >
                                <MenuItem value={1}>Insumo</MenuItem>
                                <MenuItem value={2}>Salida gerencia</MenuItem>
                                <MenuItem value={3}>Gastos operativos</MenuItem>
                                {/* Otros conceptos de egreso */}
                            </Select>
                        </FormControl>
                        {error && (
                            <Typography variant="body1" color="error">
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
                            Registrar Gasto
                        </Button>
                    </Stack>
                </form>
            </Container>
        </Box>
    );
}
