import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Header from './Header';
import Home from './Home';
import User_Login from './User_Login';
import Admin_Login from './Admin_Login';
import About_Us from './About_Us';
import Contact_Us from './Contact_Us';
import Product_management from './Product_management';
import Products from './Products';
import Kart from './Kart';
import Orders from './Orders';
import Order_success from './Order_success';

function App() {
    return (
        <div>
            <BrowserRouter>
                <Header />
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route path='/user_login' element={<User_Login />} />
                    <Route path='/admin_login' element={<Admin_Login />} />
                    <Route path='/about_us' element={<About_Us />} />
                    <Route path='/contact_us' element={<Contact_Us />} />
                    <Route path='/product_management' element={<Product_management />} />
                    <Route path='/products' element={<Products />} />
                    <Route path='/kart' element={<Kart />} />
                    <Route path='/orders' element={<Orders />} />
                    <Route path='/order_success' element={<Order_success />} />
                </Routes>
            </BrowserRouter>
        </div>
    )
}
export default App