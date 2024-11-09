import { axiosInstance } from "./axios";

const REGISTER_URL='auth/register';
const AUTHENTICATE_URL='auth/authenticate';

const register= async function (data){
    return axiosInstance.post(REGISTER_URL,data);
};

const authenticate= async function(data){
    const response = await axiosInstance.post(AUTHENTICATE_URL, data); 
    const token = response.data.access_token; 
    localStorage.setItem('access_token', token); 
    alert(token);
};


export {register,authenticate};
