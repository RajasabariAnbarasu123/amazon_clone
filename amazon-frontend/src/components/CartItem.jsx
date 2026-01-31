import { useDispatch, useSelector } from "react-redux";
import { increaseQty, decreaseQty, removeItem } from "../redux/cartSlice";
import { removeCartItemAPI } from "../services/cartApi";

const CartItem = ({ item }) => {
  const dispatch = useDispatch();
  const userId = useSelector((state) => state.auth.userId);

  const handleIncreaseQty = () => {
    dispatch(increaseQty(item.id));
  };

  const handleDecreaseQty = () => {
    dispatch(decreaseQty(item.id));
  };

  const handleRemoveItem = async () => {
    try {
      await removeCartItemAPI(item.id);
      dispatch(removeItem(item.id));
    } catch (error) {
      console.error("Error removing item:", error);
      alert("Failed to remove item");
    }
  };

  return (
    <div className="cart-item">
      {/* LEFT */}
      <div className="cart-left">
        <img src={item.image} alt={item.title} />

        <div className="cart-info">
          <h4 className="item-title">{item.name}</h4>

          <div className="quantity-controls">
            <button onClick={handleDecreaseQty}>-</button>
            <span>{item.quantity}</span>
            <button onClick={handleIncreaseQty}>+</button>
          </div>

          <button className="remove-btn" onClick={handleRemoveItem}>
            Remove
          </button>
        </div>
      </div>

      {/* RIGHT */}
      <div className="cart-right">
        <div className="price">Price: ₹{item.price}</div>
        <div className="item-total">Sub-Total: 
          ₹{item.price * item.quantity}
        </div>
      </div>
    </div>
  );
};

export default CartItem;
