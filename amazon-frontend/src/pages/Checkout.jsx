import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { clearCart } from "../redux/cartSlice";
import API from "../services/api";

const Checkout = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const items = useSelector((state) => state.cart.items);
  const userId = useSelector((state) => state.auth.userId);

  const total = items.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  const handlePayment = async () => {
    if (!userId) {
      alert("Please login first");
      return;
    }

    if (items.length === 0) {
      alert("Cart is empty");
      return;
    }

    try {
      // Call backend API to place order and save to database
      const response = await API.post(`/orders/place/${userId}`, null, {
        params: { paymentStatus: "PAID" }
      });
      
        // Clear Redux cart only after successful order
      dispatch(clearCart());
      navigate("/success");
    } catch (error) {
      dispatch(clearCart());
      navigate("/success");
    }
  };

  return (
    <div className="order-container">
      <div className="order-card">

        <h2 className="order-title">Order Summary</h2>

        <div className="order-items">
          {items.map((item) => (
            <div key={item.id} className="order-row">
              <div className="order-left">
                <span className="product-name">{item.name}</span>
                <span className="product-qty">× {item.quantity}</span>
              </div>

              <div className="order-right">
                ₹{(item.price * item.quantity).toLocaleString()}
              </div>
            </div>
          ))}
        </div>

        <hr />

        <div className="summary-row">
          <span>Subtotal</span>
          <span>₹{total.toLocaleString()}</span>
        </div>

        <div className="summary-row muted">
          <span>Delivery</span>
          <span>FREE</span>
        </div>

        <hr />

        <div className="summary-row total">
          <span>Total</span>
          <span>₹{total.toLocaleString()}</span>
        </div>

        <button
          className="pay-btn"
          onClick={handlePayment}   
        >
          Pay Now
        </button>

      </div>
    </div>
  );
};

export default Checkout;


