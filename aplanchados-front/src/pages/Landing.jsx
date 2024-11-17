import { Outlet } from "react-router-dom";
import HomeHeader from "../components/NavBar";
export default function Landing() {
    return (
        <div>
            <HomeHeader />
            <Outlet/>
        </div>
    );
}