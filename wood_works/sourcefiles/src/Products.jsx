import { useEffect, useState } from "react";
import './Products.css';
import axios from "axios";


function Products() {
    const [products, setProduct] = useState([]);

    useEffect(() => {
        const fetchImages = async () => {
            try {
                const response = await fetch("http://127.0.0.1:4500/getproducts");
                const data = await response.json();
                //console.log(data);
                setProduct(data);
            } catch (error) {
                console.error("Error fetching images:", error);
            }
        };

        fetchImages();

    }, []);


    const addToCart = async (product) => {
        try {
            const user_id = localStorage.getItem("user_id"); // ✅ Retrieve user_id from storage
            console.log("User ID:", user_id);

            if (!user_id) {
                alert("Please log in first!");
                return;
            }

            const cartData = {
                user_id,
                product_id: product.product_id,
                name: product.product_name,
                price: product.price,
                quantity: 1,
                image: product.image_url
            };

            const res = await fetch("http://127.0.0.1:4500/add_to_kart", {
                method: "POST",
                headers: { "Content-Type": "application/json" }, 
                body:JSON.stringify(cartData)
            });

            if (!res.ok) {
                throw new Error(`HTTP error! Status: ${res.status}`);
            }

            const data = await res.json();
            console.log("Cart Response:", data);
            alert("Item added to cart!");
        } catch (error) {
            console.error("Error adding to cart:", error);
        }
    };


    return (
        <div className="product-container">
            {products.map((product, index) => (
                <div key={index} className="product-card">
                    <img
                        src={`http://127.0.0.1:4500/uploads/${product.image_url}`}
                        alt={product.product_name}
                        className="product-image"
                    />
                    <p className="product-name">{product.product_name}</p>
                    <p className="product-price">₹{product.price}</p>
                    <p className="product-rating">⭐ {product.rating}</p>
                    <button className="btn productKartbtn" onClick={() => addToCart(product)}>Add To Kart</button>
                </div>
            ))}
        </div>
    );
}

export default Products;
