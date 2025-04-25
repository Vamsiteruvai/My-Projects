import React, { useState } from "react";
import "./Osn_page.css";
import image from './assets/header.jpg';
import { Link, useNavigate } from 'react-router-dom'

const User_page = () => {
    const [isRegister, setIsRegister] = useState(false);
    const [username, setusername] = useState("");
    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");
    const [dob,setDob]=useState("");
    const [address,setAddress]=useState("");
    const [phone,setPhone]=useState(0);
    const [authuser,setuser]=useState("");
    const [authpass,setpass]=useState("");
    const [error, setError] = useState({username:"",password:""});
    const navigate=useNavigate();    

    const emailPat=/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;

    const registerErrorHandle=()=>{
        let istrue=false;

        if(!emailPat.test(email)){
            setError({...error,email:"Email should be valid"});
            istrue=true;
        }
        if(password.length<8){
            setError({...error,password:"Password should be above 8 characters"});
            istrue=true;
        }
        if(!email.trim()){
            setError({...error,email:"Username cannot be empty"});
            istrue=true;
        }

        return istrue;
    }

    const getRegister=async ()=>{

        if(registerErrorHandle()){
            return;
        }
        else{
            const response=await fetch('http://localhost:3500/postUserDetails',{
                method:'POST',
                body:JSON.stringify({
                    username:username,
                    email:email,
                    password:password
                }),
                headers: { "Content-Type": "application/json" }
            });
            const data=await response.json();
            alert(data.message);
        }
        setEmail("");
        setPassword("");
        setusername("");
    }

    const authentication=async ()=>{
        const response=await fetch('http://localhost:3500/getUserDetails');
        const data=await response.json();

        const login=data.find((ele)=>(ele.email===authuser && ele.password===authpass));

        if(login){
            alert("login successfull");
            localStorage.setItem("fullname","vamsi");
            localStorage.setItem("isLoggedIn",true)
            window.location.href="/user_welcome";
        }else{
            alert("Invalid login details");
        }
    }


    const toggleForm = () => {
        setIsRegister(!isRegister);
    };

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
                                <h2>User Login</h2>
                                <input type="text" placeholder="Username" value={authuser} onChange={(e)=>{setuser(e.target.value)}} required/>
                                <input type="password" placeholder="Password" value={authpass} onChange={(e)=>{setpass(e.target.value)}} required/>
                                <button onClick={authentication}>Login</button>
                                <p onClick={toggleForm}>Don't have an account? Register</p>
                            </div>
                            <div className="register-form">
                                <h2>Register</h2>
                                <input type="text" placeholder="Full Name"  value={username} onChange={(e)=>{setusername(e.target.value)}} required />
                                <input type="email" placeholder="Email" value={email} onChange={(e)=>{setEmail(e.target.value)}} required/>
                                <p style={{color:"red"}}>{error.username}</p>
                                <input type="password" placeholder="Password" value={password} onChange={(e)=>{setPassword(e.target.value)}} required/>
                                <p style={{color:"red"}}>{error.password}</p>
                                <button onClick={getRegister}>Register</button>
                                <p onClick={toggleForm}>Already have an account? Login</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default User_page;