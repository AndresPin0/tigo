import { createSlice } from '@reduxjs/toolkit';
import { getPermissions, getUserIdFromToken } from '../services/authService';

const initialState = {
  token: localStorage.getItem('access_token') || '',
  permissions: getPermissions(),
  userId: getUserIdFromToken(),
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setToken: (state, action) => {
      state.token = action.payload;
      localStorage.setItem('access_token', action.payload); // Persistir el token en localStorage
    },
    setPermissions: (state, action) => {
      state.permissions = action.payload;
    },
    setUserId: (state, action) => {
      state.userId = action.payload;
    },
    clearAuth: (state) => {
      state.token = '';
      state.permissions = [];
      state.userId = null;
      localStorage.removeItem('access_token');
    },
  },
});

export const { setToken, setPermissions, setUserId, clearAuth } = authSlice.actions;

export default authSlice.reducer;
