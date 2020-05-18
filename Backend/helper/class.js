const connection = require('./connection');

exports.addClass = (req,res) => {

   let courseName = req.body.course_name
   let semester = req.body.semester
   let department = req.body.department
   let subjectCode = req.body.subject_code

   let post = {
       course_name : courseName,
       semester : semester,
       department : department,
       subject_code : subjectCode
   }


   let sql = 'INSERT INTO class SET ?'
   connection.__query(sql,post).then((resp)=>{

    res.send({
        result : resp,
        status : true,
        statusText : 'New class Added'
    })

   }).catch((err)=>{
       res.send({
           error : err,
           status : false,
           statusText : 'Failed to add a new class'
       })
   })

}


exports.getClasses = (req,res) =>{
    let sql = 'SELECT * FROM class'
    connection.__query(sql).then((resp)=>{

        res.send({
            total_count : resp.length,
            classes : resp,
            status : true,
            statusText : 'Retrieved'

        })
    }).catch((err)=>{
        res.status(500).send({
            error : err,
            status : false,
            statusText : 'Failed to retrieve data'
        })
    })
}

//Get a single class
exports.getClass = (req,res) =>{
    let sql = 'SELECT * FROM class WHERE ?'
    connection.__query(sql, {
        class_id : req.params.id
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
            statusText : 'Failed to get that class'
    
        })
    })
}


//patch a class
exports.updateClass = (req,res)=>{
    let sql = "UPDATE class SET `course_name` = ? , `semester` = ? , `department` = ? ,`subject_code` = ?  WHERE `class_id` = ?"
    connection.__query(sql,[     
        req.body.course_name,
        req.body.semester,
        req.body.department,
        req.body.subject_code,
        req.params.id
    ]).then((resp)=>{
        res.status(200).send({
            result : resp,
            status : true,
            statusText : 'Class data Updated'
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
exports.deleteClass = (req,res) =>{
    let sql = 'DELETE FROM class WHERE ?'
    connection.__query(sql,{
        class_id : req.params.id
    }).then((resp)=>{
        res.status(200).send({
            result : resp,
            status : true,
            statusText : 'class deleted'
        })
    }).catch((err)=>{
        res.status(500).send({
            error: err,
            status : false,
            statusText : 'Failed to delete'
        })
    })

}

