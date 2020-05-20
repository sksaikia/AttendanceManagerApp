const connection = require('./connection');
//Add Something to the routine
exports.addRoutine = (req,res) => {

    let teacher_id = req.body.teacher_id
    let class_id = req.body.class_id
    let session_id  = req.body.session_id
    let day = req.body.day
    let startTime = req.body.start_time
    let endTime = req.body.end_time

    let sql = 'INSERT INTO routine SET ?'

    let post = {
        teacher_id : teacher_id,
        class_id : class_id,
        session_id : session_id,
        day : day,
        start_time : startTime,
        end_time : endTime
    }
    connection.__query(sql,post).then((resp)=>{

        res.status(200).send({
            result : resp,
            status : true,
            statusText : 'New Routine Added'
        })

    }).catch((err)=>{
        res.status(500).send({
            error : err,
            status : false,
            statusText : 'Failed to add new routine'
        })

    })

}

//Gets all the routines

exports.getRoutines = (req,res)=>{
    let sql = 'SELECT * FROM routine'
    connection.__query(sql).then((resp)=>{
        res.send({
            total_count : resp.length,
            result : resp,
            status  : true,
            statusText : 'All Routines retrieved'
        })
    }).catch((err)=>{
        console.log(err)
        res.status(500).send({
            error : err,
            status : false,
            statusText : 'Failed to add routine'
        })
    })
}


//Get a single session
exports.getRoutine = (req,res) =>{
    let sql = 'SELECT * FROM routine WHERE ?'
connection.__query(sql, {
    routine_id : req.params.id
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
        statusText : 'Failed to get routine'

    })
})
}

//patch a session
exports.updateRoutine = (req,res)=>{
    let sql = "UPDATE routine SET `teacher_id` = ? , `class_id` = ? , `session_id` = ? ,`day` = ?, `start_time` = ? , `end_time` = ? WHERE `routine_id` = ?"
    connection.__query(sql,[     
        req.body.teacher_id,
        req.body.class_id,
        req.body.session_id,
        req.body.day,
        req.body.start_time,
        req.body.end_time,
        req.params.id
    ]).then((resp)=>{
        res.send({
            result : resp,
            status : true,
            statusText : 'Session data Updated'
        })
    }).catch((err)=>{
        res.status(500).send({
            error : err,
            status : false,
            statusText : 'Some Error Occured'
        })
    })
}




//Delete a session
exports.deleteRoutine = (req,res) =>{
    let sql = 'DELETE FROM routine WHERE ?'
    connection.__query(sql,{
        routine_id : req.params.id
    }).then((resp)=>{
        res.send({
            result : resp,
            status : true,
            statusText : 'Routine deleted'
        })
    }).catch((err)=>{
        res.status(500).send({
            error: err,
            status : false,
            statusText : 'Failed to delete'
        })
    })

}

