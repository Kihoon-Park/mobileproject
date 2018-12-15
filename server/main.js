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
