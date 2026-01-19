import { useSelector, useDispatch } from "react-redux";
import { removeFromCart } from "../features/cart/cartSlice";
import { Link } from "react-router-dom";

export default function Cart() {
  const items = useSelector(state => state.cart.items);
  const dispatch = useDispatch();

  const total = items.reduce((sum, i) => sum + i.price, 0);

  return (
    <div className="page">
      <h2>Your Cart</h2>

      {items.length === 0 && <p>Your cart is empty</p>}

      {items.map(item => (
        <div className="cart-item" key={item.id}>
          <img src={item.image} />
          <div>
            <h4>{item.title}</h4>
            <p>₹{item.price}</p>
            <button onClick={() => dispatch(removeFromCart(item.id))}>
              Remove
            </button>
          </div>
        </div>
      ))}

      {items.length > 0 && (
        <>
          <h3>Total Amount: ₹{total}</h3>
          <Link to="/checkout">
            <button className="buy-btn">Proceed to Buy</button>
          </Link>
        </>
      )}
    </div>
  );
}
