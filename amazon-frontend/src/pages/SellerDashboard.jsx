import { useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";

const SellerDashboard = () => {
  const { token } = useSelector((state) => state.auth);

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [image, setImage] = useState("");

  const submitHandler = async (e) => {
    e.preventDefault();

    try {
      await axios.post(
        "http://localhost:8080/products/add/1",
        { name, description, price, image },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      alert("Product added");
      setName("");
      setDescription("");
      setPrice("");
      setImage("");
    } catch {
      alert("Failed to add product");
    }
  };

  return (
    <form onSubmit={submitHandler}>
      <h2>Add Product</h2>

      <input placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
      <input placeholder="Description" value={description} onChange={(e) => setDescription(e.target.value)} />
      <input placeholder="Price" value={price} onChange={(e) => setPrice(e.target.value)} />
      <input placeholder="Image URL" value={image} onChange={(e) => setImage(e.target.value)} />

      <button type="submit">Add Product</button>
    </form>
  );
};

export default SellerDashboard;
