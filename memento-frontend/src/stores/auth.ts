import {defineStore} from "pinia";
import {register, login, logout} from "@/api/auth-api";
import router from "@/router";
import {AxiosError} from "axios";

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
        const {token} = await register(credentials.username, credentials.password);

        this.token = token;
        this.username = credentials.username;

        localStorage.setItem("token", token);
        localStorage.setItem("username", credentials.username);

        router.push("/");
      } catch (error) {
        if (error instanceof AxiosError && error.response?.status === 409) {
          throw new Error("Username already taken");
        } else {
          console.error("Registration failed", error);
          throw new Error("Registration failed");
        }
      }
    },

    async login(credentials: { username: string; password: string }) {
      try {
        const {token} = await login(credentials.username, credentials.password);

        this.token = token;
        this.username = credentials.username;

        localStorage.setItem("token", this.token);
        localStorage.setItem("username", this.username);

        router.push("/");
      } catch (error) {
        if (error instanceof AxiosError && error.response?.status === 403) {
          throw new Error("Incorrect username or password");
        } else {
          console.error("Login failed:", error);
          throw new Error("Login failed");
        }
      }
    },

    async logout() {
      try {
        await logout();

        this.token = null;
        this.username = null;
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        router.push("/login");
      } catch (error) {
        console.error("Logout failed:", error);
        throw new Error("Logout failed");
      }
    },
  },
});
