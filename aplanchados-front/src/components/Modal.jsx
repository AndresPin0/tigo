import { Box, Typography, Button, Modal as MuiModal } from '@mui/material';

export default function Modal({ title, body, handleClose, isOpen }) {
    return (
        <MuiModal open={isOpen} onClose={handleClose} sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <Box
                sx={{
                    bgcolor: 'background.paper',
                    borderRadius: 2,
                    boxShadow: 24,
                    p: 4,
                    maxWidth: 400,
                    width: '90%',
                    border: '2px solid #f57c00',
                    textAlign: 'center',
                }}
            >
                <Typography variant="h6" component="h2" sx={{ color: '#f57c00', fontWeight: 'bold', mb: 2 }}>
                    {title}
                </Typography>
                <Typography sx={{ mb: 3 }}>
                    {body}
                </Typography>
                <Button
                    variant="contained"
                    onClick={handleClose}
                    sx={{
                        backgroundColor: '#f57c00',
                        color: '#fff',
                        '&:hover': { backgroundColor: '#e64a19' },
                        fontWeight: 'bold',
                        mt: 2,
                        borderRadius: 2,
                    }}
                >
                    Cerrar
                </Button>
            </Box>
        </MuiModal>
    );
}
