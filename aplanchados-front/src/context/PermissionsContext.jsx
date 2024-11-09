import React, { createContext, useContext, useState, useEffect } from 'react';
import { getPermissions } from '../services/authService';

const PermissionsContext = createContext();

// Proveedor de permisos para envolver toda la aplicación
export const PermissionsProvider = ({ children }) => {
    const [permissions, setPermissions] = useState([]);

    useEffect(() => {
        const handleStorageChange = () => {
            // Obtener permisos desde el token actualizado en localStorage
            const permissionsFromToken = getPermissions(); 
            setPermissions(permissionsFromToken); // Actualizar el estado con los permisos
        };

        // Escuchar los cambios en el localStorage
        window.addEventListener('storage', handleStorageChange);

        // Llamar la función una vez cuando el componente se monta
        handleStorageChange();

        // Limpiar el listener cuando el componente se desmonta
        return () => {
            window.removeEventListener('storage', handleStorageChange);
        };
    }, []); // Solo se ejecuta una vez cuando el componente se monta

    return (
        <PermissionsContext.Provider value={permissions}>
            {children}
        </PermissionsContext.Provider>
    );
};

// Hook para usar los permisos en otros componentes
export const usePermissions = () => {
    return useContext(PermissionsContext);
};
