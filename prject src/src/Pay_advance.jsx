import { useState } from 'react'
import './Pay_advance.css'

function Payment_details(props) {
    let [advance_amount, setAdvance_amount] = useState("")
    let [id,setId]=useState("")
    let [date_time, setDate_time] = useState(new Date().toLocaleString('en-IN', {
        dateStyle: 'medium',
        timeStyle: 'short'
    }));
    let [error, setError] = useState({
        advance_amount:"",
        id:""
    })

    function handle() {
        const hasErrors = errorCheck(); // Check for errors
        if (!hasErrors) {
            uploadData(); // Proceed to store data only if no errors
            props.close();
        } else {
            console.log("Fix errors before storing data.");
        }
    }

    async function uploadData() {
        // let response = await fetch('http://localhost:3000/pay_advance', {
        //     method: 'POST',
        //     body: JSON.stringify({
        //         "id":id,
        //         "advance_amount": advance_amount,
        //         "advance_date": date_time
        //     }),
        //     headers: {
        //         'contect-type': 'application/json'
        //     }
        // })
        // let data = await response.json()
        // console.log(data)
        let response1 = await fetch(`http://localhost:3000/new_customer_details/${id}`, {
            method: 'PATCH',
            body: JSON.stringify({
                "advance_amount": advance_amount,
                "advance_date": date_time
            }),
            headers: {
                'contect-type': 'application/json'
            }
        })
        let data1 = await response1.json()
        console.log(data1)
    }

    function errorCheck() {
        let hasErrors = false;
        if (advance_amount.trim() === "") {
            setError((error) => ({ ...error, advance_amount: "Enter advance amount" }))
            hasErrors = true
        }
        else if (Number(advance_amount) < 2000 || Number(advance_amount) > 2000) {
            setError((error) => ({ ...error, advance_amount: "Enter advance of 2000" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, advance_amount: "" }))
            setAdvance_amount("")
        }
        
        if (id.trim() === "") {
            setError((error) => ({ ...error, id: "Enter id" }))
            hasErrors = true
        }
        else if (id.trim().length<5) {
            setError((error) => ({ ...error, id: "Enter id of 5 digits" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, id: "" }))
            setId("")
        }
        return hasErrors

    }
    return (
        <>
            <div className="payment_details">
                <div >
                    <h2 style={{color:"white"}}><strong>PAY ADVANCE</strong></h2>
                    <button className='exit payment_lable' onClick={() => props.close()}>×</button>
                </div>
                <div className='payment_details_input'>
                    <input value={id}  placeholder='Enter id' type="number"   onChange={(e) => setId(e.target.value)} required />
                    <span className='text-danger'>{error.id}</span>
                </div>
                <div className='payment_details_input'>
                    <input value={advance_amount}  type="number"  placeholder='Enter advance amount'  onChange={(e) => setAdvance_amount(e.target.value)} required />
                    <span className='text-danger'>{error.advance_amount}</span>
                </div>
                <div> 
                    <button className='btn btn-success' onClick={handle}>SAVE</button>
                </div>
            </div>
        </>
    )
}
export default Payment_details