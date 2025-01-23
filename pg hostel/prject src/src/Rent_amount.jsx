import { useState } from "react";
import './Rent_amount.css'

function Rent_amount(props) {
    let [rent_amount, setRent_amount] = useState("")
    let [id,setId]=useState("")
    let [date_time, setDate_time] = useState(new Date().toLocaleString('en-IN', {
        dateStyle: 'medium',
        timeStyle: 'short'
    }));
    let [error, setError] = useState({
        rent_amount:"",
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

    function errorCheck() {
        let hasErrors = false;
        if (id.trim() === "") {
            setError((error) => ({ ...error, id: "Enter id" }))
            hasErrors = true
        }
        else if (id.trim().length < 5) {
            setError((error) => ({ ...error, id: "Enter id of 5 digits" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, id: "" }))
            setId("")
        }
        if (rent_amount.trim() === "") {
            setError((error) => ({ ...error, rent_amount: "Enter rent amount" }))
            hasErrors = true
        }
        else if (Number(rent_amount) < 6000 || Number(rent_amount) > 6000) {
            setError((error) => ({ ...error, rent_amount: "Enter rent of 6000" }))
            hasErrors = true
        }
        else {
            setError(() => ({ ...error, rent_amount: "" }))
            setRent_amount("")
        }
        return hasErrors;
    }

    async function uploadData() {
        let response = await fetch(`http://localhost:3000/pay_rent`, {
            method: 'POST',
            body: JSON.stringify({
                "id": id,
                "rent_amount": rent_amount,
                "rent_date": date_time
            }),
            headers: {
                'contect-type': 'application/json'
            }
        })
        let data = await response.json()
        console.log(data)

        // let response1 = await fetch(`http://localhost:3000/new_customer_details/${id}`, {
        //     method: 'PATCH',
        //     body: JSON.stringify({
        //         "id": id,
        //         "rent_amount": rent_amount,
        //         "rent_date": date_time
        //     }),
        //     headers: {
        //         'contect-type': 'application/json'
        //     }
        // })
        // let data1 = await response1.json()
        // console.log(data1)
    }
    return (
        <>
            <div className="rent_amount">
                <div >
                    <label style={{color:"white",fontWeight:"bolder",fontSize:"25px",marginLeft:"25px"}}>PAY RENT</label>
                    <button className='exit payment_lable' onClick={() => props.close()}>×</button>
                </div>
                <div className='rent_amount_details'>
                    <input value={id} placeholder='Enter id' type="number" aria-label=".form-control-sm example" onChange={(e) => setId(e.target.value)} required />
                    <span className='text-danger'>{error.id}</span>
                </div>
                <div className='rent_amount_input'>
                    <input value={rent_amount}  type="number" placeholder='Enter rent amount' aria-label=".form-control-sm example" onChange={(e) => setRent_amount(e.target.value)} required />
                    <span className='text-danger'>{error.rent_amount}</span>
                </div>
                <div>
                    <button className='btn btn-primary' onClick={handle}>SAVE</button>
                </div>
            </div>
        </>
    )
}
export default Rent_amount