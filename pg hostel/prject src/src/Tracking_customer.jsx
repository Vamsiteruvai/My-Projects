import { useState } from 'react';
import './Tracking_customer.css'
import { useNavigate } from 'react-router-dom';

function Tracking_customer() {
    let [customer, setcustomer] = useState([])
    let [advance, setAdvance] = useState([])
    let [rent, setRent] = useState([])
    let [customerId, setCustomerId] = useState("")
    let navigate = useNavigate()

    //console.log(customer)

    function handle() {
        fetchData();
        fetchData1();
        fetchData2();
    }

    async function fetchData() {
        let response = await fetch('http://localhost:3000/new_customer_details')
        setcustomer(await response.json())
    }
    async function fetchData1() {
        let response1 = await fetch('http://localhost:3000/pay_advance')
        setAdvance(await response1.json())
    }
    async function fetchData2() {
        let response2 = await fetch('http://localhost:3000/pay_rent')
        setRent(await response2.json())
    }
    let track = [];
    customer.map((ele, index) => (track.push(ele)))
    advance.map((ele1, index) => (track.push(ele1)))
    rent.map((ele2, index) => (track.push(ele2)))
    //console.log(track)

    let track2 = [];
    for (let element of track) {
        if (Number(element.id) === Number(customerId)) {
            track2.push(element)
        }
    }
    // for(let i=0;i<14;i++){
    //     track2.pop()
    // }

    console.log(track2)

    return (
        <>
            <div className="details_of_tracking">

                <div className='tracking_of_customer_sub'>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>NAME</th>
                                <th>ROOM NUMBER</th>
                                <th>ADVANCE AMOUNT</th>
                                <th>ADVANCE DATE</th>
                                <th>RENT AMOUNT</th>
                                <th>RENT DATE</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                track2.map((ele3, index) => (
                                    //<td key={index}>{ele3}</td>
                                    <tr key={index}>
                                        <td>{ele3?.id}</td>
                                        <td>{ele3?.name}</td>
                                        <td>{ele3?.room_number}</td>
                                        <td>{ele3?.advance_amount}</td>
                                        <td>{ele3?.advance_date}</td>
                                        <td>{ele3?.rent_amount}</td>
                                        <td>{ele3?.rent_date}</td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                    <div className='two_btn'>
                        <input value={customerId} type="number" placeholder='Enter customer id' onChange={
                            (e) => setCustomerId(e.target.value)
                        }
                            required style={{ borderBottom: "2px solid rgb(175, 76, 76)" }
                            } />
                        <button className='btn btn-primary' onClick={handle}>GET DATA</button>
                        {/* <button className='btn btn-danger' onClick={()=>navigate('/New_customer')}>GO TO PAYMENT</button> */}
                    </div>
                </div>
            </div>
        </>
    )
}

export default Tracking_customer