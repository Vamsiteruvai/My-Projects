import express from 'express';
import mysql2 from 'mysql2';
import cors from 'cors';
import multer from 'multer';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
app.use(cors("*"))
app.use(express.urlencoded({ extended: true }))
app.use("/uploads", express.static(path.join(__dirname, "public/uploads")));

app.use(express.json())

app.listen(4500, () => {
    console.log("server started")
})




const con = mysql2.createConnection({
    host: "localhost",
    user: "root",
    password: "@@@Vamsi143",
    database: "woodworks"
})

con.connect((err) => {
    if (err) {
        console.error("Database connection failed: " + err.stack);
        return;
    }
    console.log("Connected to MySQL Database");
});


const storage = multer.diskStorage({
    destination: (req, file, cb) => {
        cb(null, "./sourcefiles/public/uploads/")
    },
    filename: (req, file, cb) => {
        cb(null, Date.now() + file.originalname)
    }
})

const upload = multer({
    storage: storage
})

//api for post user/admin info
app.post("/upload", (req, res) => {
    const { username, email, password } = req.body;
    if (!username || !email || !password) {
        return res.status(400).json({ message: "All fields are required" });
    }

    const sql = "INSERT INTO user (user_name, email, password) VALUES (?, ?, ?)";
    con.query(sql, [username, email, password], (err, result) => {
        if (err) {
            return res.status(500).json({ message: "Database error", error: err });
        }
        res.status(201).json({ message: "User added successfully", userId: result.insertId });
    });
});

//api for admin login and some other purppose
const adminCode=8;
app.get('/get', (req, res) => {
    const sql1 = "select email,password from user where user_id = ?";
    con.query(sql1,[adminCode], (err, result) => {
        if (err) {
            res.send("error")
        }
        else {
            res.send(result)
        }
    })
})

//api for getting userinfo
app.get('/getusers', (req, res) => {
    const sql1 = "select * from user";
    con.query(sql1, (err, result) => {
        if (err) {
            res.send("error")
        }
        else {
            res.send(result)
        }
    })
})

// API to Delete product(delete)
app.delete("/delete/:id", (req, res) => {
    const sql = "SELECT image_url FROM product WHERE product_id = ?";

    con.query(sql, [req.params.id], (err, result) => {
        if (err) {
            console.error("Database error:", err);
            return res.status(500).send("Database error");
        }

        if (result.length === 0) {
            return res.status(404).send("Image Not Found in Database");
        }

        const imagePath = `./sourcefiles/public/uploads/${result[0].image_url}`;

        // First, delete record from MySQL
        const deleteSql = "DELETE FROM product WHERE product_id = ?";
        con.query(deleteSql, [req.params.id], (err, result) => {
            if (err) {
                console.error("Error deleting from database:", err);
                return res.status(500).send("Error deleting image record from database");
            }

            // After MySQL deletion, delete the image file
            if (fs.existsSync(imagePath)) {
                fs.unlink(imagePath, (err) => {
                    if (err) {
                        console.error("Error deleting file:", err);
                        return res.status(500).send("Image deleted from database, but error deleting file.");
                    }
                    res.send("Image deleted successfully from database and uploads folder.");
                });
            } else {
                res.send("Image deleted from database, but file not found in folder.");
            }
        });
    });
});



// API for products(post)
app.post("/uploadProduct", upload.single("image_url"), (req, res) => {
    const { productname, price, rating, image_url } = req.body;
    if (!productname || !price || !rating) {
        return res.status(400).json({ message: "All fields are required" });
    }


    const imageUrl = req.file.filename;
    const sql = "INSERT INTO product (product_name,price,rating,image_url) VALUES (?,?,?,?)";

    con.query(sql, [productname, price, rating, imageUrl], (err, result) => {
        if (err) {
            console.error("Database Error:", err);
            return res.status(500).json({ message: "Database error", error: err });
        }
        res.status(201).json({ message: "Image uploaded successfully", imageUrl });
    });
});

//api for get products
app.get('/getproducts', (req, res) => {
    const sql1 = "select * from product";
    con.query(sql1, (err, result) => {
        if (err) {
            res.send("error")
        }
        else {
            res.send(result)
        }
    })
})

//api for get products based on product id
app.get('/getproducts/:id', (req, res) => {
    const sql1 = "select * from product where product_id = ?";
    con.query(sql1, [req.params.id], (err, result) => {
        if (err) {
            res.send("error")
        }
        else {
            res.send(result)
        }
    })
})


// PATCH API to Update Product (Including Image)
app.patch("/putproducts/:id", upload.single("image_url"), (req, res) => {
    const productId = req.params.id;
    const { productname, price, rating } = req.body;
    const newImage = req.file ? req.file.filename : null;

    // Fetch existing image from the database
    const selectSql = "SELECT image_url FROM product WHERE product_id = ?";
    con.query(selectSql, [productId], (err, results) => {
        if (err) {
            console.error("Error fetching image:", err);
            return res.status(500).json({ message: "Database error", error: err });
        }

        if (results.length === 0) {
            return res.status(404).json({ message: "Product not found" });
        }

        const oldImage = results[0].image_url;

        // Update product details and image if a new one is provided
        const updateSql = newImage
            ? "UPDATE product SET product_name = ?, price = ?, rating = ?, image_url = ? WHERE product_id = ?"
            : "UPDATE product SET product_name = ?, price = ?, rating = ? WHERE product_id = ?";

        const queryParams = newImage
            ? [productname, price, rating, newImage, productId]
            : [productname, price, rating, productId];

        con.query(updateSql, queryParams, (err, result) => {
            if (err) {
                console.error("Database update error:", err);
                return res.status(500).json({ message: "Database update failed", error: err });
            }

            if (result.affectedRows === 0) {
                return res.status(404).json({ message: "Product not found" });
            }

            // Delete old image if a new one is uploaded
            if (newImage && oldImage) {
                const oldImagePath = `./public/uploads/${oldImage}`;
                if (fs.existsSync(oldImagePath)) {
                    fs.unlinkSync(oldImagePath);
                }
            }

            res.status(200).json({ message: "Product updated successfully", newImage });
        });
    });
});

//api for adding data to add_to_kart
app.post('/add_to_kart', (req, res) => {
    const { user_id, product_id, name, price, quantity, image } = req.body;

    if (!user_id || !product_id || !name || !price || !quantity || !image) {
        return res.status(400).json({ success: false, message: "Missing required fields" });
    }

    const postQuery = "INSERT INTO kart (product_id, quantity, image_url, product_name, price, user_id) VALUES (?, ?, ?, ?, ?, ?)";

    con.query(postQuery, [product_id, quantity, image, name, price, user_id], (err, result) => {
        if (err) {
            console.error("Database Insert Error:", err);
            return res.status(500).json({ success: false, error: err });
        }
        res.json({ success: true, message: "Item added to cart", result });
    });
});


//api for updating quantity to add_to_kart
app.patch('/add_to_kart', (req, res) => {
    const { quantity, user_id, product_id } = req.body;

    if (!quantity) {
        return res.status(400).json({ success: false, message: "Missing required fields" });
    }

    const updateQuery = "UPDATE kart SET quantity = ? WHERE user_id = ? AND product_id = ?";

    con.query(updateQuery, [quantity, user_id, product_id], (err, result) => {
        if (err) {
            console.error("Database Insert Error:", err);
            return res.status(500).json({ success: false, error: err });
        }
        res.json({ success: true, message: "Item added to cart", result });
    });
});

//api for getting cart items
app.get('/getcartitems/:id', (req, res) => {
    const getCart = "SELECT * FROM kart WHERE user_id = ?";

    con.query(getCart, [req.params.id], (err, result) => {
        if (err) {
            res.send(err)
        } else {
            res.send(result)
        }
    })
})


//api for get cart items
app.get('/getcartupdateditems/:id', (req, res) => {

    const getCart = "SELECT user_id, product_id, image_url, quantity, product_name, price FROM kart WHERE user_id = ?";

    con.query(getCart, [req.params.id], (err, result) => {
        if (err) {
            res.send(err)
        } else {
            res.send(result)
        }
    })
})


//api for remove cart items
app.delete('/removeCartItem/:id', (req, res) => {

    const {product_id}=req.body;

    const getCart = "delete from kart where user_id = ? and product_id = ?";

    con.query(getCart, [req.params.id,product_id], (err, result) => {
        if (err) {
            res.send(err)
        } else {
            res.send(result)
        }
    })
})

//api for remove kart while removing product
app.delete('/removekart/:id', (req, res) => {

    const getCart = "delete from kart where product_id = ?";

    con.query(getCart, [req.params.id], (err, result) => {
        if (err) {
            res.send(err)
        } else {
            res.send(result)
        }
    })
})

//api for placeOrder // 

app.post('/placeOrder', (req, res) => {
    const orderItems = req.body;

    if (!orderItems || orderItems.length === 0) {
        return res.status(400).json({ message: "Cart is empty" });
    }

    //  Step 1: Insert into Orders Table
    const insertQuery = "INSERT INTO orders (user_id, product_id, product_name, quantity, price) VALUES ?";
    const values = orderItems.map(item => [item.user_id, item.product_id, item.product_name, item.quantity, item.total_price]);

    con.query(insertQuery, [values], (err, result) => {
        if (err) {
            console.error("Error placing order:", err);
            return res.status(500).json({ message: "Database error", error: err });
        }

        //  Step 2: Delete Products from Cart after Order Placement
        const userId = orderItems[0].user_id; // Since all items belong to the same user
        const productIds = orderItems.map(item => item.product_id); // Get all ordered product IDs

        const deleteQuery = "DELETE FROM kart WHERE user_id = ? AND product_id IN (?)";
        con.query(deleteQuery, [userId, productIds], (delErr, delResult) => {
            if (delErr) {
                console.error("Error deleting from cart:", delErr);
                return res.status(500).json({ message: "Cart deletion error", error: delErr });
            }

            res.json({ message: "Order placed successfully, items removed from cart", orderResult: result, deleteResult: delResult });
        });
    });
});

//api for all order details
app.get('/getOrders',(req,res)=>{
    const sqlQuery="select * from orders";
    con.query(sqlQuery,(err,result)=>{
        if(err){
            res.send(err)
        }else{
            res.send(result)
        }
    })
})

//api for no of products
app.get('/noofproducts',(req,res)=>{

    const sqlQuery=`select product_name,(count(*)) as total_sales 
                    from orders 
                    where product_name = ?
                    group by product_name
                    order by total_sales desc`;
    con.query(sqlQuery,[req.query.name],(err,result)=>{
        if(err){
            res.send(err)
        }else{
            res.send(result)
        }
    })
})

//api for productMax
app.get('/getproductmax',(req,res)=>{

    const sqlQuery=`select product_name,(sum(quantity)) as total_sales 
                    from orders
                    group by product_name
                    order by total_sales desc`;
    con.query(sqlQuery,(err,result)=>{
        if(err){
            res.send(err)
        }else{
            res.send(result)
        }
    })
})


//api for mostly purchased customers
app.get('/mostpurchased',(req,res)=>{

    const sqlQuery=`select u.user_name,o.product_name,(sum(quantity)) as total_sales 
                    from orders o inner join user u
                    on o.user_id=u.user_id
                    group by o.product_name,u.user_id
                    order by total_sales desc`;
    con.query(sqlQuery,(err,result)=>{
        if(err){
            res.send(err)
        }else{
            res.send(result)
        }
    })
})

//api for post rating
app.patch('/postrating/:id',(req,res)=>{
    const {rating}=req.body

    const sqlQuery="update user set rating=? where user_id = ?";
    con.query(sqlQuery,[rating,req.params.id],(err,result)=>{
        if(err){
            res.send(err)
        }
        else{
            res.send(result)
        }
    })
})


