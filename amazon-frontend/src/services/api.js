import axios from "axios";


const API = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

API.interceptors.request.use(
  (config) => {
    const authData = JSON.parse(localStorage.getItem("auth"));

    if (authData && authData.token) {
      config.headers.Authorization = `Bearer ${authData.token}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

 //AUTH APIs

export const loginUser = (data) => API.post("/auth/login", data);

export const signupUser = (data) => API.post("/auth/signup", data);

 //PRODUCT APIs

export const fetchProducts = () => API.get("/products");

export const addProduct = (sellerId, data) =>
  API.post(`/products/add/${sellerId}`, data);

/**
 * ======================
 * CART APIs
 * ======================
 */

export const addToCart = (userId, productId, quantity) =>
  API.post(`/cart/add?userId=${userId}&productId=${productId}&quantity=${quantity}`);

export const getCart = (userId) =>
  API.get(`/cart/${userId}`);

export const removeCartItem = (cartId) =>
  API.delete(`/cart/remove/${cartId}`);

/**
 * ======================
 * ORDER APIs
 * ======================
 */

export const placeOrder = (userId) =>
  API.post(`/orders/place/${userId}`);

export const getUserOrders = (userId) =>
  API.get(`/orders/user/${userId}`);

export default API;
