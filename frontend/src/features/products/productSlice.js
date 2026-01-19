import { createSlice } from "@reduxjs/toolkit";

const products = Array.from({ length: 30 }, (_, i) => ({
  id: i + 1,
  title: `Product ${i + 1}`,
  price: 999 + i * 100,
  image: `https://picsum.photos/seed/amazon${i + 1}/300/300`
}));

const productSlice = createSlice({
  name: "products",
  initialState: { list: products },
  reducers: {}
});

export default productSlice.reducer;
