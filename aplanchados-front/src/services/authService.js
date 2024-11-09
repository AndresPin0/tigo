import { axiosInstance } from "./axios";

const REGISTER_URL='auth/register';

const register= async function (data){
    return axiosInstance.post(REGISTER_URL,data);
};
export {register};
