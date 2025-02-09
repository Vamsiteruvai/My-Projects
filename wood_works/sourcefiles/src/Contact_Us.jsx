import './Contact_Us.css'

const Contact_Us = () => {
    return (
      <div className="contact-container">
        <h1 className="contact-title">Contact Us</h1>
        <p className="contact-description">Have questions? Get in touch with us through our social media and contact details below.</p>
        <ul className="contact-list">
          <li><a href="https://www.facebook.com" target="_blank">Facebook</a></li>
          <li><a href="https://www.instagram.com" target="_blank" >Instagram</a></li>
          <li><a href="https://www.twitter.com" target="_blank">Twitter</a></li>
          <li>vamsiteruvai9@gmail.com</li>
          <li>+91 9963383840</li>
        </ul>
      </div>
    );
  };

  export default Contact_Us