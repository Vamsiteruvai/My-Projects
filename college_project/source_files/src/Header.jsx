import { Link } from "react-router-dom";
import './Header.css';
import { useEffect, useState } from "react";

function Header() {
    const [isLogout,setLogout]=useState(false);

    useEffect(() => {
        const userLogin = localStorage.getItem("isLoggedIn");
        if (userLogin === "true") {
            setLogout(true);
        } else {
            setLogout(false);
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem("isLoggedIn");
        setLogout(false);
    };


    return (
        <>
            <div className="project-header">
                <div className="project-name">
                    <label>TRUST ASSESSMENT IN ONLINE SOCIAL NETWORKS</label>
                </div>
                <div className="header-btn">
                {
                    isLogout?
                        (<button style={{width:"75px"}}><Link to="/" onClick={handleLogout}>Logout</Link></button>):
                    (
                        <>
                        <button><Link to="/">Home</Link></button>
                        <button><Link to="user_page">User</Link></button>
                        <button><Link to="/osn_page">OSNServer</Link></button>
                        </>
                    )
                }
                </div>
            </div>

        </>
    )
}
export default Header;