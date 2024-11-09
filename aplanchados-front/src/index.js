import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import { router } from './routes/Router';
import { PermissionsProvider } from './context/PermissionsContext'; 

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>
    <PermissionsProvider> 
      <RouterProvider router={router} />
    </PermissionsProvider>
  </React.StrictMode>
);
