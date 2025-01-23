import { useState } from 'react'
import './App.css'
import Login_form from './Login-form'
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import Contact_us from './Contact_us'
import About_us from './About_us'
import Home from './Home'
import Header from './Header'
import Hostel_main from './Hostel_main'
import New_customer from './New_customer'
import Details_of_customer from './Details_of_customer'
import Remove_customer from './Remove_customer'
import Rent_amount from './Rent_amount'
import Tracking_customer from './Tracking_customer'
import Room_management from './Room_management'


function App() {
  
  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/contact us' element={<Contact_us />} />
          <Route path='/about us' element={<About_us />} />
          <Route path='/hostel_main' element={<Hostel_main />} />
          <Route path='/New_customer' element={<New_customer />} />
          <Route path='/Details_of_customer' element={<Details_of_customer/>} />
          <Route path='/Remove_customer' element={<Remove_customer/>} />
          <Route path='/Tracking_customer' element={<Tracking_customer/>} />
          <Route path='/room_management' element={<Room_management/>} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App