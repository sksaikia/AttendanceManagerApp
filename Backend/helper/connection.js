const mysql = require('mysql')

const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password:'',
    database:'Attendance'
});

db.connect((err)=>{
    if(err){
    
        throw err;
    }else{
        console.log("Mysql connected")
    }
})

const __query = (sql, params) => {
    return new Promise((resolve,reject)=> {
        db.query(sql,params,(err, result) => {
            if(err){
                reject(err)
            }
            resolve(result)
        })
    })
}

module.exports = {db, __query};