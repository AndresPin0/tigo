import React, { createContext, useContext, useState, useEffect } from 'react';
import { getPermissions } from '../services/authService';


const PermissionsContext = createContext();

// Proveedor de permisos para envolver toda la aplicación
export const PermissionsProvider = ({ children }) => {
    const [permissions, setPermissions] = useState([]);

    useEffect(() => {
        // Obtener los permisos del token almacenado en localStorage
        const permissionsFromToken = getPermissions(); // Esta función ya extrae los permisos
        setPermissions(permissionsFromToken); // Actualizar el estado con los permisos
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
