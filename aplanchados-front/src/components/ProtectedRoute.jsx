import { useSelector } from 'react-redux';
import { Navigate } from 'react-router-dom';
import { Typography } from '@mui/material';

const ProtectedRoute = ({ requiredPermission, children }) => {
  const permissions = useSelector(state => state.auth.permissions);
  
  // Handle case when permissions are still loading or not yet set
  if (permissions === null || permissions.length === 0) {
    return <Typography variant="h6">Cargando permisos...</Typography>;
  }

  if (!permissions.includes(requiredPermission)) {
    return <Navigate to="/home" />;
  }

  return children;
};


export default ProtectedRoute;
