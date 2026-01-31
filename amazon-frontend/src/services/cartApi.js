import API from "./api";

// ADD / UPDATE CART
export const addToCartAPI = (userId, productId, quantity = 1) =>
  API.post("/cart/add", null, {
    params: { userId, productId, quantity },
  });

// GET USER CART
export const getCartAPI = (userId) =>
  API.get(`/cart/${userId}`);

// REMOVE ITEM
export const removeCartItemAPI = (cartId) =>
  API.delete(`/cart/item/${cartId}`);

// CLEAR CART
export const clearCartAPI = (userId) =>
  API.delete(`/cart/clear/${userId}`);
