import { useNavigate } from 'react-router-dom'
import './New_customer.css'
import { useState } from 'react'
import Pay_advance from './Pay_advance'

function New_customer() {
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const mobile = /^[6-9]\d{9}$/;

    let [payment, setPayment] = useState(false)
    let navigate = useNavigate()
    let [fullname, setFullname] = useState("")
    let [email, setEmail] = useState("")
    let [phone, setPhone] = useState("")
    let [address, setaddress] = useState("")
    let [fathername, setFathername] = useState("")
    let [fatherPhone, setFatherphone] = useState("")
    let [roomNumber, setRoomnumber] = useState("")
    let [adhaar, setAdhaar] = useState("")
    let [error, setError] = useState({
        fullname: "",
        email: "",
        phone: "",
        adhaar: "",
        address: "",
        fathername: "",
        fatherPhone: "",
        roomNumber: ""
    })

    function Handledata() {
        const hasErrors = errorChek(); // Check for errors
        if (!hasErrors) {
            storeData(); // Proceed to store data only if no errors
        } else {
            console.log("Fix errors before storing data.");
        }
    }

    async function storeData() {
        let randomNumber = Math.floor(Math.random() * 90000) + 10000;
        let response = await fetch('http://localhost:3000/new_customer_details', {
            method: 'POST',
            body: JSON.stringify({
                "id": String(randomNumber),
                "name": fullname,
                "email": email,
                "phone": phone,
                "adhaar": adhaar,
                "address": address,
                "father_name": fathername,
                "father_phone": fatherPhone,
                "room_number": roomNumber
            }),
            headers: {
                'contect-type': 'application/json'
            }
        })
        let data = await response.json()
        alert("SUCCESSFULLY SAVED YOUR INFORMATION")
        alert(`Please Copy ID=${randomNumber}`)
    }
    function errorChek() {
        let hasErrors = false;
        if (fullname.trim() === "") {
            setError((error) => ({ ...error, fullname: "fullname not be empty" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, fullname: "" }))
            setFullname("")

        }
        if (email.trim() === "") {
            setError((error) => ({ ...error, email: "email not be empty" }))
            hasErrors = true
        }
        else if (!regex.test(email)) {
            setError((error) => ({ ...error, email: "enter valied email" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, email: "" }))
            setEmail("")
        }
        if (phone.trim() === "") {
            setError((error) => ({ ...error, phone: "phonenumber not be empty" }))
            hasErrors = true
        }
        else if (!mobile.test(phone)) {
            setError((error) => ({ ...error, phone: "enter valied phonenumber" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, phone: "" }))
            setPhone("")
        }
        if (fathername.trim() === "") {
            setError((error) => ({ ...error, fathername: "fathername not be empty" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, fathername: "" }))
            setFathername("")
        }
        if (fatherPhone.trim() === "") {
            setError((error) => ({ ...error, fatherPhone: "phonenumber not be empty" }))
            hasErrors = true
        }
        else if (!mobile.test(fatherPhone)) {
            setError((error) => ({ ...error, fatherPhone: "enter valied phonenumber" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, fatherPhone: "" }))
            setFatherphone("")
        }

        if (roomNumber.trim() === "") {
            setError((error) => ({ ...error, roomNumber: "room number not be empty" }))
            hasErrors = true
        }
        else if (Number(roomNumber) < 101 || Number(roomNumber) > 105) {
            setError((error) => ({ ...error, roomNumber: "room number not less than 101 or greater than 105" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, roomNumber: "" }))
            setRoomnumber("")
        }
        if (address.trim() === "") {
            setError((error) => ({ ...error, address: "address not be empty" }))
            hasErrors = true
        }
        else if (address.trim().length < 15) {
            setError((error) => ({ ...error, address: "address not less than 15 charectors" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, address: "" }))
            setaddress("")
        }
        if (adhaar.trim() === "") {
            setError((error) => ({ ...error, adhaar: "adhaar not be empty" }))
            hasErrors = true
        }
        else if (adhaar.trim().length < 12 || adhaar.trim().length>12) {
            setError((error) => ({ ...error, adhaar: "adhaar not less than 12" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, adhaar: "" }))
        }
        return hasErrors;
    }
    return (
        <>
            <div className='new_customer_container'>
                <div className='new_customer_subcontainer'>
                    <div className="new_customer_details">
                        <h2><strong>Personal details</strong></h2>
                        <div className='new_customer_details_input'>
                            <input type="text" placeholder="full name" value={fullname} onChange={(e) => setFullname(e.target.value)} />
                            <span className='text-danger'>{error.fullname}</span>
                        </div>
                        <div className='new_customer_details_input'>
                            <input type="text" placeholder="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                            <span className='text-danger'>{error.email}</span>
                        </div>
                        <div className='new_customer_details_input'>
                            <input type="number" placeholder="phone" value={phone} onChange={(e) => setPhone(e.target.value)} />
                            <span className='text-danger'>{error.phone}</span>
                        </div>
                        <div className='new_customer_details_input'>
                            <input type="text" placeholder="adhaar" value={adhaar} onChange={(e) => setAdhaar(e.target.value)} />
                            <span className='text-danger'>{error.adhaar}</span>
                        </div>
                        <div className='new_customer_details_input'>
                            <input type="text" placeholder="address" value={address} onChange={(e) => setaddress(e.target.value)} />
                            <span className='text-danger'>{error.address}</span>
                        </div>
                        <div className='new_customer_details_input'>
                            <input type="text" placeholder="father name" value={fathername} onChange={(e) => setFathername(e.target.value)} />
                            <span className='text-danger'>{error.fathername}</span>
                        </div>
                        <div className='new_customer_details_input'>
                            <input type="number" placeholder="father phone" value={fatherPhone} onChange={(e) => setFatherphone(e.target.value)} />
                            <span className='text-danger'>{error.fatherPhone}</span>
                        </div>
                    </div>
                    <div className="new_customer_room" id='room_details'>
                        <h2><strong>Room details</strong></h2>
                        <div className='new_customer_details_input'>
                            <input type="number" placeholder="room number" value={roomNumber} onChange={(e) => setRoomnumber(e.target.value)} />
                            <span className='text-danger'>{error.roomNumber}</span>
                        </div>
                        <div className='new_customer_details_input'>
                            <input style={{ backgroundColor: 'blue', color: 'white' }} className="form-control form-control-sm" type="button" placeholder="address" aria-label=".form-control-sm example" value={'PAY ADVANCE'} onClick={() => setPayment(!payment)} />
                        </div>
                        <div className='new_customer_details_input'>
                            <input style={{ backgroundColor: 'green', color: 'white' }} className="form-control form-control-sm" type="button" aria-label=".form-control-sm example" value={'save'} onClick={Handledata} />
                        </div>
                    </div>
                </div>
                <div className='new_customer_return_home'>
                    <button type='button' className='btn btn-danger' onClick={() => navigate('/hostel_main')}>Return Home</button>
                </div>
            </div>


            {
                payment && (<Pay_advance close={setPayment} />)
            }
        </>
    )
}
export default New_customer