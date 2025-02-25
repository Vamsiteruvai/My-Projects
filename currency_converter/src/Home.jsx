import React, { useEffect, useState } from "react";
import "./Home.css";

const currencyFlags = {
    USD: "https://flagcdn.com/w40/us.png",
    INR: "https://flagcdn.com/w40/in.png",
};

const Home = () => {
    const [amount, setAmount] = useState("");
    const [fromCurrency, setFromCurrency] = useState("USD");
    const [toCurrency, setToCurrency] = useState("INR");
    const [currencies, setCurrencies] = useState([]);
    const [exchangeRates, setExchangeRates] = useState({});
    const [convertedAmount, setConvertedAmount] = useState("--");

    useEffect(() => {
        const getData = async () => {
            try {
                const response = await fetch("https://api.exchangerate-api.com/v4/latest/USD");
                const data = await response.json();
                setCurrencies(Object.keys(data.rates));
                setExchangeRates(data.rates);
            } catch (error) {
                console.error("Error fetching exchange rates:", error);
            }
        };
        getData();
    }, []);

    const convertCurrency = () => {
        if (!amount || isNaN(amount)) return;
        const rate = exchangeRates[toCurrency] / exchangeRates[fromCurrency];
        setConvertedAmount((amount * rate).toFixed(2));
    };
    return (
        <div className="container">
            <div className="card">
                <h1 className="title">Currency Converter</h1>
                <div className="input-group">
                    <input
                        type="number"
                        placeholder="Enter amount"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        className="input"
                    />
                </div>
                <div className="currency-select">
                    <div className="currency-option">
                        <img src={currencyFlags[fromCurrency]} alt={fromCurrency} className="flag" />
                        <select value={fromCurrency} onChange={(e) => setFromCurrency(e.target.value)}>
                            {currencies.map((ele, i) => (
                                <option key={i} value={ele}>{ele}</option>
                            ))}
                        </select>
                    </div>
                    <span className="icon">⇆</span>
                    <div className="currency-option">
                        <img src={currencyFlags[toCurrency]} alt={toCurrency} className="flag" />
                        <select value={toCurrency} onChange={(e) => setToCurrency(e.target.value)}>
                            {currencies.map((ele, i) => (
                                <option key={i} value={ele}>{ele}</option>
                            ))}
                        </select>
                    </div>
                </div>
                <button className="convert-btn" onClick={convertCurrency}>Convert</button>
                <div className="converted-amount">Converted Amount: {convertedAmount}</div>
            </div>
        </div>
    );
};

export default Home;
