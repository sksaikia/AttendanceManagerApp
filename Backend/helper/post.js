const connection = require('./connection');


exports.addPost = (req,res) => {
    let titleStr = req.body.title;
    let bodyStr = req.body.body;

    let post = {
        title : titleStr,
        body : bodyStr
    }
    let sql = 'INSERT INTO posts SET ?'
    connection.__query(sql,post).then((resp)=>{
    res.send({
        result : resp,
        status : true,
        statusText : 'Successfully Added to DB'
    })
}).catch((err)=>{
    console.log(err)
})

}