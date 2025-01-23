
import Login_form from "./Login-form"
import { useState } from "react"
import './Header.css'
import { Link } from "react-router-dom"


function Header() {
    let [login, setLogin] = useState(false)
    return (
        <>
            <div className="header-container">
                <div className="header-label">
                    <label>Menu</label>
                </div>
                <div className="list">
                    <button className="list_li"><Link to="/">Home</Link></button>
                    <button className="list_li"><Link to="/contact us">Contact Us</Link></button>
                    <button className="list_li"><Link to="/about us">About Us</Link></button>
                </div>
            </div>
            {
                login && <Login_form close={setLogin} />
            }
        </>
    )
}
export default Header