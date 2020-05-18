const connection = require('./connection');

exports.addTeacher = (req,res) =>{

let firstName = req.body.teacher_first_name;
let middleName = req.body.teacher_middle_name;
let lastName = req.body.teacher_last_name;

let post = {
    teacher_first_name : firstName,
    teacher_middle_name : middleName,
    teacher_last_name : lastName
}


let sql = 'INSERT INTO teachers SET ?'
connection.__query(sql,post).then ((resp)=>{
    res.send({
        result : resp,
        status  : true,
        statusText : 'New Teacher Added'
    })
}).catch((err)=>{
    console.log(err)
    res.status(500).send({
        status : false,
        statusText : 'Failed to add new teacher'
    })
})

}

//Get all Teachers
exports.getAllTeachers = (req,res) =>{

    let sql = 'SELECT * FROM teachers'
    connection.__query(sql).then((resp)=>{
        res.send({
            status : true,
            statusText : 'Successfully retrieved',
            result : resp
        })
    }).catch((err)=>{
        console.log(err)
        res.status(500).send({
            status : false,
            statusText : 'Failed to retrieve data'
        })

    })
}

exports.getTeacher = (req,res) =>{
    let sql = `SELECT * FROM teachers WHERE ?`;
    connection.__query(sql, {
        teacher_id : req.params.id
    }).then((resp)=>{
        if(resp.length==0){
            res.status(404).send({
                status : false,
                statusText : 'Not found'
            })
        }else{
        res.send({
            result : resp,
            status : true,
            statusText : 'Retrieved Successfully'
        })
    }
    }).catch((err)=>{
        res.status(500).send({
            status : false,
            error : err,
            statusText : 'Failed to get Teacher'

        })
    })
}

exports.updateTeacher = (req,res) =>{
    let sql = "UPDATE teachers SET `teacher_first_name` = ? , `teacher_middle_name` = ? , `teacher_last_name` = ? WHERE `teacher_id` = ?"
    connection.__query(sql,[     
        req.body.teacher_first_name,
        req.body.teacher_middle_name,
        req.body.teacher_last_name,
        req.params.id
    ]).then((resp)=>{
        console.log(resp)
        res.send({
            result : resp,
            status : true,
            statusText : 'Fields Updated'
        })
    }).catch((err)=>{
        console.log(err)
        res.status(500).send({
            error : err,
            status : false,
            statusText :'Error Occured'
        })
    })
}

exports.deleteTeacher = (req,res) =>{
    let sql = 'DELETE from teachers WHERE ?'
    connection.__query(sql,{
        teacher_id : req.params.id
    }).then((resp)=>{
        console.log(resp)
        res.send({
            result : resp,
            status : true,
            statusText : 'Field Deleted'
        })
    }).catch((err)=>{
        console.log(err)
        res.status(500).send({
            error : err,
            status : false,
            statusText :'Error Occured'
        })
    })
}
