import { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const submitHandler = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/auth/login", {
        email,
        password,
      });

      const authData = {
        token: res.data.token,
        role: res.data.role,
        userId: res.data.userId,
      };

      // Save to localStorage
      localStorage.setItem("auth", JSON.stringify(authData));

      // Update Redux
      dispatch({
        type: "auth/login",
        payload: authData,
      });

      navigate("/");
    } catch (err) {
      alert("Invalid email or password");
    }

  };

  return (
    <form onSubmit={submitHandler}>
      <h2>Login</h2>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
      />
      <button type="submit">Login</button>
    </form>
  );
};

export default Login;
