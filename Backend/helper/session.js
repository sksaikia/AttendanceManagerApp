const connection = require('./connection');
//Add a new session
exports.addSession = (req,res) =>{
    let name = req.body.session_name

    let post = {
        session_name : name
    }

    let sql = 'INSERT INTO session SET ?'

    connection.__query(sql,post).then((resp) => {
        res.status(200).send({
            result : resp,
            status : true,
            statusText : 'New Session Added'
        })

    }).catch((err)=>{
        res.status(500).send({
            error : err,
            status : false,
            statusText : 'Failed to add new session'
        })
    })
}

//Get all Sessions
exports.getSessions = (req,res)=>{
    let sql = 'SELECT * FROM session'
    connection.__query(sql).then((resp)=>{
        res.send({
            total_count : resp.length,
            result : resp,
            status  : true,
            statusText : 'All sessions retrieved'
        })
    }).catch((err)=>{
        console.log(err)
        res.status(500).send({
            error : err,
            status : false,
            statusText : 'Failed to add sessions'
        })
    })
}


//Get a single session
exports.getSession = (req,res) =>{
    let sql = 'SELECT * FROM session WHERE ?'
connection.__query(sql, {
    session_id : req.params.id
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
//patch a session
exports.updateSession = (req,res)=>{
    let sql = "UPDATE session SET `session_name` = ? WHERE `session_id` = ?"
    connection.__query(sql,[     
        req.body.session_name,
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
exports.deleteSession = (req,res) =>{
    let sql = 'DELETE FROM session WHERE ?'
    connection.__query(sql,{
        session_id : req.params.id
    }).then((resp)=>{
        res.send({
            result : resp,
            status : true,
            statusText : 'Session deleted'
        })
    }).catch((err)=>{
        res.status(500).send({
            error: err,
            status : false,
            statusText : 'Failed to delete'
        })
    })

}