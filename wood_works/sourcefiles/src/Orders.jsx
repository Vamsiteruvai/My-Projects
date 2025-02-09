import { useState } from 'react';
import './Orders.css';
import { Link, useNavigate } from 'react-router-dom';



const Orders = () => {
    const [activeForm, setActiveForm] = useState(null);
    const [showAnalysisButtons, setShowAnalysisButtons] = useState(false);
    const [showOrdersTable, setShowOrdersTable] = useState(false);
    const [ordersData, setOrdersData] = useState([]);
    const [pname, setPname] = useState("");
    const navigate = useNavigate();


    const toggleForm = (form) => {
        setActiveForm(activeForm === form ? null : form);
    };


    const getOrders = async () => {
        const res = await fetch('http://127.0.0.1:4500/getOrders');
        const data = await res.json();
        //console.log(data)
        setOrdersData(data);
        setShowOrdersTable(!showOrdersTable)
    }

    const noOfProducts = async () => {
        try {
            const res = await fetch(`http://127.0.0.1:4500/noofproducts/?name=${pname}`)
            const data = await res.json();
            if (res.ok) {
                setOrdersData(data);
                toggleForm("product-max");
            }
        } catch (error) {
            alert(error)
        }

    }


    const maxProductSale = async () => {
        try {
            const res = await fetch(`http://127.0.0.1:4500/getproductmax`)
            const data = await res.json();
            if (res.ok) {
                setOrdersData(data);
                toggleForm("max-product");
            }
        } catch (error) {
            alert(error)
        }

    }


    const mostPurchased=async ()=>{
        try {
            const res = await fetch(`http://127.0.0.1:4500/mostpurchased`)
            const data = await res.json();
            if (res.ok) {
                setOrdersData(data);
                //console.log(data)
                toggleForm("most-user");
            }
        } catch (error) {
            alert(error)
        }
    }


    return (
        <div className="orders-page">
            <div className="orders-background"></div>
            <div className="orders-content">
                <h1 className="orders-title">Order Management</h1>
                <div className="orders-buttons">
                    <button className="total-orders" onClick={getOrders}>Total Orders</button>
                    <button className="product-analysis" onClick={() => setShowAnalysisButtons(!showAnalysisButtons)}>Product Analysis</button>
                    <button className="product-analysis"><Link to="/product_management">Back to Dashboard</Link></button>
                </div>
                {showAnalysisButtons && (
                    <div className="analysis-buttons">
                        <button className="max-sale" onClick={() => toggleForm("max-sale")}>Product Sold</button>
                        <button className="min-sale" onClick={maxProductSale}>Max Product Sold</button>
                        <button className="most-purchased-user" onClick={mostPurchased}>Mostly Purchased Users</button>
                    </div>
                )}
                <div className="list">
                    {activeForm === "max-sale" && (
                        <div className="form-container1 slide-up">
                            <h2>No.of Products Sold</h2>
                            <input type="text" placeholder="Product Name" className="input-field1" value={pname} onChange={(e) => setPname(e.target.value)} />
                            <button className="form-button1" onClick={noOfProducts}>Get Details</button>
                        </div>
                    )}
                    {activeForm === "max-product" && (
                        <table className="orders-table">
                            <thead>
                                <tr>
                                    <th>product name</th>
                                    <th>Quantity Of Sold</th>

                                </tr>
                            </thead>
                            <tbody>
                                {
                                    ordersData.map((item, index) => (
                                        <tr key={index}>
                                            <td>{item.product_name}</td>
                                            <td>{item.total_sales}</td>
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </table>
                    )}
                    {activeForm === "most-user" && (
                        <table className="orders-table">
                        <thead>
                            <tr>
                                <th>User Name</th>
                                <th>Product Name</th>
                                <th>Quantity Of Purchsed</th>

                            </tr>
                        </thead>
                        <tbody>
                            {
                                ordersData.map((item, index) => (
                                    <tr key={index}>
                                        <td>{item.user_name}</td>
                                        <td>{item.product_name}</td>
                                        <td>{item.total_sales}</td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                    )}
                    {/* product max */}
                    {activeForm === "product-max" && (
                        <table className="orders-table">
                            <thead>
                                <tr>
                                    <th>product name</th>
                                    <th>No.of.Times</th>

                                </tr>
                            </thead>
                            <tbody>
                                {
                                    ordersData.map((item, index) => (
                                        <tr key={index}>
                                            <td>{item.product_name}</td>
                                            <td>{item.total_sales}</td>
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </table>
                    )}
                </div>
                {showOrdersTable && (
                    <div className="orders-table-container fade-in">
                        <h2 className="orders-table-title">Orders Portal</h2>
                        <table className="orders-table">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>User ID</th>
                                    <th>Product ID</th>
                                    <th>Product Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Date/Time</th>
                                </tr>
                            </thead>
                            <tbody>
                                {ordersData.map((order, index) => (
                                    <tr key={index}>
                                        <td>{order.order_id}</td>
                                        <td>{order.user_id}</td>
                                        <td>{order.product_id}</td>
                                        <td>{order.product_name}</td>
                                        <td>{order.quantity}</td>
                                        <td>{order.price}</td>
                                        <td>{order.order_datetime}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <button className="back-to-dashboard" onClick={() => setShowOrdersTable(false)}>Back to Dashboard</button>
                    </div>
                )}
            </div>

        </div>
    );
};

export default Orders
