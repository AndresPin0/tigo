import { axiosInstance } from "./axios";
const MANAGER_URL = 'manager';
export async function getUsers() {
  const users = await axiosInstance.get(MANAGER_URL + '/users');
  return users.data;
}

export async function getPermissions() {
  const permissions = await axiosInstance.get(MANAGER_URL + '/permissions');
  return permissions.data;
}

export async function getRoles() {
  const roles = await axiosInstance.get(MANAGER_URL + '/roles');
  return roles.data;
}
export const editUserRole = async (userId, role) => {
  const data = { "id": userId, "role": role };
  try {
    const response = await axiosInstance.post(MANAGER_URL + '/edit-user-role', data);
    console.log(response);
    return response.data;
  } catch (error) {
    console.error("Error al editar el rol del usuario:", error);
    throw error;
  }
};

export async function updateRolePermissions(data) {
  try {
    const response = await axiosInstance.post(MANAGER_URL + '/update-role', data);
    console.log(response);
    return response.data;
  } catch (error) {
    console.error("Error al editar el rol del usuario:", error);
    throw error;
  }
}

export async function removeRolePermissions(data){
  try {
    const response = await axiosInstance.post(MANAGER_URL + '/remove-role', data);
    console.log(response);
    return response.data;
  } catch (error) {
    console.error("Error al editar el rol del usuario:", error);
    throw error;
  }
}

export async function addRole(data){

  try {
    const response = await axiosInstance.post(MANAGER_URL + '/remove-role', data);
    console.log(response);
    return response.data;
  } catch (error) {
    console.error("Error al editar el rol del usuario:", error);
    throw error;
  }
}

 
