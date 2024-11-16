import React, { useState } from 'react';
import { TextField, Typography, Button, Container, MenuItem, Select, FormControl, InputLabel } from '@mui/material';
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
        <Container maxWidth="sm" sx={{ /* estilos */ }}>
            <Typography variant="h4" component="h1" sx={{ /* estilos */ }}>
                Registrar Gasto
            </Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    fullWidth
                    label="Valor"
                    type="number"
                    value={value}
                    onChange={(e) => setValue(e.target.value)}
                    required
                    margin="normal"
                />
                <TextField
                    fullWidth
                    label="Detalles adicionales"
                    value={additionalDetail}
                    onChange={(e) => setAdditionalDetail(e.target.value)}
                    margin="normal"
                    multiline
                    rows={3}
                />
                <TextField
                    fullWidth
                    label="Número de documento específico (a quien va dirigida la salida)"
                    value={personDocumentNumber}
                    onChange={(e) => setPersonDocumentNumber(e.target.value)}
                    required
                    margin="normal"
                />
                <TextField
                    fullWidth
                    label="Fecha"
                    type="datetime-local"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    required
                    margin="normal"
                />
                <FormControl fullWidth margin="normal">
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
                <FormControl fullWidth margin="normal">
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
                <FormControl fullWidth margin="normal">
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
                    <Typography variant="body1" color="error" sx={{ marginBottom: '1rem' }}>
                        {error}
                    </Typography>
                )}
                <Button
                    variant="contained"
                    fullWidth
                    type="submit"
                    sx={{ /* estilos */ }}
                >
                    Registrar Gasto
                </Button>
            </form>
        </Container>
    );
}
