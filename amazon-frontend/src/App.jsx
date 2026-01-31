import { useEffect } from "react";
import { useDispatch } from "react-redux";
import Navbar from "./components/Navbar";
import AppRoutes from "./routes/AppRoutes";
import { login } from "./redux/authSlice";
import "./styles/main.css";

function App() {
  const dispatch = useDispatch();

  // Restore auth from localStorage on app load
  useEffect(() => {
    const authData = localStorage.getItem("auth");
    if (authData) {
      try {
        const parsed = JSON.parse(authData);
        dispatch(login(parsed));
      } catch (error) {
        console.error("Error restoring auth:", error);
      }
    }
  }, [dispatch]);

  return (
    <>
      <Navbar />
      <AppRoutes />
    </>
  );
}

export default App;
