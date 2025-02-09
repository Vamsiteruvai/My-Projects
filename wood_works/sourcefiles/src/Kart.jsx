import { useEffect, useState } from 'react';
import './Kart.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Order_success from './Order_success';

const Kart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [success, setSuccess] = useState(false);
  const navigate=useNavigate();

  useEffect(() => {
    const user_id = localStorage.getItem("user_id");
    axios.get(`http://127.0.0.1:4500/getcartitems/${user_id}`)
      .then(response => {
        const updatedCart = response.data.map(item => ({
          ...item,
          quantity: item.quantity || 1,  // Default quantity to 1 if undefined
          total_price: (item.price || 0) * (item.quantity || 1) // Default values to prevent NaN
        }));
        setCartItems(updatedCart);
      })
      .catch(error => console.error("Error fetching cart items:", error));
  }, []);

  //  Update Quantity in Frontend Only
  const updateQuantity = (product_id, newQuantity) => {
    if (newQuantity < 1) newQuantity = 1; // Prevent negative or zero values

    const updatedCart = cartItems.map(item =>
      item.product_id === product_id
        ? { ...item, quantity: newQuantity, total_price: item.price * newQuantity }
        : item
    );
    setCartItems(updatedCart);
  };

  //  Remove Cart Item
  const removeCartItem = async (item) => {
    const user_id = localStorage.getItem("user_id");
    try {
      await fetch(`http://127.0.0.1:4500/removeCartItem/${user_id}`, {
        method: 'DELETE',
        body: JSON.stringify({ product_id: item.product_id }),
        headers: { "Content-Type": "application/json" },
      });

      // Remove item from frontend state
      setCartItems(cartItems.filter(cartItem => cartItem.product_id !== item.product_id));
    } catch (error) {
      console.error("Error removing item:", error);
    }
  };

  //  Calculate Overall Cart Price
  const totalCartPrice = cartItems.reduce((acc, item) => acc + (item.total_price || 0), 0);


  const placeOrder = async () => {
    try {
      const cartProd = cartItems.map(item => ({
        user_id: item.user_id,
        product_id: item.product_id,
        product_name: item.product_name,
        quantity: item.quantity,
        total_price: item.total_price
      }));

      const res = await fetch('http://127.0.0.1:4500/placeOrder', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(cartProd)
      });

      const data = await res.json();
      //console.log("Order Response:", data);

      if (res.ok) {
        //  Remove only ordered items from cart (not entire cart)
        const orderedProductIds = cartProd.map(item => item.product_id);
        setCartItems(prevCart => prevCart.filter(item => !orderedProductIds.includes(item.product_id)));

        alert("Order placed successfully!");
        setSuccess(true)
      }
    } catch (error) {
      //console.error("Error placing order:", error);
    }
  };

  const maintain=()=>{
    navigate('/');
    localStorage.removeItem("isLoggedIn")
    window.location.reload();
  }

  return (
    <div className="kart-container">
      <h1 className="kart-title">Shopping Cart</h1>
      <div className="kart-items">
        {cartItems.map((item) => (
          <div key={item.product_id} className="kart-item">
            <img src={`http://127.0.0.1:4500/uploads/${item.image_url}`} alt={item.product_name} className="kart-item-image" />
            <div className="kart-item-details">
              <p className="kart-item-name">{item.product_name}</p>
              <p className="kart-item-price">Price: ₹{item.price}</p>

              {/* Quantity Update */}
              <div className="kart-item-quantity">
                <input
                  type="number"
                  value={item.quantity}
                  className="quantity-input"
                  onChange={(e) => updateQuantity(item.product_id, Number(e.target.value))}
                  min={1} max={10}
                />
              </div>

              <p className="kart-item-total">Total: ₹{item.total_price}</p>
              <button className="remove-btn" onClick={() => removeCartItem(item)}>Remove</button>
            </div>
          </div>
        ))}
      </div>

      <div className="kart-actions">
        <p className="kart-item-total">Overall Cart: ₹{totalCartPrice}</p>
        <button className="checkout-btn" onClick={placeOrder}>Proceed to Order</button>
        <button className="checkout-btn" onClick={()=>navigate('/Products')}>Back To Shop</button>
        <button className="checkout-btn" onClick={maintain}>Logout</button>
      </div>
      {
        success&&(<Order_success/>)
      }
    </div>
  );
};

export default Kart;
