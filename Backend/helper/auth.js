const connection = require('./connection');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken')

exports.signup = (req, res) => {

    let password = bcrypt.hash(req.body.password, 10, (err, hash) => {
        if (err)
            throw err;
        else {
            //use hash as password  
            let emailString = req.body.email;

            let sql1 = 'SELECT * FROM users WHERE ?'
            connection.__query(sql1, { email: emailString })
                .then((resp) => {
                    if (resp.length > 0) {
                        res.send('Already Exists');
                    } else {
                        let post = {
                            email: emailString,
                            password: hash,
                            first_name: 'Json',
                            last_name: 'Talukdar',
                            role: 'Admin'
                        }
                        let sql = 'INSERT INTO users SET ?'
                        connection.__query(sql, post)
                            .then((resp) => {
                                res.send({
                                    result: resp,
                                    status: true,
                                    statusText: 'Success'
                                })
                            }).catch((err) => {
                                console.log(err)
                            })
                    }
                }).catch((err) => {
                    res.status(500).send({
                        status : false,
                        statusText : 'Failed to auth'
                    })
                    console.log(err);
                })
        }
    })
}

exports.login = (req,res)=>{

    let emailString = req.body.email;
    let sql1 = 'SELECT * FROM users WHERE ?'

    connection.__query(sql1,{email: emailString}).then((sqlResult)=>{

          if(sqlResult.length==0){
              res.send('User does not exists')
          } else{
              console.log(sqlResult)
              bcrypt.compare(req.body.password,
                sqlResult[0].password,(err,result)=>{
                  if(err){
                      return res.status(401).json({
                            message:'Auth Failed'
                      });
                  }

                  if(result){
                        const token =   jwt.sign({
                            email : sqlResult[0].email
                        },"secret",
                        {
                            expiresIn: "7d"
                        },
                        
                    );
                        return res.status(200).json({
                            message : 'Auth successful',
                            token : token
                        });
                  }
                  res.status(401).json({
                    status : false,
                    statusText : 'Check password or email'
                  })

              });
          } 
    }).catch((err) => {
        res.status(500).send({
            status : false,
            statusText : 'Failed to auth'
        })
        console.log(err);
    })

}
