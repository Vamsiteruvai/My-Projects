import './Home.css'
import image1 from './assets/image1.jpg'
import image2 from './assets/image2.jpg'
import image3 from './assets/image3.jpg'


const Home = () => {
    return (
      <div className="home-container">
        <h1 className="home-title">Welcome to Our Wooden Furniture Store</h1>
        <p className="home-description">Explore our premium collection of wooden furniture, including double cots, dining tables, chairs, and more.</p>
        <div className="product-grid">
          <div className="product-card">
            <img src={image1} alt="Double Cot" className="product1-image" />
            <h2 className="product-title">Double Cot</h2>
          </div>
          <div className="product-card">
            <img src={image3} alt="Dining Table" className="product1-image" />
            <h2 className="product-title">Dining Table</h2>
          </div>
          <div className="product-card">
            <img src={image2} alt="Chair" className="product1-image" />
            <h2 className="product-title">Chair</h2>
          </div>
        </div>
      </div>
    );
  };
      
export default Home