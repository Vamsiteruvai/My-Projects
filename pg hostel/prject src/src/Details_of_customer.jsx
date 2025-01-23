import { useState } from 'react';
import './Details_of_customer.css'
import { useNavigate } from 'react-router-dom';

function Details_of_customer() {
    let [data, setData] = useState([])
    let navigate = useNavigate()
    async function fetchData() {
        let response = await fetch('http://localhost:3000/new_customer_details')
        setData(await response.json())
    }
    return (
        <>
            <div className="details_of_customer">
                <div className='details_of_customer_sub'>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>NAME</th>
                                <th>EMAIL</th>
                                <th>PHONE</th>
                                <th>ADHAAR NUMBER</th>
                                <th>ADDRESS</th>
                                <th>FATHER NAME</th>
                                <th>FATHER PHONE</th>
                                <th>ROOM NUMBER</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                data.map((ele, index) => (
                                    <tr key={index}>
                                        <td>{ele.id}</td>
                                        <td>{ele.name}</td>
                                        <td>{ele.email}</td>
                                        <td>{ele.phone}</td>
                                        <td>{ele.adhaar}</td>
                                        <td>{ele.address}</td>
                                        <td>{ele.father_name}</td>
                                        <td>{ele.father_phone}</td>
                                        <td>{ele.room_number}</td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                    <div className='two_btn'>
                        <button className='btn btn-primary' onClick={fetchData}>GET DATA</button>
                        <button className='btn btn-danger' onClick={() => navigate('/New_customer')}>GO TO PAYMENT</button>
                        <button className='btn btn-primary' onClick={() => navigate('/Tracking_customer')}>GO TO TRACKING</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Details_of_customer