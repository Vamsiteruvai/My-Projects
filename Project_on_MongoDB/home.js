const express = require('express');
const { MongoClient } = require('mongodb');
const cors = require('cors');

const app = express();

app.use(cors({ origin: "*" }));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

const url = 'mongodb://127.0.0.1:27017';
const client = new MongoClient(url);

async function run() {
    try {
        await client.connect();
        console.log("Connected to MongoDB");

        const db = client.db('college');
        const collection = db.collection('branchAllocation');

        app.post('/postDetails', async (req, res) => {
            const csecut = 90;
            const ececut = 80;
            const eeecut = 70;
            const civilcut = 60;
            const mechcut = 50;

            const marks = Number(req.body.MARKS);
            let setBranch = '';

            try {
                if (marks >= 50 && marks <= 100) {
                    if (marks >= csecut) setBranch = 'CSE';
                    else if (marks >= ececut) setBranch = 'ECE';
                    else if (marks >= eeecut) setBranch = 'EEE';
                    else if (marks >= civilcut) setBranch = 'CIVIL';
                    else if (marks >= mechcut) setBranch = 'MECH';

                    await collection.insertOne({
                        id: req.body.ID,
                        name: req.body.NAME,
                        branch: setBranch
                    });

                    res.send({ message: "Inserted" });
                } else {
                    res.send({ message: "Above 100 or below 50 is not Allowed" });
                }
            } catch (err) {
                res.send({ message: err.message });
            }
        });

        app.get('/getDetails', async (req, res) => {
            try {
                const data = await collection.find().toArray();
                res.send(data);
            } catch (err) {
                res.send({ message: err.message });
            }
        });

        app.listen(8888, () => {
            console.log("Server started on port 8888");
        });

    } catch (err) {
        console.error(err);
    }
}

run();
