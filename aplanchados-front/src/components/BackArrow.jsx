import React from 'react';
import { useNavigate } from 'react-router-dom';
import { IconButton } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

function BackArrow() {
    const navigate = useNavigate();

    const handleGoBack = () => {
        navigate(-1); // Navega a la página anterior
    };

    return (
        <IconButton 
            onClick={handleGoBack} 
            aria-label="Volver"
            sx={{
                position: 'absolute', // Para posicionar según lo necesites
                top: '1rem',
                left: '1rem',
                backgroundColor: '#f57c00',
                color: 'white',
                '&:hover': {
                    backgroundColor: '#e64a19',
                },
            }}
        >
            <ArrowBackIcon />
        </IconButton>
    );
}

export default BackArrow;
