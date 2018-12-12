var express = require('express');

var mysql = require('mysql');

var app = express();


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
