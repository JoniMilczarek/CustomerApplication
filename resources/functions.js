
var login = {
	"user": "",
	"password": ""
}

var customer = {
	firstName: "",
	lastName: "",
	sex: "",
	cpf: "",
	email: "",
	nationality: "",
	naturalness: "",
	dateOfBirth: ""
};

$('.form').find('input, textarea').on('keyup blur focus', function(e) {

	var $this = $(this),
		label = $this.prev('label');

	if (e.type === 'keyup') {
		if ($this.val() === '') {
			label.removeClass('active highlight');
		} else {
			label.addClass('active highlight');
		}
	} else if (e.type === 'blur') {
		if ($this.val() === '') {
			label.removeClass('active highlight');
		} else {
			label.removeClass('highlight');
		}
	} else if (e.type === 'focus') {

		if ($this.val() === '') {
			label.removeClass('highlight');
		}
		else if ($this.val() !== '') {
			label.addClass('highlight');
		}
	}

});

$('.tab a').on('click', function(e) {

	e.preventDefault();

	$(this).parent().addClass('active');
	$(this).parent().siblings().removeClass('active');

	target = $(this).attr('href');

	$('.tab-content > div').not(target).hide();

	$(target).fadeIn(600);

});


var submitGetForm = function() {
	if(!validateLogin()) {
		return;
	}

	if ($("#get .cpf").val() == "") {
		$("#infoModal .modal-body").html("CPF eh um campo obrigatorio");
		$("#infoModal").modal();
	}
	
	$.ajax({
		url: "http://localhost:8080/api/v1/customers/" + $("#get .cpf").val(),
		type: "GET",
		headers: { "Authorization": "Basic " + btoa(login.user + ":" + login.password) }
	}).done(function(response) {
		if(response != undefined && response != "") {
			resolveResponse(response);	
			$("#alertModal").modal();
		} else {
			clearModal();
			$("#infoModal .modal-body").html("Cliente não encontrado");
			$("#infoModal").modal();		
		}
	});
}

var submitCreateForm = function() {
	if(!validateLogin()) {
		return;
	}
	let post_url = "http://localhost:8080/api/v1/customers";
	customer.firstName = $("#firstName").val();
	customer.lastName = $("#lastName").val();
	customer.cpf = $("#cpf").val();
	customer.sex = $("#sex").val();
	customer.dateOfBirth = $("#dateOfBirth").val();
	customer.naturalness = $("#naturalness").val();
	customer.nationality = $("#nationality").val();
	customer.email = $("#email").val();
	
	$.ajax({
		url : post_url,
		type: "POST",
		data : JSON.stringify(customer),
		dataType: 'json',
		context: "json",
	    contentType: 'application/json',
		headers: { "Authorization": "Basic " + btoa(login.user + ":" + login.password) }	    
	}).done(function(response){
		$("#infoModal .modal-body").html(response);
		$("#infoModal").modal();		
	}).fail(function(response) {
		console.log(response);
		$("#infoModal .modal-body").html(response.responseText);
		$("#infoModal").modal();	
	});
}


var submitUpdateForm = function() {
	if(!validateLogin()) {
		return;
	}	
	let post_url = "http://localhost:8080/api/v1/customers";
	customer.firstName = $("#firstNameUpdate").val();
	customer.lastName = $("#lastNameUpdate").val();
	customer.cpf = $("#cpfUpdate").val();
	customer.sex = $("#sexUpdate").val();
	customer.dateOfBirth = $("#dateOfBirthUpdate").val();
	customer.naturalness = $("#naturalnessUpdate").val();
	customer.nationality = $("#nationalityUpdate").val();
	customer.email = $("#emailUpdate").val();
	
	console.log("customer", customer);
	$.ajax({
		url : post_url,
		type: "PUT",
		data : JSON.stringify(customer),
		dataType: 'json',
		context: "json",
	    contentType: 'application/json',
		headers: { "Authorization": "Basic " + btoa(login.user + ":" + login.password) }	    
	}).done(function(response){ 
		$("#infoModal .modal-body").html(response);
		$("#infoModal").modal();	
	}).fail(function(response) {
		$("#infoModal .modal-body").html(response.responseText);
		$("#infoModal").modal();	
	});
}

var submitDeleteForm = function() {
	if(!validateLogin()) {
		return;
	}
	let deleteForm = {
		cpf: ""
	}
	deleteForm.cpf = $("#delete .cpf").val();
	$.ajax({
		url: "http://localhost:8080/api/v1/customers",
		type: "DELETE",
		data : JSON.stringify(deleteForm),
		dataType: 'json',
		context: "json",
	    contentType: 'application/json',
		headers: { "Authorization": "Basic " + btoa("jonatan" + ":" + "Teste123") }
	}).done(function(response) {
		$("#infoModal .modal-body").html(response);
		$("#infoModal").modal();
	}).fail(function(response) {
		$("#infoModal .modal-body").html(response.responseText);
		$("#infoModal").modal();	
	});
}

var resolveResponse = function(responseJson) {
	$("#modalCpf").html(responseJson.cpf);
	$("#modalNome").html(responseJson.firstName);
	$("#modalSobrenome").html(responseJson.lastName);
	$("#modalEmail").html(responseJson.email);
	$("#modalEndereco").html(responseJson.address);
	$("#modalSexo").html(responseJson.sex);
	$("#modalAniversario").html(responseJson.dateOfBirth);
	$("#modalNatural").html(responseJson.naturalness);
	$("#modalNacional").html(responseJson.nationality);
}

var clearModal = function() {
	$("#modalCpf").html();
	$("#modalNome").html();
	$("#modalSobrenome").html();
	$("#modalEmail").html();
	$("#modalEndereco").html();
	$("#modalSexo").html();
	$("#modalAniversario").html();
	$("#modalNatural").html();
	$("#modalNacional").html();
}

var validateLogin = function() {
	if (login.user != "jonatan" || login.password != "Teste123") {
		$("#loginModal").modal();
		return false;
	} else {
		return true;
	}
}

var submitLogin = function() {
	login.user = $(".loginUser").val();
	login.password = $(".loginPassword").val();
	console.log("login.user", login.user);
	console.log("login.password", login.password);
	$("#loginModal").modal("hide");
}
