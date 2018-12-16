var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');
var app = express();

app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'qwe123',
  database : 'mop'
});

connection.connect(function(err){
   if (err) throw err;
   console.log("Mysql Connect")
});

var server = app.listen(3000, function(){
    console.log("Express server has started on port 3000")
})

app.get('/',function(req,res){
        res.send("Hello Mop")
});


//user REST API
app.get("/user", function(req, res){
    connection.query('SELECT * from user', function(err, rows, fields){
        if (!err){
            console.log('The solution is: ', rows);
            res.json(rows);
        }
        else
            console.log('Error while performing Query.', err);
    });
})

//product REST API
app.get("/product", function(req, res){
    connection.query('SELECT * from product', function(err, rows, fields){
        if (!err){
            console.log('The solution is: ', rows);
            res.json(rows);
        }
        else
            console.log('Error while performing Query.', err);
    });
})

//빌린 물건
app.get("/product/isrent", function(req, res){
    connection.query('SELECT * from product where p_isrent = 1', function(err, rows, fields){
        if (!err){
            console.log('The solution is: ', rows);
            res.json(rows);
        }
        else
            console.log('Error while performing Query.', err);
    });
})

//빌릴 수 있는 물건
app.get("/product/isnotrent", function(req, res){
    connection.query('SELECT * from product where p_isrent = 0', function(err, rows, fields){
        if (!err){
            console.log('The solution is: ', rows);
            res.json(rows);
        }
        else
            console.log('Error while performing Query.', err);
    });
})


app.post("/proudct/rent", function(req, res){
    var body = req.body;//pid,mid 입력받아야 함
    connection.query('UPDATE product SET p_rnet = 1 ,on_borrower = "Kihoon Park" , p_on_start=NOW(),p_on_end = date_add(now(), interval +2 day) WHERE ? = p_id',body.p_id, function(err, rows, fields) {

        if (!err){
            console.log('The solution is: ', rows);
            res.send(rows);
        }
        else
            console.log('Error while performing Query.', err);
    });

})
