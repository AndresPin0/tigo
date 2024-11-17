import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import Landing from "../pages/Landing";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Home from "../pages/Home"; 
import ManagerPage from "../pages/ManagerPage";
import ExpensePage from "../pages/ExpensePage";
import IncomePage from "../pages/IncomePage";
import ReportPage from "../pages/ReportPage";
import MonthlyReportPage from '../pages/MonthReportPage'; 
import ProtectedRoute from '../components/ProtectedRoute';  
import NavBar from '../components/NavBar';
import BackArrow from '../components/BackArrow';

const routes = createRoutesFromElements(
<<<<<<< HEAD
    <>
        <Route path="/" element={<Landing />} >
        <Route index element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} /> 
        </Route>
     <Route 
     path="manager" 
     element={
       <ProtectedRoute requiredPermission="MANAGE SYSTEM">
         <ManagerPage />
       </ProtectedRoute>
     } 
   />
   <Route 
     path="expense" 
     element={
       <ProtectedRoute requiredPermission="ADD EXPENSE">
         <ExpensePage />
       </ProtectedRoute>
     } 
   />
   <Route 
     path="income/create" 
     element={
       <ProtectedRoute requiredPermission="ADD INCOME">
         <IncomePage />
       </ProtectedRoute>
     } 
   />
   <Route 
     path="report/upload-excel" 
     element={
       <ProtectedRoute requiredPermission="GENERATE REPORT">
         <ReportPage />
       </ProtectedRoute>
     } 
   />
   <Route 
     path="reports/monthly" 
     element={
       <ProtectedRoute requiredPermission="GENERATE REPORT">
         <MonthlyReportPage />
       </ProtectedRoute>
     } 
   />
 </>
=======
  <>
    <Route path="/" element={<Landing />} >
      <Route index element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/home" element={<Home />} /> 
    </Route>
    <Route 
      path="manager" 
      element={
        <ProtectedRoute requiredPermission="MANAGE SYSTEM">
          <NavBar/>
          <BackArrow />
          <ManagerPage />
        </ProtectedRoute>
      } 
    />
    <Route 
      path="expense" 
      element={
        <ProtectedRoute requiredPermission="ADD EXPENSE">
          <NavBar/>
          <BackArrow /> 
          <ExpensePage />
        </ProtectedRoute>
      } 
    />
    <Route 
      path="income/create" 
      element={
        <ProtectedRoute requiredPermission="ADD INCOME">
          <NavBar/>
          <BackArrow />
          <IncomePage />
        </ProtectedRoute>
      } 
    />
    <Route 
      path="report/upload-excel" 
      element={
        <ProtectedRoute requiredPermission="GENERATE REPORT">
          <NavBar/>
          <BackArrow /> 
          <ReportPage />
        </ProtectedRoute>
      } 
    />
  </>
>>>>>>> 0a47ddb513176aad256d0b6fceead9f223f6731f
);

export const router = createBrowserRouter(routes, { basename: '/' });
