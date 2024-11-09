import axios from 'axios';




const axiosInstance = axios.create({
    baseURL: process.env.REACT_APP_API,
    headers: {
        Accept: 'application/json',
    },
    validateStatus: function validateStatus(status) {
        if (status >= 200 && status < 300) {
            return true;
        } else {
            return false;
        }
    }
});
axiosInstance.interceptors.request.use(

function (config){
    const token = localStorage.getItem('access_token');
    if(token){
        console.log("Alo?");
        config.headers['Authorization']=`Bearer ${token}`;
    }
    return config;
}
);

export { axiosInstance };