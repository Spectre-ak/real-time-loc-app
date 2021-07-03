
//fetching user details

fetch('http://localhost:8080/fetchUserDetail').then(response=>response.json()).then(data=>{
  console.log(data);
  
  if(data.error==="1"){
    document.getElementById("userDetailDiv").innerHTML="Some Error Occurred on database. Please open a issue at <a href='https://github.com/Spectre-ak/real-time-loc-app/issues'>Github</a>."
  }
  else if(data.user==="-1"){
    //user not logged in
    document.getElementById("userDetailDiv").innerHTML="<a class='btn btn-primary' href='/login' role='button'>Login/Sign up</a>"
  }
  else{
    var nameP="<p>"+data.name+"</p>";
    var idP="<p>"+data.socketId+"</p>";
    document.getElementById("userDetailDiv").innerHTML=nameP+idP;
  }

});


var oneTimeGlobalVar;
if (navigator.geolocation) {
  navigator.geolocation.getCurrentPosition(function(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    var accuracy = position.coords.accuracy;
    oneTimeGlobalVar=position;
    console.log(latitude,longitude,accuracy);
    document.getElementById("currentPOSDiv").innerHTML=(latitude+" , " +longitude+" , "+accuracy);

  },
  function error(msg) {
//alert('Please enable your GPS position feature.');
},
  {maximumAge:10000, timeout:5000, enableHighAccuracy: true});
} else {
  //alert("Geolocation API is not supported in your browser.");
}

function sendLocationToBackend(){
  console.log(oneTimeGlobalVar);
  var pos={"acc":oneTimeGlobalVar.coords.accuracy,"lat":oneTimeGlobalVar.coords.latitude,"long":oneTimeGlobalVar.coords.longitude};
  stompClient.send("/app/coordinatesGlobal", {},JSON.stringify(pos));
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

