var express = require('express');
var app = express();
var Admin = require('./models/adminSchema');
var mongoose = require('mongoose');
var session = require('express-session');
var MongoStore = require('connect-mongo')(session);

//connect to MongoDB
//mongoose.connect('mongodb://mongiello:mongiello@ds137246.mlab.com:37246/userdata', { useMongoClient: true });
mongoose.connect('mongodb://localhost/userData', { useMongoClient: true });
//mongoose.connect('mongodb://localhost/userData', { useMongoClient: true });
var db = mongoose.connection;

//handle mongo error
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function () {
  // we're connected!
});

var username, password, email, name, surname;
if(process.argv.indexOf("-u") != -1){ //does our flag exist?
    username = process.argv[process.argv.indexOf("-u") + 1]; //grab the next item
}
if(process.argv.indexOf("-p") != -1){ //does our flag exist?
    password = process.argv[process.argv.indexOf("-p") + 1]; //grab the next item
}
if(process.argv.indexOf("-m") != -1){ //does our flag exist?
    email = process.argv[process.argv.indexOf("-m") + 1]; //grab the next item
}
if(process.argv.indexOf("-n") != -1){ //does our flag exist?
    name = process.argv[process.argv.indexOf("-n") + 1]; //grab the next item
}
if(process.argv.indexOf("-s") != -1){ //does our flag exist?
    surname = process.argv[process.argv.indexOf("-s") + 1]; //grab the next item
}


var adminData = {
      username: username,
      password: password,
      email: email,
      name: name,
      surname: surname
    }


Admin.create(adminData, function (error, adm) {
	if (error) {
		console.log(error);
        return error;
      } else {
		console.log("Created admin:", adm.name, adm.surname, "with username", adm.username)
		return;
	  }
    });
