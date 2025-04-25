import React, { useState } from "react";
import "./Osn_page.css";
import image from './assets/header.jpg';
import { Link } from 'react-router-dom'

const Osn_page = () => {
    const [isRegister, setIsRegister] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const toggleForm = () => {
        setIsRegister(!isRegister);
    };

    const getAuthent=()=>{

        if(username==="vamsi9@gmail.com" && password==="osnserver123"){
            localStorage.setItem("isLoggedIn",true);
            window.location.href="/osn_welcome";
        }
    }

    return (
        <div className="osn-container">
            <div className="osn-main">
                <div className="osn-image">
                    <img src={image} alt="OSN Banner" />
                </div>
                <div className="osn-content">
                    <div className="osn-sidebar">
                        <ul>
                            <li><Link to="/">Home</Link></li>
                            <li><Link to="/user_page">User</Link></li>
                            <li><Link to="/osn_page">OSNServer</Link></li>
                        </ul>
                    </div>
                    <div className="osn-form-container">
                        <div className={`form-box ${isRegister ? "slide" : ""}`}>
                            <div className="login-form">
                                <h2>OSN Login</h2>
                                <input type="text" placeholder="Username" value={username} onChange={(e)=>(setUsername(e.target.value))}/>
                                <input type="password" placeholder="Password" value={password} onChange={(e)=>(setPassword(e.target.value))}/>
                                <button onClick={getAuthent}>Login</button>
                                <p onClick={toggleForm}>Don't have an account? Register</p>
                            </div>
                            <div className="register-form">
                                <h2>Register</h2>
                                <input type="text" placeholder="Full Name" />
                                <input type="email" placeholder="Email" />
                                <input type="password" placeholder="Password" />
                                <button>Register</button>
                                <p onClick={toggleForm}>Already have an account? Login</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Osn_page;