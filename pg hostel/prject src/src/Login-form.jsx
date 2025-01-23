import { useState } from 'react';
import './Login-form.css';
import ReactDOM from 'react-dom';
import Hostel_main from './Hostel_main';
import { useNavigate } from 'react-router-dom';



function Login_form(props) {

    let [username, setUsername] = useState("")
    let [password, setPassword] = useState("")
    let [error, setError] = useState({
        username: "",
        password: ""
    })
    const navigate = useNavigate()
    //let [showAlert, setShowAlert] = useState(false)

    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    function errorChek() {
        if (username.trim() === "") {
            setError((error) => ({ ...error, username: "username notbe empty" }))
        }
        else if (!regex.test(username)) {
            setError((error) => ({ ...error, username: "enter valied email" }))
        }
        else {
            setError(() => ({ ...error, username: "" }))
            setUsername("")
        }
        if (password.trim() === "") {
            setError((error) => ({ ...error, password: "password notbe empty" }))
        }
        else if (password.trim().length < 8) {
            setError((error) => ({ ...error, password: "enter valied minimum 8 characters" }))
        }
        else {
            setError(() => ({ ...error, password: "" }))
            setPassword("")
        }
    }
    function checkAuth() {
        if (username === 'v@gmail.com' && password === 'vamsi123456') {
            return true;
        }
    }
    function handleClick() {
        errorChek();
        if (checkAuth() === true) {
            navigate('/Hostel_main')
        }
        else {
            alert('Login failed')
        }
    }

    return (
        ReactDOM.createPortal(
            <div className="login-container">
                <div className='portal'>
                    <div className="login-label">
                        <label>Login form</label>
                        <button className='exit' onClick={() => props.close()}>×</button>
                    </div>
                    <div className="login-form">
                        <label>Username</label>
                        <input name='username' placeholder='Enter Username' type="text" required value={username} onChange={(e) => {
                            setUsername(e.target.value)
                        }} />
                        <span className='text-danger'>{error.username}</span>
                        <label>Password</label>
                        <input name='password' type="password" placeholder='Enter Password' required value={password} onChange={(e) => {
                            setPassword(e.target.value)
                        }} />
                        <span className='text-danger'>{error.password}</span>
                    </div>
                    <div className='login-submit'>
                        <button onClick={
                            handleClick
                        }>Submit</button>
                    </div>
                </div>
            </div >, document.body
            
        )
        
    )
}
export default Login_form