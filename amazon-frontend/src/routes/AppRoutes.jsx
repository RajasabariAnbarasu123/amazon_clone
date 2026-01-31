import { lazy, Suspense } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import { useSelector } from "react-redux";

// Lazy loaded pages
const Home = lazy(() => import("../pages/Home"));
const Login = lazy(() => import("../pages/Login"));
const Signup = lazy(() => import("../pages/Signup"));
const SellerDashboard = lazy(() => import("../pages/SellerDashboard"));
const Cart = lazy(() => import("../pages/Cart"));
const Checkout = lazy(() => import("../pages/Checkout"));
const OrderSuccess = lazy(() => import("../pages/OrderSuccess"));

const AppRoutes = () => {
  const { token, role } = useSelector((state) => state.auth);

  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Routes>

        {/* Public Routes */}
        <Route path="/" element={<Home />} />
        <Route path="/login" element={!token ? <Login /> : <Navigate to="/" />} />
        <Route path="/signup" element={!token ? <Signup /> : <Navigate to="/" />} />

        {/* Protected User Routes */}
        <Route
          path="/cart"
          element={token ? <Cart /> : <Navigate to="/login" />}
        />

        <Route
          path="/checkout"
          element={token ? <Checkout /> : <Navigate to="/login" />}
        />

        <Route
          path="/success"
          element={token ? <OrderSuccess /> : <Navigate to="/login" />}
        />

        {/* Protected Seller Route */}
        <Route
          path="/seller"
          element={
            token && role === "SELLER" ? (
              <SellerDashboard />
            ) : (
              <Navigate to="/" />
            )
          }
        />

        {/* Fallback */}
        <Route path="*" element={<Navigate to="/" />} />

      </Routes>
    </Suspense>
  );
};

export default AppRoutes;
