import Home from "../pages/Home";
import Register from "../pages/Register";
import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import HomeHeader from "../components/HomeHeader";
const routes = createRoutesFromElements(
    <>
    <Route path="/" element={<Home />} >
    <Route index element={<HomeHeader />} />
    </Route>

    <Route path="/register" element={<Register />} />
    </>
);

export const router = createBrowserRouter(routes, { basename: '/'});