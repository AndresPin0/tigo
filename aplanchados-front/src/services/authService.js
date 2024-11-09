import { axiosInstance } from "./axios";
import { jwtDecode } from 'jwt-decode';
const REGISTER_URL = 'auth/register';
const AUTHENTICATE_URL = 'auth/authenticate';

const register = async function (data) {
    localStorage.setItem('access_token','');
    return axiosInstance.post(REGISTER_URL, data);
};

function decodeToken(token) {
    try {
        const decoded = jwtDecode(token);
        return decoded;
    } catch (error) {
        return null;
    }
}

const  getPermissions=()=> {
    return decodeToken(localStorage.getItem('access_token')).permissions || '';
};

const authenticate = async function (data) {
    localStorage.setItem('access_token','');
    const response = await axiosInstance.post(AUTHENTICATE_URL, data);
    const token = response.data.access_token;
    localStorage.setItem('access_token', token);
};


export { register, authenticate, getPermissions };
