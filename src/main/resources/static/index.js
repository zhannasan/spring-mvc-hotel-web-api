Function.prototype.extend = function(fn) {
  var self = this;
  return function() {
    self.apply(this, arguments);
    fn.apply(this, arguments);
  };
};

window.onload = function clientSelector(){
	fetch('/client/all')
	    .then(function(response) {
	        if (!response.ok) {
	        	throw Error(response.statusText);
	        }
	        return response.json();
	        })
	        .then(function(tableClient) {
	            var liClient = tableClient.map(function(client){
	                return '<option value="'+client.uuid+'">'+client.nom+" "+client.prenoms+'</option>'
	            }).join('');
	            document.querySelector('#clientSelect').innerHTML = liClient;
	        })
	        .catch(function(error) {
	        console.log('An error has occured : ', error);
	        });
	}


window.onload = window.onload.extend(function roomSelector(){
    fetch('/chambre/all')
    .then(function(response) {
        if (!response.ok) {
        	throw Error(response.statusText);
        }
        return response.json();
        })
        .then(function(tableChambre) {
        	
            var liChambre = tableChambre.map(function(chambre){
            	// var hotel= chambre.hotel;
       	    	return ('<li><input type="checkbox" class="chambreCheckbox" id="'+chambre.uuid+'" >'+
				'<label class="custom-control-label" for="'+chambre.uuid+'">'+chambre.numero+' '+chambre.surfaceEnM2+'sqm</label></li>');
            }).join('')
           
          /*  var hotel = tableChambre.map(function(hotel){
            	return '<h1 id='+hotel.id+'>'+hotel.nom+'</h1>';
            	}).join('')
            */
            document.querySelector('#chkboxLi').innerHTML = liChambre;
           // document.querySelector('#hotel').innerHTML = hotel;
	/*	}).then(function(tableChambre){
			 var hotel= tableChambre.map(function(hotel){
			return '<h1 id='+hotel.id+'>'+hotel.nom+'</h1>';
		document.querySelector('#hotel').innerHTML = hotel;*/
		}).catch(function(error) {
        console.log('An error has occured : ', error);
        });
})

function ajoutBDD(){
	var resa ={
			
	}
}

fetch('reservations',{method: 'POST', body: ''})

