import { Outlet } from "react-router-dom";
import HomeHeader from "../components/HomeHeader";
export default function Landing() {
    return (
        <div>
            <HomeHeader />
            <Outlet/>
        </div>
    );
}