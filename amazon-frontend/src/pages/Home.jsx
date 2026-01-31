import { useEffect, useState } from "react";
import { fetchProducts } from "../services/api";
import ProductCard from "../components/ProductCard";

const Home = () => {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    try {
      const response = await fetchProducts();
      setProducts(response.data);
      setError("");
    } catch (err) {
      console.error(err);
      setError("Failed to load products");
    }
  };

  return (
    <div className="home-container">
      <h2 className="page-title">Products</h2>

      {error && <p className="error">{error}</p>}

      <div className="grid">
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </div>
  );
};

export default Home;
