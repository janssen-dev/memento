<script setup>
import {ref, onMounted, computed} from "vue";
import {useRoute} from "vue-router";
import {GridStack} from "gridstack";
import "gridstack/dist/gridstack.min.css";
import {faCog, faUser} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {useAuthStore} from "@/stores/auth.ts";

const route = useRoute();
const grid = ref(null);

onMounted(() => {
  grid.value = GridStack.init({
    cellHeight: 100,
    float: true,
    resizable: {handles: "e, se, s, sw, w"},
    draggable: true,
  });
});

const authStore = useAuthStore();
const isAuthenticated = computed(() => authStore.isAuthenticated);
</script>

<template>
  <div class="app-container">
    <!-- Navbar -->
    <nav class="navbar">
      <router-link to="/" class="nav-item">Home</router-link>

      <div class="nav-right" v-if="isAuthenticated">
        <span class="username">{{ authStore.username }}</span>
        <button class="logout-button" @click="authStore.logout()">Logout</button>
        <router-link to="/settings" class="icon-button">
          <FontAwesomeIcon :icon="faCog"/>
        </router-link>
        <router-link to="/user" class="icon-button">
          <FontAwesomeIcon :icon="faUser"/>
        </router-link>
      </div>
    </nav>

    <!-- Main Content -->
    <main>
      <router-view/>
    </main>
  </div>
</template>

<style scoped>
/* Basic Reset */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.app-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 100vw;
}

/* Navbar */
.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #1e88e5;
  padding: 15px 20px;
}

.nav-item {
  color: white;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.2rem;
  padding: 8px 12px;
  border-radius: 5px;
  transition: background 0.3s;
}

.nav-item:hover {
  background: rgba(255, 255, 255, 0.2);
}

/* Navbar Right */
.nav-right {
  display: flex;
  gap: 15px;
}

.icon-button {
  color: white;
  font-size: 1.5rem;
  transition: opacity 0.3s;
}

.icon-button:hover {
  opacity: 0.7;
}

/* Main Content */
main {
  flex-grow: 1;
  padding: 20px;
  width: 100%;
}
</style>
