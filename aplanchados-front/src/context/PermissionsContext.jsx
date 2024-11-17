import React, { createContext, useContext, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setPermissions } from '../slices/authSlice'; // Importar las acciones de Redux
import { getPermissions } from '../services/authService'; // Obtener los permisos desde el token

const PermissionsContext = createContext();

export const PermissionsProvider = ({ children }) => {
  const dispatch = useDispatch();
  const permissions = useSelector(state => state.auth.permissions); // Obtener permisos desde el store de Redux

  useEffect(() => {
    // Obtener permisos desde el token y actualizar el estado de Redux
    const permissionsFromToken = getPermissions();
    dispatch(setPermissions(permissionsFromToken));
  }, [dispatch]);

  return (
    <PermissionsContext.Provider value={permissions}>
      {children}
    </PermissionsContext.Provider>
  );
};

export const usePermissions = () => {
  return useContext(PermissionsContext);
};
