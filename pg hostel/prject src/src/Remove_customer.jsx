import { useState } from 'react'
import './Remove_customer.css'

function Remove_customer() {
    let [data, setData] = useState(' ')
    const [message, setMessage] = useState(' ');

    function handle() {
        if (data.trim() === '') {
            setMessage('Customer ID is required!');
            return;
        }
        // confirm("Are you sure to REMOVE")
        removeCustomer();
        setData("")
        alert("REMOVED SUCCESSFULLY")
    }
    async function removeCustomer() {
        try {
            const response = await fetch(`http://localhost:3000/new_customer_details/${data}`, {
                method: 'DELETE',
            });
            const response1 = await fetch(`http://localhost:3000/pay_rent`)
            const data8 = await response1.json();


            //console.log(data8)
            for (let i = 0; i < data8.length; i++) {
                await fetch(`http://localhost:3000/pay_rent/${data}`, { method: 'DELETE' }); // Assuming each object has an 'id' field
            }
        } catch (error) {
            setMessage('An error occurred while removing the customer.');
            console.error(error);
        }
    }
    return (
        <>
            <div className="remove_customer">
                <div className='remove_customer_sub'>
                    <label><strong>REMOVE CUSTOMER</strong></label>
                    <div className='remove_customer_inner'>
                        <div>
                            <input value={data} type="number"  placeholder="User ID"   onChange={(e) => setData(e.target.value)} />
                        </div>
                        <button type="button" onClick={handle} value={data} disabled={!data.trim()}>REMOVE</button>
                    </div>
                    {
                        message && <p style={{color:"red"}}>{message}</p>
                    }
                </div>
            </div>
        </>
    )
}
export default Remove_customer