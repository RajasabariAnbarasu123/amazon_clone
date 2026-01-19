import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/auth/authSlice";
import cartReducer from "../features/cart/cartSlice";
import productReducer from "../features/products/productSlice";

export default configureStore({
  reducer: {
    auth: authReducer,
    cart: cartReducer,
    products: productReducer
  }
});
