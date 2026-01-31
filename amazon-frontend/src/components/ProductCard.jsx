import { useDispatch, useSelector } from "react-redux";
import { addItem } from "../redux/cartSlice";
import { addToCartAPI } from "../services/cartApi";

const ProductCard = ({ product }) => {
  const dispatch = useDispatch();
  const token = useSelector((state) => state.auth.token);
  const userId = useSelector((state) => state.auth.userId);

  const addToCartHandler = async () => {
    if (!token || !userId) {
      alert("Please login to add items to cart");
      return;
    }

    try {
      // Call backend API to save to database
      await addToCartAPI(userId, product.id, 1);
      
      // Update Redux state
      dispatch(addItem({
        id: product.id,
        name: product.name,
        price: product.price,
        image: product.image,
        quantity: 1
      }));
      
      alert("Added to cart!");
    } catch (error) {
      console.error("Error adding to cart:", error);
      alert("Failed to add to cart");
    }
  };

  return (
    <div className="product-card">
      <img src={product.image} alt={product.name} />

      <h3>{product.name}</h3>
      <p className="price">₹{product.price}</p>

      <button onClick={addToCartHandler}>Add to Cart</button>
    </div>
  );
};

export default ProductCard;
