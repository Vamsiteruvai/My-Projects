import { useState } from "react";
import "./User_Login.css";
import { useNavigate } from "react-router-dom";

const User_Login = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [authEmail, setAuthEmail] = useState("");
    const [authPass, setAuthPass] = useState("");
    const [error, setError] = useState({ username: "", email: "", password: "" });

    const emailPat = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const passPat = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\S+$).{8,20}$/;

    const navigate = useNavigate();

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
                const errorMessage = await response.text();
                throw new Error(`Failed to register: ${errorMessage}`);
            }

            const data = await response.json();
            console.log("Server Response:", data);
            alert("Registration Successful!");
            
            // Clear inputs on success
            setUsername("");
            setEmail("");
            setPassword("");
            setIsLogin(true); // Switch to login view

        } catch (error) {
            alert(`Error submitting form: ${error.message}`);
        }
    };

    const getRegister = async () => {
        try {
            const response = await fetch("http://127.0.0.1:4500/getusers");

            if (!response.ok) {
                throw new Error(`Failed to get users: ${await response.text()}`);
            }

            const data = await response.json();

            const user = data.find(user => user.email === authEmail && user.password === authPass);

            if (user) {
                alert("Login successful!");
                localStorage.setItem("user_id", user.user_id);
                localStorage.setItem("user_email", user.email);
                navigate('/products');
                localStorage.setItem("isLoggedIn","true");
                localStorage.setItem("isLoggedOut","true");
                window.location.reload();
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
                        <h2>User Login</h2>
                        <input type="email" placeholder="Email" className="input-box"
                            onChange={(e) => setAuthEmail(e.target.value)} required/>
                        <input type="password" placeholder="Password" className="input-box"
                            onChange={(e) => setAuthPass(e.target.value)} required/>
                        <button className="btn" onClick={getRegister}>Login</button>
                    </div>
                    <div className="form-box register">
                        <h2>User Register</h2>
                        <input type="text" placeholder="Full Name" className="input-box"
                            value={username} onChange={(e) => setUsername(e.target.value)} />
                        <span style={{ color: "red" }}>{error.username}</span>

                        <input type="email" placeholder="Email" className="input-box"
                            value={email} onChange={(e) => setEmail(e.target.value)} />
                        <span style={{ color: "red" }}>{error.email}</span>

                        <input type="password" placeholder="Password" className="input-box"
                            value={password} onChange={(e) => setPassword(e.target.value)} />
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
        </div>
    );
};

export default User_Login;
