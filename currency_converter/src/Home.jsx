import React, { useEffect, useState } from "react";
import "./Home.css";

// Currency to country code mapping
const currencyToCountryCode = {
    AED: "AE",
    AFN: "AF",
    XCD: "AG",
    ALL: "AL",
    AMD: "AM",
    ANG: "AN",
    AOA: "AO",
    AQD: "AQ",
    ARS: "AR",
    AUD: "AU",
    AZN: "AZ",
    BAM: "BA",
    BBD: "BB",
    BDT: "BD",
    XOF: "BE",
    BGN: "BG",
    BHD: "BH",
    BIF: "BI",
    BMD: "BM",
    BND: "BN",
    BOB: "BO",
    BRL: "BR",
    BSD: "BS",
    NOK: "BV",
    BWP: "BW",
    BYR: "BY",
    BZD: "BZ",
    CAD: "CA",
    CDF: "CD",
    XAF: "CF",
    CHF: "CH",
    CLP: "CL",
    CNY: "CN",
    COP: "CO",
    CRC: "CR",
    CUP: "CU",
    CVE: "CV",
    CYP: "CY",
    CZK: "CZ",
    DJF: "DJ",
    DKK: "DK",
    DOP: "DO",
    DZD: "DZ",
    ECS: "EC",
    EEK: "EE",
    EGP: "EG",
    ETB: "ET",
    EUR: "FR",
    FJD: "FJ",
    FKP: "FK",
    GBP: "GB",
    GEL: "GE",
    GGP: "GG",
    GHS: "GH",
    GIP: "GI",
    GMD: "GM",
    GNF: "GN",
    GTQ: "GT",
    GYD: "GY",
    HKD: "HK",
    HNL: "HN",
    HRK: "HR",
    HTG: "HT",
    HUF: "HU",
    IDR: "ID",
    ILS: "IL",
    INR: "IN",
    IQD: "IQ",
    IRR: "IR",
    ISK: "IS",
    JMD: "JM",
    JOD: "JO",
    JPY: "JP",
    KES: "KE",
    KGS: "KG",
    KHR: "KH",
    KMF: "KM",
    KPW: "KP",
    KRW: "KR",
    KWD: "KW",
    KYD: "KY",
    KZT: "KZ",
    LAK: "LA",
    LBP: "LB",
    LKR: "LK",
    LRD: "LR",
    LSL: "LS",
    LTL: "LT",
    LVL: "LV",
    LYD: "LY",
    MAD: "MA",
    MDL: "MD",
    MGA: "MG",
    MKD: "MK",
    MMK: "MM",
    MNT: "MN",
    MOP: "MO",
    MRO: "MR",
    MTL: "MT",
    MUR: "MU",
    MVR: "MV",
    MWK: "MW",
    MXN: "MX",
    MYR: "MY",
    MZN: "MZ",
    NAD: "NA",
    XPF: "NC",
    NGN: "NG",
    NIO: "NI",
    NPR: "NP",
    NZD: "NZ",
    OMR: "OM",
    PAB: "PA",
    PEN: "PE",
    PGK: "PG",
    PHP: "PH",
    PKR: "PK",
    PLN: "PL",
    PYG: "PY",
    QAR: "QA",
    RON: "RO",
    RSD: "RS",
    RUB: "RU",
    RWF: "RW",
    SAR: "SA",
    SBD: "SB",
    SCR: "SC",
    SDG: "SD",
    SEK: "SE",
    SGD: "SG",
    SKK: "SK",
    SLL: "SL",
    SOS: "SO",
    SRD: "SR",
    STD: "ST",
    SVC: "SV",
    SYP: "SY",
    SZL: "SZ",
    THB: "TH",
    TJS: "TJ",
    TMT: "TM",
    TND: "TN",
    TOP: "TO",
    TRY: "TR",
    TTD: "TT",
    TWD: "TW",
    TZS: "TZ",
    UAH: "UA",
    UGX: "UG",
    USD: "US",
    UYU: "UY",
    UZS: "UZ",
    VEF: "VE",
    VND: "VN",
    VUV: "VU",
    YER: "YE",
    ZAR: "ZA",
    ZMK: "ZM",
    ZWD: "ZW",
};

// Function to get flag URL
const getFlagUrl = (currencyCode) => {
    const countryCode = currencyToCountryCode[currencyCode] || "us"; // Default to US flag if not found
    return `https://flagcdn.com/w40/${countryCode.toLowerCase()}.png`;
};
const Home = () => {
    const [amount, setAmount] = useState("");
    const [fromCurrency, setFromCurrency] = useState("USD");
    const [toCurrency, setToCurrency] = useState("INR");
    const [currencies, setCurrencies] = useState([]);
    const [exchangeRates, setExchangeRates] = useState({});
    const [convertedAmount, setConvertedAmount] = useState("--");
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const getData = async () => {
            try {
                const response = await fetch("https://api.exchangerate-api.com/v4/latest/USD");
                const data = await response.json();
                const allCurrencies = Object.keys(data.rates);
                setCurrencies(allCurrencies);
                setExchangeRates(data.rates);
            } catch (error) {
                console.error("Error fetching exchange rates:", error);
            } finally {
                setIsLoading(false);
            }
        };
        getData();
    }, []);

    useEffect(() => {
        convertCurrency();
    }, [amount, fromCurrency, toCurrency]);

    const convertCurrency = () => {
        if (!amount || isNaN(amount)) {
            setConvertedAmount("Invalid amount");
            return;
        }
        if (!exchangeRates[fromCurrency] || !exchangeRates[toCurrency]) {
            setConvertedAmount("Error fetching rates");
            return;
        }
        const rate = exchangeRates[toCurrency] / exchangeRates[fromCurrency];
        setConvertedAmount((amount * rate).toFixed(2));
    };

    const swapCurrencies = () => {
        setFromCurrency(toCurrency);
        setToCurrency(fromCurrency);
    };

    if (isLoading) {
        return <div className="loading">Loading...</div>;
    }

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
                        <img src={getFlagUrl(fromCurrency)} alt={fromCurrency} className="flag" />
                        <select value={fromCurrency} onChange={(e) => setFromCurrency(e.target.value)}>
                            {currencies.map((ele, i) => (
                                <option key={i} value={ele}>{ele}</option>
                            ))}
                        </select>
                    </div>
                    <span className="icon" onClick={swapCurrencies} aria-label="Swap currencies">⇆</span>
                    <div className="currency-option">
                        <img src={getFlagUrl(toCurrency)} alt={toCurrency} className="flag" />
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