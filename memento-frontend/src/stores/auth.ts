import { defineStore } from "pinia";
import api from "@/api/axios-api";
import router from "@/router";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    token: localStorage.getItem("token"),
    username: localStorage.getItem("username"),
  }),

  getters: {
    isAuthenticated: (state): boolean => !!state.token,
  },

  actions: {
    async register(credentials: { username: string; password: string }) {
      try {
        const response = await api.post("/auth/register", credentials);
        const { token } = response.data;

        this.token = token;
        this.username = credentials.username;

        localStorage.setItem("token", token);
        localStorage.setItem("username", credentials.username);

        router.push("/");
      } catch (error) {
        console.error("Registration failed", error);
        throw error;
      }
    },

    async login(credentials: { username: string; password: string }) {
      try {
        const response = await api.post<{ token: string }>("/auth/login", credentials);
        this.token = response.data.token;
        this.username = credentials.username;

        localStorage.setItem("token", this.token);
        localStorage.setItem("username", this.username);

        router.push("/");
      } catch (error) {
        console.error("Login failed:", error);
        throw new Error("Login failed");
      }
    },

    logout() {
      this.token = null;
      this.username = null;
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      router.push("/login");
    },
  },
});
