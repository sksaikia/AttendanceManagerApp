const connection = require('./connection');

exports.addRoutine = (req,res) => {

    let teacher_id = req.body.teacher_id
    let class_id = req.body.class_id
    let session_id  = req.body.session_id
    let day = req.body.day
    let startTime = req.body.start_time
    let endTime = rq.body.end_time

    let sql = 'INSERT INTO routine SET ?'

    let post = {
        teacher_id : teacher_id,
        class_id : class_id,
        session_id : session_id,
        day : day,
        start_time : startTime,
        end_time : endTime
    }

}