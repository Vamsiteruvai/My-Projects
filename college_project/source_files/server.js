import express from 'express';
import mysql2 from 'mysql2';
import cors from 'cors';
import multer from 'multer';
import fs from 'fs';
import path from 'path';
import { error } from 'console';


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

//post user details
app.post("/postUserDetails", (req, res) => {
    const { username, email, password } = req.body;
    if (!username || !email || !password) {
        return res.status(400).json({ message: "All fields are required" });
    }

    const sql = "INSERT INTO authenticate (fullname, email, password) VALUES (?, ?, ?)";
    con.query(sql, [username, email, password], (err, result) => {
        if (err) {
            return res.status(500).json({ message: "Database error", error: err });
        }
        res.status(201).json({ message: "User added successfully", userId: result.insertId });
    });
});

//get user details
app.get("/getUserDetails",(req,res)=>{
    const sql="select email,password,fullname from authenticate";
    con.query(sql,(err,result)=>{
        if(err){
            return res.status(500).json({message:"database error",error:err});
        }else{
            res.send(result)
        }
    })
})