import { useEffect, useState } from 'react';
import './Product_management.css'
import image5 from './assets/image5.jpg'
import image6 from './assets/image6.jpg'
import image7 from './assets/image7.jpg'
import { Link, useNavigate } from 'react-router-dom';

const Product_management = () => {

  const [product_id, setprodid] = useState("");
  const [productname, setpname] = useState("");
  const [price, setprice] = useState("");
  const [rating, setrating] = useState("");
  const [image_url, setimage] = useState(null);
  const [activeForm, setActiveForm] = useState(null);
  const navigate = useNavigate();
  const [users, setUsers] = useState([]);
  const [products, setProducts] = useState([]);



  const getIndProduct = async () => {
    const res1 = await fetch(`http://127.0.0.1:4500/getproducts/${product_id}`);
    const data2 = await res1.json();
    setpname(data2[0].product_name)
    setprice(data2[0].price)
    setrating(data2[0].rating)
  }
  
  
  const updateindproduct = async () => {
    try {
      const formData = new FormData();
      formData.append("productname", productname);
      formData.append("price", price);
      formData.append("rating", rating);
      formData.append("image_url", image_url);
      const response = await fetch(`http://127.0.0.1:4500/putproducts/${product_id}`, {
        method: "PATCH",
        body: formData,
      });

      const data = await response.json();
      //console.log(data);
      setpname("");
      setprice("");
      setrating("");
      setimage("");
      alert(data.message);
    } catch (error) {
      console.error("Error updating product:", error);
    }
  };



  const handleImageChange = (e) => {
    const file = e.target.files[0];
    setimage(file);
  };



  const addProducts = async () => {
    //console.log(image_url)
    const formData = new FormData();

    formData.append('productname', productname);
    formData.append('price', price);
    formData.append('rating', rating);
    formData.append('image_url', image_url);

    const response = await fetch('http://127.0.0.1:4500/uploadproduct', {
      method: 'POST',
      body: formData,
      // headers: { "Content-Type": "multipart/form-data" }
    });
    setprice("");
    setrating("");
    setpname("");
    setimage("");
    alert("Product Added Successfully" )
  }



  const deleteProduct = async () => {
    const response = await fetch(`http://127.0.0.1:4500/removekart/${product_id}`, { method: 'DELETE' });
    const response1 = await fetch(`http://127.0.0.1:4500/delete/${product_id}`, { method: 'DELETE' });
    // const data1 = await response1.json();
    setprodid("");
  }




  const showuserInfo = async () => {
    try {
      const response = await fetch("http://127.0.0.1:4500/getusers");
      const data = await response.json();
      setUsers(data);
      setActiveForm("user")
    } catch (error) {
      console.error("Error fetching images:", error);
    }
  };


  const viewProducts = async () => {
    try {
      const response = await fetch("http://127.0.0.1:4500/getproducts");
      const data = await response.json();
      setProducts(data);
      setActiveForm("view products")
    } catch (error) {
      console.error("Error fetching images:", error);
    }
  };




  const renderForm = () => {
    switch (activeForm) {
      case "add":
        return (
          <div className="form1-container add-form">
            <h2>Add Product</h2>
            <input type="text" placeholder="Product Name" className="input-field" onChange={(e) => { setpname(e.target.value) }} value={productname} />
            <input type="number" placeholder="Price" className="input-field" onChange={(e) => { setprice(e.target.value) }} value={price} />
            <input type="text" placeholder="Rating" className="input-field" onChange={(e) => { setrating(e.target.value) }} value={rating} />
            <input type="file" className="input-field" onChange={handleImageChange} />
            <button className="form-button" onClick={addProducts}>Add Product</button>
          </div>
        );
      case "update":
        return (
          <div className="form1-container update-form">
            <h2>Update Product</h2>
            <div className="input-group"><input type="text" placeholder="Product ID" className="input-field" value={product_id} onChange={
              (e) => { setprodid(e.target.value) }
            } /><button className="form-button" onClick={getIndProduct}>GetProduct</button></div>
            <div className="input-group"><input type="text" placeholder="Product Name" className="input-field" value={productname} onChange={
              (e) => { setpname(e.target.value) }
            } /><button className="form-button" onClick={updateindproduct}>Update</button></div>
            <div className="input-group"><input type="number" placeholder="Price" className="input-field" value={price} onChange={
              (e) => { setprice(e.target.value) }
            } /><button className="form-button" onClick={updateindproduct}>Update</button></div>
            <div className="input-group"><input type="text" placeholder="Rating" className="input-field" value={rating} onChange={
              (e) => { setrating(e.target.value) }
            } /><button className="form-button" onClick={updateindproduct}>Update</button></div>
            <div className="input-group"><input type="file" className="input-field" onChange={handleImageChange}/><button className="form-button" onClick={updateindproduct}>Update</button></div>
          </div>
        );
      case "delete":
        return (
          <div className="form1-container delete-form">
            <h2>Delete Product</h2>
            <input type="number" placeholder="Product ID" className="input-field" value={product_id} onChange={(e) =>  setprodid(e.target.value) } />
            <input type="text" placeholder="Product Name OPTIONAL" className="input-field" />
            <button className="form-button delete-button" onClick={deleteProduct}>Delete Product</button>
          </div>
        );
      case "show":
        return navigate('/products');
      case "user":
        return (
          <div className="user-info-container">
            <h2>User Information</h2>
            <table className="user-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Password</th>
                  <th>Site Rating</th>
                </tr>
              </thead>
              <tbody>
                {users.map((user, index) => (
                  <tr key={index}>
                    <td>{user.user_id}</td>
                    <td>{user.user_name}</td>
                    <td>{user.email}</td>
                    <td>{user.password}</td>
                    <td>{user.rating}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        );
        case "view products":
        return (
          <div className="user-info-container">
            <h2>User Information</h2>
            <table className="user-table">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Product Name</th>
                  <th>Price</th>
                  <th>Rating</th>
                </tr>
              </thead>
              <tbody>
                {products.map((product, index) => (
                  <tr key={index}>
                    <td>{product.product_id}</td>
                    <td>{product.product_name}</td>
                    <td>{product.price}</td>
                    <td>{product.rating}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        );
      default:
        return null;
    }
  };




  return (
    <div className="products-container">
      <h1 className="products-title">Manage Products</h1>
      <div className="products-buttons">
        <button className="add-product" onClick={() => setActiveForm("add")}>Add Product</button>
        <button className="update-product" onClick={() => setActiveForm("update")}>Update Product</button>
        <button className="delete-product" onClick={() => setActiveForm("delete")}>Delete Product</button>
        <button className="show-products" ><Link style={{color:"white",textDecoration:"none"}} to="/products">Show Products</Link></button>
        <button className="user-info" onClick={showuserInfo}>User Info</button>
        <button className="user-info" onClick={viewProducts}>View Products</button>
        <button className="user-info" onClick={()=>navigate('/Orders')}>User Orders</button>
      </div>
      <div className="list">
        {renderForm()}
      </div>
      <div className="product-list">
        <div className="product-card">
          <img src={image6} alt="Sofa" className="product-image" />
          <h2 className="product-title">Sofa</h2>
        </div>
        <div className="product-card">
          <img src={image7} alt="Bed" className="product-image" />
          <h2 className="product-title">Bed</h2>
        </div>
        <div className="product-card">
          <img src={image5} alt="Wardrobe" className="product-image" />
          <h2 className="product-title">Wardrobe</h2>
        </div>
      </div>
    </div>
  );
};
export default Product_management
