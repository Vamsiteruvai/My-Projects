import { useState } from 'react';
import './Room_management.css';

function Room_management() {
    const [room, setRoom] = useState([]);
    const [room1, setRoom1] = useState([]);
    const [room2, setRoom2] = useState([]);
    const [room3, setRoom3] = useState([]);
    const [room4, setRoom4] = useState([]);

    const [clickCount, setClickCount] = useState(0);

    async function fetchRoomInfo() {
        const response = await fetch('http://localhost:3000/new_customer_details');
        const data = await response.json();
        setRoom(data);
    }

    function roomValidate() {
        room.forEach((ele) => {
            if (ele.room_number === "101" && room1.length < 4) {
                setRoom1((prev) => [...prev, ele.id].slice(0, 4)); // Enforce max size of 4
            } else if (ele.room_number === "102" && room2.length < 4) {
                setRoom2((prev) => [...prev, ele.id].slice(0, 4)); // Enforce max size of 4
            } else if (ele.room_number === "103" && room3.length < 4) {
                setRoom3((prev) => [...prev, ele.id].slice(0, 4)); // Enforce max size of 4
            } else if (ele.room_number === "104" && room4.length < 4) {
                setRoom4((prev) => [...prev, ele.id].slice(0, 4)); // Enforce max size of 4
            } else {
                console.log(`Room ${ele.room_number} is full or doesn't exist`);
            }
        });
    }

    function clickHandle() {
        setClickCount((prev) => prev + 1);
    }

    function handle() {
        clickHandle();
        fetchRoomInfo().then(() => roomValidate());
    }

    return (
        <>
            <div className="room_management_container">
                <div className="room_management_subcontainer">
                    <label className="room_management_label">ROOM MANAGEMENT</label>
                    <div className="room">
                        <label className="room_number_label">101</label>
                        <div>
                            {room1.map((e, i) => (
                                <p key={i} className="room_info">{e}</p>
                            ))}
                        </div>
                    </div>
                    <div className="room">
                        <label className="room_number_label">102</label>
                        <div>
                            {room2.map((e, i) => (
                                <p key={i} className="room_info">{e}</p>
                            ))}
                        </div>
                    </div>
                    <div className="room">
                        <label className="room_number_label">103</label>
                        <div>
                            {room3.map((e, i) => (
                                <p key={i} className="room_info">{e}</p>
                            ))}
                        </div>
                    </div>
                    <div className="room">
                        <label className="room_number_label">104</label>
                        <div>
                            {room4.map((e, i) => (
                                <p key={i} className="room_info">{e}</p>
                            ))}
                        </div>
                    </div>
                </div>
                <button className="room_btn" onClick={handle} disabled={clickCount >= 2}>
                    GET ROOM INFO
                </button>
                <span className="room_note">NOTE: please double-click the button</span>
            </div>
        </>
    );
}

export default Room_management;
