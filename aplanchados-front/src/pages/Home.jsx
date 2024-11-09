import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';

import Container from '@mui/material/Container';

import { Outlet, useNavigate } from 'react-router-dom';
import React from "react";

import { BottomNavigation, BottomNavigationAction } from "@mui/material";

export default function Home() {
    return (
        <div>
            <Outlet />
            <Container maxWidth="sm">
                <Stack spacing={2}>


                    <TextField

                        id="outlined-required"
                        label="ID"
                        required
                    />
                    <TextField
                        id="filled-password-input"
                        label="Password"
                        type="password"
                        autoComplete="current-password"
                        variant="filled"
                        required
                    />

                    <Button variant="contained"> Iniciar sesion</Button>

                </Stack>

            </Container>
        </div>

    );
}
