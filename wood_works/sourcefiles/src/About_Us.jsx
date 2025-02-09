import './About_Us.css'
import image4 from './assets/image4.jpg'

const About_Us = () => {
    return (
      <div className="about-container">
        <h1 className="about-title">About Us</h1>
        <p className="about-description">We are a leading provider of premium wooden furniture, offering a variety of styles to fit your home and office needs. Our products are crafted with high-quality materials to ensure durability and elegance.</p>
        <div className="about-content">
          <img src={image4} alt="About Us" className="about-image" />
          <div className="about-text">
            <h2>Our Mission</h2>
            <p>To deliver top-notch wooden furniture that combines style, comfort, and sustainability.</p>
            <h2>Why Choose Us?</h2>
            <ul>
              <li>High-quality craftsmanship</li>
              <li>Eco-friendly materials</li>
              <li>Custom designs available</li>
              <li>Affordable prices</li>
            </ul>
          </div>
        </div>
      </div>
    );
  };
export default   About_Us