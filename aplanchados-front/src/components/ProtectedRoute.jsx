import React from 'react';
import { usePermissions } from '../context/PermissionsContext';
import Typography from '@mui/material/Typography';

const ProtectedRoute = ({ children, requiredPermission }) => {
  const permissions = usePermissions();
  if (!permissions.includes(requiredPermission)) {
    return <Typography variant="h6" color="error">No tienes permiso para acceder a esta página.</Typography>;
  }

  return children;
};

export default ProtectedRoute;
