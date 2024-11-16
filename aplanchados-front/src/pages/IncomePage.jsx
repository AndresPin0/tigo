import React, { useState } from 'react';
import { TextField, Typography, Button, Container, MenuItem, Select, FormControl, InputLabel } from '@mui/material';
import { axiosInstance } from '../services/axios';
import { useNavigate } from 'react-router-dom';
import { getUserIdFromToken } from '../services/authService';

export default function IncomePage() {
    const navigate = useNavigate();
    const [value, setValue] = useState('');
    const [additionalDetail, setAdditionalDetail] = useState('');
    const [personDocumentNumber, setPersonDocumentNumber] = useState('');
    const [date, setDate] = useState('');
    const [paymentMethodCode, setPaymentMethodCode] = useState('');
    const [paymentTypeCode, setPaymentTypeCode] = useState('');
    const [incomeConceptCode, setIncomeConceptCode] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const userId = getUserIdFromToken();

        // Ajustar el formato de la fecha si es necesario
        let formattedDate = date;
        if (formattedDate) {
            // Agregar ":00" para los segundos
            formattedDate += ':00';
            // Si es necesario reemplazar 'T' por un espacio:
            // formattedDate = formattedDate.replace('T', ' ');
        }

        const incomeData = {
            id: null,
            userId: userId,
            value: parseInt(value),
            additionalDetail: additionalDetail,
            personDocumentNumber: personDocumentNumber,
            date: formattedDate ? formattedDate : null,
            paymentMethodCode: parseInt(paymentMethodCode),
            paymentTypeCode: parseInt(paymentTypeCode),
            incomeConceptCode: parseInt(incomeConceptCode),
        };

        try {
            const response = await axiosInstance.post('/income/create', incomeData);
            if (response.status === 200 || response.status === 201) {
                alert('Ingreso registrado con éxito');
                navigate('/home');
            } else {
                setError('Error durante el registro del ingreso');
            }
        } catch (error) {
            console.error('Error:', error);
            setError('Ocurrió un error al registrar el ingreso');
        }
    };

    return (
        <Container maxWidth="sm" sx={{ /* Puedes agregar tus estilos aquí */ }}>
            <Typography variant="h4" component="h1" sx={{ /* Estilos opcionales */ }}>
                Registrar Ingreso
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
                    label="Número de documento específico (de quien proviene la entrada)"
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
                    <InputLabel>Código del concepto de ingreso</InputLabel>
                    <Select
                        value={incomeConceptCode}
                        onChange={(e) => setIncomeConceptCode(e.target.value)}
                        required
                    >
                        <MenuItem value={1}>Venta</MenuItem>
                        <MenuItem value={2}>Comisión</MenuItem>
                        <MenuItem value={3}>Ingreso por servicio</MenuItem>
                        <MenuItem value={4}>Reembolso</MenuItem>
                        {/* Otros conceptos de ingreso */}
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
                    sx={{ /* Estilos opcionales */ }}
                >
                    Registrar Ingreso
                </Button>
            </form>
        </Container>
    );
}
