import { Link, useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";

const Navbar = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const { token, role } = useSelector((state) => state.auth);
  const cartItems = useSelector((state) => state.cart.items);

  const logoutHandler = () => {
    dispatch({ type: "auth/logout" });
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <Link to="/" className="logo">Amazon</Link>
      </div>

      <div className="navbar-right">
        {!token && (
          <>
            <Link to="/login">Login</Link>
            <Link to="/signup">Signup</Link>
          </>
        )}

        {token && role === "SELLER" && (
          <Link to="/seller">Seller Dashboard</Link>
        )}
        
        {token && (
          <>
            <Link to="/cart">Cart ({cartItems.length})</Link>
            <button className="logout-btn" onClick={logoutHandler}>
              Logout
            </button>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;

