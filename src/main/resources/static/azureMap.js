
var map, point,point2;
var defLat=28.5934,defLong=77.2223;

var cookie=document.cookie;
cookie=cookie.split(';')
var socketId=cookie[2].split("=")[1];
console.log(socketId);

function GetMap() {
    //Initialize a map instance.
    map = new atlas.Map('myMap', {
        center: [defLong,defLat],
        zoom: 4,
        view: 'Auto',

        //Add authentication details for connecting to Azure Maps.
        authOptions: {
            //Use Azure Active Directory authentication.
            // authType: 'anonymous',
            // clientId: "04ec075f-3827-4aed-9975-d56301a2d663", //Your Azure Active Directory client id for accessing your Azure Maps account.
            // getToken: function (resolve, reject, map) {
            //     //URL to your authentication service that retrieves an Azure Active Directory Token.
            //     var tokenServiceUrl = "https://azuremapscodesamples.azurewebsites.net/Common/TokenService.ashx";

            //     fetch(tokenServiceUrl).then(r => r.text()).then(token => resolve(token));
            // }

            //Alternatively, use an Azure Maps key. Get an Azure Maps key at https://azure.com/maps. NOTE: The primary key should be used as the key.
            authType: 'subscriptionKey',
            subscriptionKey: 'E941Wqqedrlu1xGQg1hp0f7EXPCCZ87G3dBI8lUSI7U'
        }
    });

    //Wait until the map resources are ready.
    map.events.add('ready', function(){

        //Create an HTML marker and add it to the map.


        // var marker = new atlas.HtmlMarker({
        //     color: 'DodgerBlue',
        //     text: '10',
        //     position: [defLong,defLat],
        //     popup: new atlas.Popup({
        //         content: '<div style="padding:10px">Hello World</div>',
        //         pixelOffset: [0, -30]
        //     })
        // });
           
        // map.markers.add(marker);
        // marker.togglePopup();
        // marker=marker;
        // //Add a click event to toggle the popup.
        // map.events.add('click',marker, () => {
        //     //console.log(marker);
        //     //marker.togglePopup();
        //     //marker.position=[33,33];

        // });

        var user={};
        stompClient.subscribe('/topic/globalReceiver',function(response){
	        response=JSON.parse(response.body);
	        console.log(response);
	        var marker;
	        if(response.socketId===socketId){
	        	//case when this user positon is received
	        	marker = new atlas.HtmlMarker({
	        		color: 'DodgerBlue',
		            text: 'Y',
		            position: [response.long,response.lat],
		            popup: new atlas.Popup({
		                content: '<div style="padding:10px"><i><b>You</b></i></div>',
		                pixelOffset: [0, -30]
		            })
	        	});
			}
			else{
			 	//others case
	        	marker = new atlas.HtmlMarker({
	        		color: 'slategrey',
		            text: 'O',
		            position: [response.long,response.lat],
		            popup: new atlas.Popup({
		                content: "<div style='padding:10px'><i><b>"+response.name+"</b></i> last position"+"</div>",
		                pixelOffset: [0, -30]
		            })
	        	});
	        }

        	// removing the old marker for this user
        	map.markers.remove(user[response.socketId]);

        	//adding new marker
        	map.markers.add(marker);
        	marker.togglePopup();
        	map.events.add('click',()=>{marker.togglePopup()});
        	user[response.socketId]=marker;

	    });



        // document.getElementById("mapButtonId").addEventListener('click',()=>{
        //     map.markers.remove(marker);
        // });
        
    });

    
}


function mapIntializer(){
	console.log(defLat,defLong);
	const intervalId=setInterval(()=>{
		try{
			stompClient.subscribe('/topic/test',function(res){
				console.log(res);
			});
			GetMap();
			clearInterval(intervalId);
		}
		catch(r){
			console.log(r);
		}
	},1000);
}


mapIntializer();