var express = require('express'); //Express is a minimal and flexible Node.js web application framework that provides a robust set of features for web and mobile applications.
var fs=require('fs');
var key = fs.readFileSync('./certificates/CEP/webserver_key.pem');
var certificate = fs.readFileSync( './certificates/CEP/webserver_cert.pem' );
var options = {key: key, cert:certificate}
var app = express(); //The app object conventionally denotes the Express application. Create it by calling the top-level express() function exported by the Express module.
var bodyParser = require('body-parser'); //Node.js body parsing middleware. Parse incoming request bodies in a middleware.
var mongoose = require('mongoose'); //mongodb object modeling for node.js.
var session = require('express-session'); //A session is a place to store data that you want access to across requests. Each user that visits your website has a unique session.
var MongoStore = require('connect-mongo')(session); //MongoDB session store.
var Event = require('./models/event_alertSchema');
var amqp = require('amqplib');
var mongoDbQueue = require('mongodb-queue'); //Message queues which uses MongoDB.
var https=require('https');
var http=require('http');
var server = http.Server(app); //Create a server.
var io = require('socket.io')(server);

var clients =[];

var port = process.env.PORT || 5000; 
var https_port    =   process.env.PORT_HTTPS || 8443; //you can set the environment variable PORT to tell your web server what port to listen on. So process.env.PORT || 3000 means: whatever is in the environment variable PORT, or 3000 if there's nothing there.

//redirections





server.listen(8002);
io.sockets.on('connection', function(socket){ //Start listening for socket events with the specified eventName (in this case connection). Will trigger the provided callback function when a matching event is received.
  socket.on('storeClientInfo', function (data) {
      var clientInfo = new Object();
      clientInfo.customID = data.customID;
      clientInfo.clientID = socket.id;
      clients.push(clientInfo);
  });
    
  socket.on('disconnect', function (data) {
    
      for( var i=0, len=clients.length; i<len; ++i ){
          var c = clients[i];

          if(c.clientID == socket.id){ //rimuove l'elemento con clientID == socket.id dall'array clients.
              clients.splice(i,1);
              break;
          }
      }

  });
  
})

//connect to MongoDB
//mongoose.connect('mongodb://mongiello:mongiello@ds137246.mlab.com:37246/userdata', { useMongoClient: true });
mongoose.connect('mongodb://localhost/userData', { useMongoClient: true });
var db = mongoose.connection;


//handle mongo error
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function () {
  // we're connected!
});

var queue = mongoDbQueue(db, 'testQ');

//use sessions for tracking logins
app.use(session({
  secret: 'mercuia',
  resave: true,
  saveUninitialized: false,
  store: new MongoStore({
    mongooseConnection: db
  })
}));

// parse incoming requests
app.use(bodyParser.json()); //bodyParser.json() returns middleware that only parses json.
app.use(bodyParser.urlencoded({ extended: false }));


// serve static files from template
app.use(express.static(__dirname + '/public'));

// include routes
var routes = require('./routes/router');
app.use('/', routes);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  var err = new Error('File Not Found');
  err.status = 404;
  //next(err);
  res.redirect("/404err");
});

// error handler
// define as the last app.use callback
app.use(function (err, req, res, next) {
  res.status(err.status || 500);
  res.send(err.message);
});



app.use(function(req,res,next) {
  if (!/https/.test(req.protocol)){
     res.redirect("https://" + req.headers.host + req.url);
  } else {
     return next();
  } 
});
app.get('*',function (req, res) {
    res.redirect('https://127.0.0.1' + req.url);
});


//redirect http
http.Server(function (req, res) {
    res.writeHead(301, { "Location": "https://" + req.headers['host'].replace(port,https_port) + req.url });
    console.log("http request, will go to >> ");
    console.log("https://" + req.headers['host'].replace(port,https_port) + req.url );
    res.end();
}).listen(port);
// listen on port 1337
// 
//server https 
//stava questo prima : app.listen(port,function
https.Server(options, app).listen(https_port, function () {
 console.log('Express app listening on http port ' + port+ '\n' +"Express app listening on Https on port",https_port);


 

  amqp.connect("amqp://nizuzoyu:R-9eFbEOoHtTyPKU26fy4rjLMaZgjbgy@duckbill.rmq.cloudamqp.com/nizuzoyu").then(function(conn) {
    process.once('SIGINT', function() { conn.close(); });
    return conn.createChannel().then(function(ch) {
  
      var ok = ch.assertQueue('output_queue', {durable: false}); //usare 2 code diverse per input e output?
  
      ok = ok.then(function(_qok) {
        return ch.consume('output_queue', function(msg) {
          console.log(" [x] Received '%s'", msg.content.toString());
    var obj = JSON.parse(msg.content.toString());
    console.log(" [x] Received ID '%s'", obj.event.metaData.id_utente);

    var eventAlert = {
      idDoc: obj.event.metaData["id_doctor"],
      metadata: JSON.stringify(obj.event.metaData),
      payload: JSON.stringify(obj.event.payloadData),
    }

    Event.create(eventAlert, function (error, event) {
      if (error) {
        console.log(error);
      }
      else {
        console.log("Published event for", obj.event.metaData["id_doctor"]);
      }
    });

    
    if(clients.length > 0){
      var recipient;
      for (var i = 0; i < clients.length; i++)
      {
        if(clients[i]["customID"]===obj.event.metaData.id_doctor){
          recipient=clients[i];
          break;
        }
      }
      if (recipient!==undefined){
    var rcptSocketID=recipient["clientID"];
      io.sockets.to(rcptSocketID).emit( 'notification', JSON.stringify(obj) );}
    //io.to(clients[0]["customID"]).emit('notification', JSON.stringify(obj));
  }
        }, {noAck: true});
      });
  
      return ok.then(function(_consumeOk) {
        console.log(' [*] Waiting for messages. To exit press CTRL+C');
      });
    });
  }).catch(console.warn);


});
