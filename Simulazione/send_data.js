var amqp = require('amqplib/callback_api');
var fs = require("fs");
var amqpConn = null;
//var q = "Input_FAHM_CEP_QUEUE";
var q = "input_queue";

var dati = caricaJSON();
function caricaJSON(){
	return JSON.parse(fs.readFileSync("./JSON_EXAMPLE.json")); //CHIAMATA SINCRONA PERCHE MI SERVONO TUTTI I DATI
}

function start() {
	amqp.connect('amqp://nizuzoyu:R-9eFbEOoHtTyPKU26fy4rjLMaZgjbgy@duckbill.rmq.cloudamqp.com/nizuzoyu', function(err, conn) {
	//amqp.connect('amqp://localhost', function(err, conn) {
	    if (err) {
		console.error("[AMQP]", err.message);
		return setTimeout(start, 1000);
	    }
	    conn.on("error", function(err) {
	    	if (err.message !== "Connection closing") {
	    		console.error("[AMQP] conn error", err.message);
	    	}
	    });
	    conn.on("close", function() {
	    	console.log("[AMQP] Connection closed");
		setTimeout(function() { process.exit(0) }, 500); //dopo 0.5 secondi esegue function().
	    });

	    console.log("[AMQP] connected");
	    amqpConn = conn;
	    startPublisher();

	});
}

var pubChannel = null;
var offlinePubQueue = []; //[] means a new array object.
function startPublisher() {
      //createConfirmChannel opens a channel which uses "confirmation mode". A channel in confirmation mode require each published message to be 'acked' or 'nacked' by the server, thereby indicating that it has been handled.
      amqpConn.createConfirmChannel(function(err, ch) {
      //amqpConn.createChannel(function(err, ch) {

	      if (closeOnErr(err)) return;

	      ch.on("error", function(err) {
	      	console.error("[AMQP] channel error", err.message);
	      });

	      ch.on("close", function() {
		console.log("[AMQP] channel closed");
	      });

	      pubChannel = ch;

	      ch.assertQueue(q, {durable: true});

	      while( (m = offlinePubQueue.shift()) !== undefined ) { //The shift() method removes the first item of an array. This method changes the length of the array. The return value of the shift method is the removed item.
		publish("", q, m[2]);
	      }
    });
}

function publish(exchange, routingKey, content) { //The publish method will queue messages internally if the connection is down and resend them later. The consumer subscribes to the jobs-queue.
    try {
      var d = new Date();
      pubChannel.publish(exchange, routingKey, content, {
	contentType:"text/plain",
	contentEncoding :"ISO-8859-1",
	priority:1,
	timestamp:d.getTime(),
	userId :"nizuzoyu",
	//userId :"guest",
	appId :"applicationId",
	persistent: true },
        function(err, ok) {
        	if (err) {
                	console.error("[AMQP] publish", err);
                	offlinePubQueue.push([exchange, routingKey, content]); //mette l'elemento nella coda offline in caso di errore e quindi di non pubblicazione sulla coda online dell'istanza di rabbitmq (INPUT_FAHM_CEP).
                	pubChannel.connection.close();
                }
        });
    } catch (e) {
      console.error("[AMQP] publish", e.message);
      offlinePubQueue.push([exchange, routingKey, content]);
    }
}

function closeOnErr(err) {
    if (!err) return false;
    console.error("[AMQP] error", err);
    amqpConn.close();
    return true;
}

var i = 0, length = dati.length;
setInterval(function() { //setInterval: ogni tot. secondi (secondo parametro) chiama la funzione (primo parametro).
	if(i<length){
		dati[i].event.metaData.timestamp = new Date();
		publish("", q, Buffer.from(JSON.stringify(dati[i])));
		//pubChannel.sendToQueue(q, Buffer.from(JSON.stringify(dati[i])));
		console.log("Message " + i + " of "+ length + " time: " + dati[i].event.metaData.timestamp);
		i++;
	}else{
		amqpConn.close();  
	}  
}, (Math.floor(Math.random() * 9) + 1 ) *500);

start();
