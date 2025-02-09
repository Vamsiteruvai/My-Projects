import React, { useState } from "react";
import "./Admin_Login.css";
import { useNavigate } from "react-router-dom";

const Admin_Login = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [showAlert, setAlert] = useState(false);
    const [authUser, setAuthUser] = useState("");
    const [authPass, setAuthPass] = useState("");
    const [error, setError] = useState({ username: "", email: "", password: "" });
    const navigate = useNavigate();

    const emailPat = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const passPat = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\S+$).{8,20}$/;

    const toggleForm = () => {
        setIsLogin(!isLogin);
    };

    const postRegister = async () => {
        if (errorCheck()) return;

        try {
            const response = await fetch("http://127.0.0.1:4500/upload", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username: username.trim(), email: email.trim(), password: password.trim() }),
            });

            if (!response.ok) {
                throw new Error(`Failed to register: ${await response.text()}`);
            }

            alert("Admin Registration Successful!");
            setUsername("");
            setEmail("");
            setPassword("");
            setAlert(true);
            setIsLogin(true);
        } catch (error) {
            alert(`Error submitting form: ${error.message}`);
        }
    };

    const getLogin = async () => {
        try {
            const response = await fetch("http://127.0.0.1:4500/get");

            if (!response.ok) {
                throw new Error(`Failed to fetch admins: ${await response.text()}`);
            }

            const data = await response.json();
            const admin = data.find(admin => admin.email === authUser && admin.password === authPass);

            if (admin) {
                alert("Login successful!");
                localStorage.setItem("admin_id", admin.admin_id);
                localStorage.setItem("admin_email", admin.email);
                navigate('/product_management');
            } else {
                alert("Invalid Email or Password");
            }
        } catch (error) {
            alert(`Error logging in: ${error.message}`);
        }
    };

    const errorCheck = () => {
        let hasErrors = false;
        const newErrors = { username: "", email: "", password: "" };

        if (!username.trim()) {
            newErrors.username = "Username cannot be empty";
            hasErrors = true;
        }
        if (!emailPat.test(email)) {
            newErrors.email = "Enter a valid email";
            hasErrors = true;
        }
        if (!passPat.test(password)) {
            newErrors.password = "Password must be 8-20 characters, contain uppercase, lowercase, number & special character";
            hasErrors = true;
        }

        setError(newErrors);
        return hasErrors;
    };

    return (
        <div className="container">
            <div className={`form-container ${isLogin ? "login-active" : "register-active"}`}>
                <div className="form-wrapper">
                    <div className="form-box login">
                        <h2>Admin Login</h2>
                        <input type="email" placeholder="Email" className="input-box" value={authUser} onChange={(e) => setAuthUser(e.target.value)} required/>
                        <input type="password" placeholder="Password" className="input-box" value={authPass} onChange={(e) => setAuthPass(e.target.value)} required/>
                        <button className="btn" onClick={getLogin}>Login</button>
                    </div>
                    <div className="form-box register">
                        <h2>Register</h2>
                        <input type="text" placeholder="Full Name" className="input-box" value={username} onChange={(e) => setUsername(e.target.value)} />
                        <span style={{ color: "red" }}>{error.username}</span>

                        <input type="email" placeholder="Email" className="input-box" value={email} onChange={(e) => setEmail(e.target.value)} />
                        <span style={{ color: "red" }}>{error.email}</span>

                        <input type="password" placeholder="Password" className="input-box" value={password} onChange={(e) => setPassword(e.target.value)} />
                        <span style={{ color: "red" }}>{error.password}</span>

                        <button className="btn" onClick={postRegister}>Register</button>
                    </div>
                </div>
                <div className="toggle-btn">
                    <button onClick={toggleForm} className="toggle">
                        {isLogin ? "Go to Register" : "Go to Login"}
                    </button>
                </div>
            </div>
            {showAlert && (
                <div className="succ_alert" role="alert">Registration Successful<button className="succ_exit" onClick={() => setAlert(false)}>×</button></div>
            )}
        </div>
    );
};

export default Admin_Login;
