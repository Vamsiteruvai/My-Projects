import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ReactDOM from 'react-dom';
import './Order_success.css';

const Order_success = () => {
    const navigate = useNavigate();
    const [rating, setRating] = useState(0);
    const user_id = localStorage.getItem("user_id");

    const handleRating = (index) => {
        setRating(index + 1);
    };

    const postRating = async () => {
        if (!user_id) {
            console.error("User ID not found in localStorage.");
            return;
        }
        try {
            const res = await fetch(`http://127.0.0.1:4500/postrating/${user_id}`, {
                method: 'PATCH',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ rating })
            });

            const data = await res.json();
            // console.log("Rating posted:", data);
            return data;
        } catch (error) {
            console.error("Error posting rating:", error);
        }
    };

    // const postRatingToProduct = async () => {
    //     if (!user_id) {
    //         console.error("User ID not found in localStorage.");
    //         return;
    //     }
    //     try {
    //         const res = await fetch(`http://127.0.0.1:4500/postrating/${user_id}`, {
    //             method: 'PATCH',
    //             headers: {
    //                 "Content-Type": "application/json"
    //             },
    //             body: JSON.stringify({ rating })
    //         });

    //         const data = await res.json();
    //         // console.log("Rating posted:", data);
    //         return data;
    //     } catch (error) {
    //         console.error("Error posting rating:", error);
    //     }
    // };

    const getRating = async () => {
        try {
            const res = await fetch('http://127.0.0.1:4500/getusers');
            const data = await res.json();
            // console.log("Fetched user ratings:", data);
            return data;
        } catch (error) {
            console.error("Error fetching ratings:", error);
        }
    };

    

    const handleFun = async () => {
        await postRating();  // Ensure rating is updated first
        setTimeout(async () => {
            await getRating();
        }, 2000);
        alert("Rating Submitted Successfully")
    };

    return ReactDOM.createPortal(
        <div className="order-success-container">
            <div className="success-message">
                <h2>🎉 Order Placed Successfully! 🎉</h2>
                <p>Thank you for shopping with us. Your order has been confirmed.</p>
            </div>

            {/* Rating Section */}
            <div className="rating-section">
                <h3>Rate Your Experience</h3>
                <div className="stars">
                    {[...Array(5)].map((_, index) => (
                        <span 
                            key={index} 
                            className={`material-symbols-outlined ${index < rating ? 'active' : ''}`} 
                            onClick={() => handleRating(index)}
                        >
                            star
                        </span>
                    ))}
                </div>
                <button className="submit-rating" onClick={handleFun}>Submit Rating</button>
            </div>

            {/* Back to Shop Button */}
            <button className="back-to-home" onClick={() => navigate('/products')}>
                Back to Shop
            </button>
        </div>,
        document.body
    );
};

export default Order_success;
