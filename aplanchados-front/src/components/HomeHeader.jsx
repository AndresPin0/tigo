import { AppBar, Box, Toolbar, Typography, IconButton } from '@mui/material';
import { Link } from 'react-router-dom';

function HomeHeader() {
    return (
        <AppBar position="static" sx={{ backgroundColor: '#f57c00' }}>
            <Toolbar sx={{ display: 'flex', justifyContent: 'space-between' }}>
                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                    <IconButton edge="start" color="inherit" aria-label="logo" sx={{ mr: 2 }}>
                        <img src="/images/logo.jpeg" alt="Logo" style={{ height: '40px' }} /> 
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ fontWeight: 'bold' }}>
                        Aplanchados
                    </Typography>
                </Box>
                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                    <Link to="/" style={{ color: 'white', textDecoration: 'none', marginRight: '20px' }}>
                        Home
                    </Link>
                    <Link to="/about" style={{ color: 'white', textDecoration: 'none' }}>
                        About
                    </Link>
                </Box>
            </Toolbar>
        </AppBar>
    );
}

export default HomeHeader;
