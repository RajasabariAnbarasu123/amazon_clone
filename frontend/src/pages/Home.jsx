import { useSelector } from "react-redux";
import ProductCard from "../components/ProductCard";

export default function Home() {
  const products = useSelector(state => state.products.list);

  return (
    <div className="grid">
      {products.map(p => (
        <ProductCard key={p.id} product={p} />
      ))}
    </div>
  );
}
