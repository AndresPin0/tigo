import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../slices/authSlice';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; // Usamos el almacenamiento local del navegador

// Configuración de persistencia
const persistConfig = {
  key: 'root',
  storage,
};

const persistedReducer = persistReducer(persistConfig, authReducer);

export const store = configureStore({
  reducer: {
    auth: persistedReducer,
  },
});

export const persistor = persistStore(store); // Crear el persistor
