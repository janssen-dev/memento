import { createRouter, createWebHistory } from "vue-router"
import { useAuthStore } from "@/stores/auth"
import LoginForm from "@/views/LoginForm.vue"
import RegisterForm from "@/views/RegisterForm.vue"
import HomeView from '@/views/HomeView.vue'

const routes = [
  { path: "/login", component: LoginForm },
  { path: "/register", component: RegisterForm },
  { path: "/", component: HomeView, meta: { requiresAuth: true } },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next("/login");
  } else {
    next();
  }
});

export default router
