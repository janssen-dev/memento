import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    token: localStorage.getItem("token") || "",
    isAuthenticated: !!localStorage.getItem("token"),
  }),

  actions: {
    async login(username: string, password: string) {
      const response = await axios.post("/api/auth/login", { username, password });
      this.token = response.data.token;
      this.isAuthenticated = true;
      localStorage.setItem("token", this.token);
      router.push("/");
    },

    async register(username: string, password: string) {
      await axios.post("/api/auth/register", { username, password });
      await this.login(username, password);
    },

    logout() {
      this.token = "";
      this.isAuthenticated = false;
      localStorage.removeItem("token");
      router.push("/login");
    },
  },
});
