
if (navigator.geolocation) {
  navigator.geolocation.getCurrentPosition(function(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    var accuracy = position.coords.accuracy;

    console.log(latitude,longitude,accuracy);
    document.getElementById("currentPOSDiv").innerHTML=(latitude+" , " +longitude+" , "+accuracy);

  },
  function error(msg) {alert('Please enable your GPS position feature.');},
  {maximumAge:10000, timeout:5000, enableHighAccuracy: true});
} else {
  alert("Geolocation API is not supported in your browser.");
}

function sendLocationToBackend(){
  if (navigator.geolocation) {
    console.log("func called");
    navigator.geolocation.getCurrentPosition(function(pos) {
      try{
        console.log("send func called "+JSON.stringify(pos));
        stompClient.send("/app/coordinatesGlobal", {},JSON.stringify(pos));
      }
      catch(err){
        console.log(err)
      }
     
    });
  }
}

var id, target, options;

function success(position) {
  try{
      var latitude = position.coords.latitude;
      var longitude = position.coords.longitude;
      var accuracy = position.coords.accuracy;
     console.log("changed pos "+JSON.stringify(position));
     document.getElementById("changedPOSDiv").innerHTML="changed pos "+(latitude+" , " +longitude+" , "+accuracy);
     stompClient.send("/app/coordinatesGlobal", {},JSON.stringify(position));
  }
  catch(err){
    console.log(err);
  }

}

function error(err) {
  document.getElementById("changedPOSDiv").innerHTML=('ERROR(' + err.code + '): ' + err.message);
}

target = {
  latitude : 0,
  longitude: 0
};

options = {
  enableHighAccuracy: true,
  timeout: 5000,
  maximumAge: 0
};

id = navigator.geolocation.watchPosition(success, error, options);

