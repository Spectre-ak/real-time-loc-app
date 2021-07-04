
//fetching user details

//fetch('https://mapio.azurewebsites.net/fetchUserDetail')

var data=undefined;

fetch('https://mapio.azurewebsites.net/fetchUserDetail')
.then(response=>response.json()).then(res=>{
  console.log(res);
  
  if(res.error==="1"){
    document.getElementById("userDetailDiv").innerHTML="Some Error Occurred on database. Please open a issue at <a href='https://github.com/Spectre-ak/real-time-loc-app/issues'>Github</a>."
  }
  else if(res.user==="-1"){
    //user not logged in
    document.getElementById("userDetailDiv").innerHTML="<a class='btn btn-primary' href='/login' role='button'>Login/Sign up</a>"
  }
  else{
    var nameP="<p>"+res.name+"</p>";
    var idP="<p id='socketIdpara'>"+res.socketId+"</p>";
    document.getElementById("userDetailDiv").innerHTML=nameP+idP;
    data=res;
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
  var posCold={"acc":oneTimeGlobalVar.coords.accuracy,"lat":oneTimeGlobalVar.coords.latitude,"long":oneTimeGlobalVar.coords.longitude,"socketId":data.socketId,"name":data.name};
  if(data!==undefined)
    stompClient.send("/app/"+data.socketId, {},JSON.stringify(posCold));
}

var id, target, options;

function success(position) {
  try{
      var latitude = position.coords.latitude;
      var longitude = position.coords.longitude;
      var accuracy = position.coords.accuracy;
      console.log("changed pos "+JSON.stringify(position));
      document.getElementById("changedPOSDiv").innerHTML="changed pos "+(latitude+" , " +longitude+" , "+accuracy);

      if(data!==undefined){
        var pos={"acc":accuracy,"lat":latitude,"long":longitude,"socketId":data.socketId,"name":data.name};
        stompClient.send("/app/"+data.socketId, {},JSON.stringify(pos));
      }
      
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

