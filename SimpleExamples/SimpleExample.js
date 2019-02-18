let multichain = require("multichain-node")({
	port: ****,
	host: '***.***.***.***',
	user: "****",
	pass: "****"
});

//Use this method to check if your connection is working
//There will be some Informations about your Chain printed to the console
//Please check if the name of the chain is the one for your Team!
multichain.getInfo()
	.then((info) => {
		console.log(info);
		console.log("You are connected with your blockchain!");
	})
	.catch((err) => {
		console.log("***Error*** " + err.message);
	});

//Here we are writing to the root stream which is already initiated for you
//you can also create other streams and publish information to them
writeDataToStream("root", "myExampleKey", "this is some example data...")
	.then((result) => {
		console.log(result);
	})
	.catch((err) => {
		console.log("***Error*** " + err.message);
	});

//Here we are reading all published information from the root stream
readDataFromStream("root")
	.then((result) => {
		console.log(result);
	})
	.catch((err) => {
		console.log("***Error*** " + err.message);
	});


function writeDataToStream(streamName, key, data) {
	return new Promise((resolve, reject) => {
		multichain.publish({
				stream: streamName,
				key: key,
				"data": ascii2hex(data)
			})
			.then(() => {
				resolve("inserted '" + data + "' into " + streamName);
			})
			.catch((err) => {
				reject(err);
			});
	});
}

function readDataFromStream(streamName) {
	return new Promise((resolve, reject) => {
		multichain.listStreamItems({
				stream: streamName
			})
			.then((result) => {
				var insertedObjects = [];
				result.forEach((obj) => {
					insertedObjects.push({
						keys: obj.keys,
						data: hex2ascii(obj.data)
					});
				});
				resolve(insertedObjects);
			})
			.catch((err) => {
				reject(err);
			});
	});
}


//the following functions are provided to convert the data stored in the multichain
//all data published to the chain must be a hex string
function hex2ascii(hexx) {
	var hex = hexx.toString();
	var str = '';
	for (var i = 0;	(i < hex.length && hex.substr(i, 2) !== '00'); i += 2)
		str += String.fromCharCode(parseInt(hex.substr(i, 2), 16));
	return str;
}

function ascii2hex(str) {
	var arr1 = [];
	for (var n = 0, l = str.length; n < l; n++) {
		var hex = Number(str.charCodeAt(n)).toString(16);
		arr1.push(hex);
	}
	return arr1.join('');
}