import './About_us.css';
import image from './image1.jpg'

function About_us() {
    return (
        <>
            <div className='about_us_container'>
                <div className='about_us'>
                    <label>ABOUT US</label>
                    <p>we believe in creating a safe, comfortable, and supportive environment where you can thrive and focus on your goals.
                        Whether you're a student, working professional, or someone new to the city, our mission is to offer a cozy,
                        welcoming space that caters to your needs.To provide a secure, convenient, and affordable living space that offers more than just accommodation. 
                        We are committed to fostering a sense of community and ensuring that every resident feels at home, away from home.</p>
                </div>
                <div className='about_us_img'>
                    <img src={image} height="300" width="450"/>
                </div>
            </div>
        </>
    );
}

export default About_us;
