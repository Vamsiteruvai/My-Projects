import { Link } from "react-router-dom";
import './Header.css';

function Header() {
    return (
        <>
            <div className="project-header">
                <div className="project-name">
                    <label>TRUST ASSESSMENT IN ONLINE SOCIAL NETWORKS</label>
                </div>
                <div className="header-btn">
                    <button><Link to="/">Home</Link></button>
                    <button><Link to="user_page">User</Link></button>
                    <button><Link to="/osn_page">OSNServer</Link></button>
                </div>
            </div>

        </>
    )
}
export default Header;