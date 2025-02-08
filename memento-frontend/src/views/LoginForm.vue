<script setup lang="ts">
import { ref } from "vue";
import { useAuthStore } from "@/stores/auth";

const authStore = useAuthStore();
const username = ref("");
const password = ref("");
const errorMessage = ref("");

const login = async () => {
  try {
    await authStore.login({username: username.value, password: password.value});
  } catch (error) {
    errorMessage.value = "Invalid credentials.";
  }
};
</script>

<template>
  <div class="auth-container">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input type="text" v-model="username" placeholder="Username" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    <router-link to="/register">Noch kein Konto? Registrieren</router-link>
  </div>
</template>

<style scoped>
.auth-container {
  max-width: 300px;
  margin: auto;
  text-align: center;
}
input {
  width: 100%;
  margin: 8px 0;
  padding: 10px;
}
button {
  width: 100%;
  padding: 10px;
  background: #1e88e5;
  color: white;
  border: none;
}
.error {
  color: red;
}
</style>
