import express from 'express';
import mysql2 from 'mysql2';
import cors from 'cors';
import multer from 'multer';
import fs from 'fs';
import path from 'path';


const app=express();
app.use(cors('*'));
app.use(express.urlencoded({extended:true}));
app.use(express.json());

app.listen(3500,(req,res)=>{
    console.log("server started");
})

const con = mysql2.createConnection({
    host: "localhost",
    user: "root",
    password: "@@@Vamsi143",
    database: "osndata"
})

con.connect((err) => {
    if (err) {
        console.error("Database connection failed: " + err.stack);
        return;
    }
    console.log("Connected to MySQL Database");
});