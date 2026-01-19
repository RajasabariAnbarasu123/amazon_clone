import { loadStripe } from "@stripe/stripe-js";
import { useSelector } from "react-redux";

const stripePromise = loadStripe("pk_test_51SqzkcDGS4Tsnohf0J4Vmfr7jpfoICtNd4rHDrJtpupvJUZb5u9wueJeqEa1eMDLSW4SAK1Yc9dveXWoTxTTJMX0000ldUZQbW");

export default function Checkout() {
  const items = useSelector(state => state.cart.items);
  const total = items.reduce((sum, i) => sum + i.price, 0);

  const handlePayment = async () => {
    const stripe = await stripePromise;

    // TEMPORARY Stripe Checkout Session (Frontend-only demo)
    await stripe.redirectToCheckout({
      lineItems: items.map(item => ({
        price_data: {
          currency: "inr",
          product_data: {
            name: item.title,
          },
          unit_amount: item.price * 100,
        },
        quantity: 1,
      })),
      mode: "payment",
      successUrl: window.location.origin + "/success",
      cancelUrl: window.location.origin + "/cart",
    });
  };

  return (
    <div className="page">
      <h2>Order Summary</h2>

      {items.map(item => (
        <p key={item.id}>
          {item.title} - ₹{item.price}
        </p>
      ))}

      <h3>Total Payable: ₹{total}</h3>

      <button className="buy-btn" onClick={handlePayment}>
        Pay with Stripe
      </button>
    </div>
  );
}
