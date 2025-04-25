import '../userscomponant/Profile.css';
import image from '../assets/header.jpg';
import profile from '../assets/profile.jpg';
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

function Profile() {
    const [profile, setProfile] = useState([]);
    useEffect(() => {
        getData();
    }, []);
    const getData = async () => {
        const response = await fetch("http://localhost:3500/getUserDetails");
        const data = await response.json();
        setProfile(data);
    }
    return (
        <>
            <div className="home-componant">
                <div className="header-img">
                    <img src={image} alt="img not fount" />
                </div>
                <div className="home-view">
                    <div className="home-sidebar">
                        <ul>
                            <li><Link to="#">View my profile</Link></li>
                            <li><Link to="#">Search my friends & request</Link></li>
                            <li><Link to="#">View friend requests by me</Link></li>
                            <li><Link to="#">View friend requests by others</Link></li>
                            <li><Link to="#">View all friends and feed opinions</Link></li>
                            <li><Link to="#">Give ratings</Link></li>
                        </ul>
                    </div>
                    <div className="home-content">
                        <h1>Profile</h1>
                        <div className="profile-container">
                            <table>
                                <thead>
                                    <tr>
                                        <th>NAME</th>
                                        <th>EMAIL</th>
                                        <th>PHONE</th>
                                        <th>ADDRESS</th>
                                        <th>DOB</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        profile.map((ele, index) => (
                                            <tr key={index}>
                                                <td>{ele.fullname}</td>
                                                <td>{ele.email}</td>
                                            </tr>
                                        ))
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
export default Profile;