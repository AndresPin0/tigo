import Home from "../pages/Home";
import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
const routes = createRoutesFromElements(
    <Route path="/" element={<Home />}>
        
    </Route>
)

export const router = createBrowserRouter(routes, { basename: '/'});