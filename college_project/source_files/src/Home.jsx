import { Link } from "react-router-dom";
import './Home.css';
import image from './assets/header.jpg';
import { useState } from "react";

function Home() {
    let home= localStorage.getItem("home");
    console.log(localStorage.getItem("home"));
    return (
        <>
            <div className="home-componant">
                <div className="header-img">
                    <img src={image} alt="img not fount" />
                </div>
                <div className="home-view">
                    <div className="home-sidebar">
                        <ul>
                            <li><Link to="/">Home</Link></li>
                            <li><Link to="/user_page">User</Link></li>
                            <li><Link to="/osn_page">OSNServer</Link></li>
                        </ul>
                    </div>
                    <div className="home-content">
                        <h1>About the Project</h1>
                        <p>
                            The <span class="highlight">Trust Assessment in Online Social Networks (OSNs)</span> project aims to
                            evaluate and enhance the reliability and trustworthiness of interactions within online social
                            platforms. With the increasing reliance on OSNs for communication, information sharing, and
                            decision-making, ensuring trust among users has become a critical challenge.
                        </p>
                        <p>
                            This project focuses on developing a framework to:
                        </p>
                        <ul>
                            <li>Analyze user behavior and interactions to identify trust patterns.</li>
                            <li>Detect and mitigate malicious activities such as fake accounts, misinformation, and scams.</li>
                            <li>Provide users with a trust score to help them make informed decisions about their interactions.
                            </li>
                        </ul>
                        <p>
                            By leveraging advanced algorithms and machine learning techniques, this project aims to create a
                            safer and more reliable environment for users in online social networks.
                        </p>
                    </div>
                </div>
            </div>
        </>
    )
}
export default Home;