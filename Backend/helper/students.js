const connection = require('./connection');

//Adding a new student
exports.addStudent = (req,res)=>{
    let firstName = req.body.student_first_name
    let middleName = req.body.student_middle_name
    let lastName = req.body.student_last_name
    let phone = req.body.student_phone
    let rollNo = req.body.student_roll_no
    let email = req.body.student_email

    let post = {
        student_first_name : firstName,
        student_middle_name : middleName,
        student_last_name : lastName,
        student_phone : phone,
        student_roll_no : rollNo,
        student_email : email
    }

    let sql = 'INSERT INTO students SET ?'
    connection.__query(sql,post).then((resp)=>{
        res.send({
            result : resp,
            status  : true,
            statusText : 'New Student Added'
        })
    }).catch((err)=>{
        console.log(err)
    res.status(500).send({
        error : err,
        status : false,
        statusText : 'Failed to add new student'
    })
    })


}

//Get All students
exports.getAllStudents = (req,res)=>{
    let sql = 'SELECT * FROM students'
    connection.__query(sql).then((resp)=>{
        res.send({
            total_count : resp.length,
            result : resp,
            status  : true,
            statusText : 'All students retrieved'
        })
    }).catch((err)=>{
        console.log(err)
        res.status(500).send({
            error : err,
            status : false,
            statusText : 'Failed to add new student'
        })
    })
}

//Get a single student
exports.getStudent = (req,res) =>{
    let sql = 'SELECT * FROM students WHERE ?'
connection.__query(sql, {
    student_id : req.params.id
}).then((resp)=>{
    if(resp.length==0){
        res.status(404).send({
            status : false,
            statusText : 'Not found'
        })
    }else{
        res.status(200).send({
            result : resp,
            status : true,
            statusText : 'Retrieved Successfully'
        })
    }
}).catch((err)=>{
    res.status(500).send({
        status : false,
        error : err,
        statusText : 'Failed to get student'

    })
})
}

//patch a student
exports.updateStudent = (req,res)=>{
    let sql = "UPDATE students SET `student_first_name` = ? , `student_middle_name` = ? , `student_last_name` = ? ,`student_roll_no` = ?, `student_phone` = ? , `student_email` = ? WHERE `student_id` = ?"
    connection.__query(sql,[     
        req.body.student_first_name,
        req.body.student_middle_name,
        req.body.student_last_name,
        req.body.student_roll_no,
        req.body.student_phone,
        req.body.student_email,
        req.params.id
    ]).then((resp)=>{
        res.send({
            result : resp,
            status : true,
            statusText : 'Student data Updated'
        })
    }).catch((err)=>{
        res.status(500).send({
            error : err,
            status : false,
            statusText : 'Some Error Occured'
        })
    })
}
//Delete a student
exports.deleteStudent = (req,res) =>{
    let sql = 'DELETE FROM students WHERE ?'
    connection.__query(sql,{
        student_id : req.params.id
    }).then((resp)=>{
        res.send({
            result : resp,
            status : true,
            statusText : 'Student deleted'
        })
    }).catch((err)=>{
        res.status(500).send({
            error: err,
            status : false,
            statusText : 'Failed to deleted'
        })
    })

}

