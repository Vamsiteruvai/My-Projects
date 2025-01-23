import pre from './pre.jpg'
import './Home.css'
import { useState } from 'react'
import Hostel_main from './Hostel_main'
import Login_form from './Login-form'



function Home() {
    let [login, setLogin] = useState(false)
    return (
        <>
            <div className='home-container'>
                <div className='pg-container'>
                    <label className='pg-heading'>PG HOSTEL</label>
                    <button className='btn btn-danger'onClick={()=>{
                        setLogin(!login)
                    }}>Login</button>
                </div>
                {/* <div>
                    <img className='image' src={pre} alt="Hostel view" height="650" width="800" />
                </div> */}
                {
                    login&&(<Login_form close={setLogin}/>)
                }

            </div>
        </>
    )
}
export default Home