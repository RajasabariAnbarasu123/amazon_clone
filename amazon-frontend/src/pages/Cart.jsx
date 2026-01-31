import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import CartItem from "../components/CartItem";

const Cart = () => {
  const items = useSelector((state) => state.cart.items);
  const navigate = useNavigate();

  const total = items.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  return (
    <div className="cart-page">
      <h2 className="page-title">Your Cart</h2>

      {items.length === 0 ? (
        <p className="empty-text">🛒 Cart is empty</p>
      ) : (
        <>
          {items.map((item) => (
            <CartItem key={item.id} item={item} />
          ))}

          <div className="cart-summary">
            <h3>Total: ₹{total}</h3>
            <button onClick={() => navigate("/checkout")}>
              Buy Now
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default Cart;
