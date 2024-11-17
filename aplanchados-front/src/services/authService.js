import { axiosInstance } from "./axios";
import { jwtDecode } from 'jwt-decode';

const REGISTER_URL = 'auth/register';
const AUTHENTICATE_URL = 'auth/authenticate';

const getUserIdFromToken = () => {
    const token = localStorage.getItem('access_token');
    if (!token) return null;
    const decoded = jwtDecode(token);
    return decoded.sub ? Number(decoded.sub) : null;
};

const register = async function (data) {
    localStorage.setItem('access_token', ''); 
    return axiosInstance.post(REGISTER_URL, data);
};

const decodeToken = (token) => {
    try {
        const decoded = jwtDecode(token);
        return decoded;
    } catch (error) {
        return null;
    }
};

const getPermissions = () => {
  const token = localStorage.getItem('access_token');
  if (!token) return [];  // Return an empty array if no token
  const decoded = decodeToken(token);
  console.log('decoded:', decoded);

  // Ensure permissions is always an array, even if it's undefined in the token
  return Array.isArray(decoded?.permissions) ? decoded.permissions : [];
};

const authenticate = async function (data) {
    localStorage.setItem('access_token', ''); 
    const response = await axiosInstance.post(AUTHENTICATE_URL, data);
    const token = response.data.access_token;
    localStorage.setItem('access_token', token); 
    const decoded = jwtDecode(token);
    localStorage.setItem('userId', decoded.sub);
    return token;
};

export { register, authenticate, getPermissions, getUserIdFromToken };
