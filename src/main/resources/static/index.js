Function.prototype.extend = function(fn) {
  var self = this;
  return function() {
    self.apply(this, arguments);
    fn.apply(this, arguments);
  };
};

window.onload = 
function clientSelector(){
	fetch('/clients')
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


window.onload = window.onload.extend(
function roomSelector(){
    fetch('/chambres')
    .then(function(response) {
        if (!response.ok) {
        	throw Error(response.statusText);
        }
        return response.json();
        })
        .then(function(tableChambre) {
            var listChambre = tableChambre.map(function(chambre){
       	    	return ('<li><div><input type="checkbox" class="chambreCheckbox" id="'+chambre.uuid+'" >'+
				'<label class="chambrelabel" for="'+chambre.uuid+'" value="'+chambre.uuid+'">'+' '+chambre.numero+' - '+chambre.surfaceEnM2+'sqm</label></div></li>');
            }).join('')
          document.querySelector('#options').innerHTML =listChambre ;
          
            let hotel = tableChambre.map(chambre=>chambre.hotel);
            document.querySelector('#hotel').innerHTML = '<h1>'+hotel[0].nom+'</h1>';

            	var star='';
            	for(var i=0; i<hotel[0].nombreEtoiles;i++){
            		star+='*';
            	}
            document.querySelector('#stars').innerHTML = '<h1>'+star+'</h1>';
            
		}).catch(function(error) {
        console.log('An error has occured : ', error);
        });
})

function dateIn(){
	var startDate = document.getElementById('formDateDebut').value;
	var today = new Date().toJSON().slice(0,10);
	var endDate = document.getElementById('formDateFin').value;

	var x = document.getElementById("dateCIn");
	if(startDate < today){
	    x.style.display = "inline";
		  } else {
		    x.style.display = "none";
		  }
	}
function dateOut(){
	var startDate = document.getElementById('formDateDebut').value;
	var today = new Date().toJSON().slice(0,10);
	var endDate = document.getElementById('formDateFin').value;

	var x = document.getElementById("dateCOut");
	if(endDate < today || endDate<startDate){
	    x.style.display = "inline";
		  } else {
		    x.style.display = "none";
		  }
	}

function formSubmit(){
	var clientId = document.getElementById('clientSelect').value;
	var chambresId=[]; 
	var checkBoxes = document.querySelectorAll('input[type="checkbox"]');
	var j =0;
	for(var i=0; checkBoxes[i]; ++i){
      if(checkBoxes[i].checked){
    	  chambresId[j] = checkBoxes[i].id;
        j++;
      }
	}
	if(!chambresId){
		document.getElementById("unchecked").style.display="inline"
	}
	
	var startDate = document.getElementById('formDateDebut').value;
	var endDate = document.getElementById('formDateFin').value;
	
	const data = {dateDebut: startDate, dateFin: endDate, clientUuid: clientId, chambreUuid : chambresId};
	console.log(data);
	
	const form = document.getElementById('createReservForm');
	form.addEventListener('submit', function(e){
		e.preventDefault();
		fetch('/reservations', {
			  method: 'post',
			  body: JSON.stringify({dateDebut: startDate, dateFin: endDate, clientUuid: clientId, chambreUuid : chambresId})
			})
			.then((response) => response.text())
			.then((text) => {
			  console.log('Success:', text);
			})
			.catch((error) => {
			  console.error('Error:', error);
			});
	})

	
}
