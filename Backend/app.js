const express = require('express')
const bodyparser = require("body-parser");


const app = express();

app.use(bodyparser.json());
app.use(
  bodyparser.urlencoded({
    extended: false
  })
);


//Create Table
// app.get('/createPostTable',(req,res)=>{
//     let sql = 'CREATE TABLE posts(id INT AUTO_INCREMENT PRIMARY KEY,title VARCHAR(255),body VARCHAR(255));'
//     db.query(sql,(err,result)=>{
//         if(err)
//             throw err;
//         console.log(result);
//         res.send('Posts table created')

//     })
// })


const authRoutes = require('./routes/auth')
app.use(`/api/auth`, authRoutes);

const postRoutes = require('./routes/post')
app.use(`/api/posts`,postRoutes);

const teacherRoutes = require('./routes/teachers')
app.use(`/api/teachers`,teacherRoutes)

const studentRoutes = require('./routes/students')
app.use(`/api/students`,studentRoutes)

const classRoutes = require('./routes/class')
app.use(`/api/class`,classRoutes)

const sessionRoutes = require('./routes/session')
app.use(`/api/session`,sessionRoutes)


app.listen('3000',()=>{
    console.log("Server started on port 3000")

})