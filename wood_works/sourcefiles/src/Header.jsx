import { useEffect, useState } from 'react';
import './Header.css';
import { Link } from "react-router-dom";

function Header() {
    const [isCartVisible, setIsCartVisible] = useState(false);
    const [isUserLoggedIn, setIsUserLoggedIn] = useState(false);

    useEffect(() => {
        const userLogin = localStorage.getItem("isLoggedIn");
        if (userLogin === "true") {
            setIsUserLoggedIn(true);
            setIsCartVisible(true);
        } else {
            setIsUserLoggedIn(false);
            setIsCartVisible(false);
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem("isLoggedIn");
        setIsUserLoggedIn(false);
        setIsCartVisible(false);
    };

    return (
        <div className="header-container">
            <div className="header-label">
                <label>Menu</label>
            </div>
            <div className="list">
                <button className='list-btn'><Link to="/">Home</Link></button>
                
                {isUserLoggedIn ? (
                    <button className='list-btn' onClick={handleLogout}>
                        <Link to="/">Logout</Link>
                    </button>
                ) : (
                    <button className='list-btn'><Link to="/user_login">Login</Link></button>
                )}

                <button className='list-btn'><Link to="/admin_login">Admin Login</Link></button>
                <button className='list-btn'><Link to="/contact_us">Contact Us</Link></button>
                <button className='list-btn'><Link to="/about_us">About Us</Link></button>

                {isCartVisible && (
                    <span className="material-symbols-outlined list-btn">
                        <Link to="/kart">shopping_cart_checkout</Link>
                    </span>
                )}
            </div>
        </div>
    );
}

export default Header;
