import api from "./axios-api";

export const register = async (username: string, password: string) => {
  try {
    const response = await api.post("/auth/register", {username, password});
    return response.data;
  } catch (error) {
    console.error("Failed to register", error);
    throw error;
  }
};

export const login = async (username: string, password: string) => {
  try {
    const response = await api.post("/auth/login", {username, password});
    return response.data as { token: string };
  } catch (error) {
    console.error("Failed to login", error);
    throw error;
  }
};

export const logout = async () => {
  try {
    await api.post("/auth/logout");
  } catch (error) {
    console.error("Failed to logout", error);
    throw error;
  }
};
