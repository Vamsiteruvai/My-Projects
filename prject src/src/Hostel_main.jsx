import { useNavigate } from 'react-router-dom'
import './Hostel_main.css'
import { useState } from 'react'
import Rent_amount from './Rent_amount'
function Hostel_main() {
    let navigate = useNavigate()
    let navigate1 = useNavigate()
    let navigate3 = useNavigate()
    let [showRent, setShowrent] = useState(false)
    function handle(){
        navigate3('/Remove_customer');
        confirm("Are you sure to REMOVE")
    }
    return (
        <>
            <div className="hostel_main">
                <div className='items'>
                    <button className='hostel' onClick={() => navigate('/New_customer')}>NEW CUSTOMER</button>
                    <button className='hostel' onClick={() => navigate('/room_management')}>ROOM MANAGEMENT</button>
                    <button className='hostel' onClick={() => navigate1('/Details_of_customer')}>DETAILS OF CUSTOMER</button>
                    <button className='hostel' onClick={handle}>REMOVE CUSTOMER</button>
                    <button className='hostel' onClick={() => setShowrent(!showRent)}>PAY RENT</button>
                    {
                        showRent && (<Rent_amount close={setShowrent} />)
                    }
                </div>
            </div>
        </>
    )
}
export default Hostel_main