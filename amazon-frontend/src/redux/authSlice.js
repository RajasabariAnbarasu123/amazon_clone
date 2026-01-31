import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  token: null,
  role: null,
  userId: null,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    login(state, action) {
      state.token = action.payload.token;
      state.role = action.payload.role;
      state.userId = action.payload.userId;
    },
    logout(state) {
      state.token = null;
      state.role = null;
      state.userId = null;
    },
  },
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;
