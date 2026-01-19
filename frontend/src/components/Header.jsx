import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

export default function Header() {
  const count = useSelector(state => state.cart.items.length);

  return (
    <header className="header">
      <h2>Amazon Clone</h2>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/cart">Cart ({count})</Link>
        <Link to="/login">Login</Link>
      </nav>
    </header>
  );
}
