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
	$.ajax({
		url: "http://localhost:8080/api/v1/customers/" + $("#get .cpf").val(),
		type: "GET",
		headers: { "Authorization": "Basic " + btoa("jonatan" + ":" + "Teste123") }
	}).done(function(response) {
		alert(response);
	});
}

var submitCreateForm = function() {
	let post_url = "http://localhost:8080/api/v1/customers";
	customer.firstName = $("#firstName").val();
	customer.lastName = $("#lastName").val();
	customer.cpf = $("#cpf").val();
	customer.sex = $("#sex").val();
	customer.dateOfBirth = $("#dateOfBirth").val();
	customer.naturalness = $("#naturalness").val();
	customer.nationality = $("#nationality").val();
	customer.email = $("#email").val();
	
	console.log("customer", customer);
	$.ajax({
		url : post_url,
		type: "POST",
		data : JSON.stringify(customer),
		dataType: 'json',
		context: "json",
	    contentType: 'application/json',
		headers: { "Authorization": "Basic " + btoa("jonatan" + ":" + "Teste123") }	    
	}).done(function(response){ 
		alert(response);
	});
}


var submitUpdateForm = function() {
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
		let : post_url,
		type: "PUT",
		data : JSON.stringify(customer),
		dataType: 'json',
		context: "json",
	    contentType: 'application/json',
		headers: { "Authorization": "Basic " + btoa("jonatan" + ":" + "Teste123") }	    
	}).done(function(response){ 
		alert(response);
	});
}

var submitDeleteForm = function() {
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
		alert(response);
	});;
}
