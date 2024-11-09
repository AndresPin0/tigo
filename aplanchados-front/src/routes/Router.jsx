import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import Landing from "../pages/Landing";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Home from "../pages/Home"; 

const routes = createRoutesFromElements(
    <>
        <Route path="/" element={<Landing />} >
        <Route index element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} /> 
        </Route>
    </>
);

export const router = createBrowserRouter(routes, { basename: '/' });
