import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './Home'
import Header from './Header';
import Osn_page from './Osn_page';
import User_page from './User_page';
import User_welcome from './User_welcome';
import Osn_welcome from './Osn_welcome';
import Profile from './userscomponant/Profile.jsx';

function App() {
    return (
        <>
            <BrowserRouter>
                <Header />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/osn_page" element={<Osn_page />} />
                    <Route path="/user_page" element={<User_page />} />
                    <Route path="/user_welcome" element={<User_welcome />} />
                    <Route path="/osn_welcome" element={<Osn_welcome />} />
                    <Route path="/profile" element={<Profile />} />
                </Routes>
            </BrowserRouter>
        </>
    )
}
export default App;