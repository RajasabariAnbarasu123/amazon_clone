import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Signup = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("USER");

  const navigate = useNavigate();

  const submitHandler = async (e) => {
    e.preventDefault();

    try {
      await axios.post("http://localhost:8080/auth/signup", {
        username,
        email,
        password,
        role,
      });

      alert("Signup successful. Please login.");
      navigate("/login");
    } catch (err) {
      alert(
        err.response?.data || "Signup failed. Email may already exist."
      );
    }
  };

  return (
    <form onSubmit={submitHandler}>
      <h2>Signup</h2>

      <input
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        required
      />

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

      <select value={role} onChange={(e) => setRole(e.target.value)}>
        <option value="USER">User</option>
        <option value="SELLER">Seller</option>
      </select>

      <button type="submit">Create Account</button>
    </form>
  );
};

export default Signup;
