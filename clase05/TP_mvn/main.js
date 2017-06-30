var defaultObj = document.createElement("DIV");
var usr = defaultObj;
var password = defaultObj;
var error = defaultObj;
function on_index_load() {
	usr = document.getElementById('usr');
	password = document.getElementById('pass');
	error = document.getElementById('msg');
	usr.id = Math.random().toString(36).substr(2, 5);
	password.id = Math.random().toString(36).substr(2, 5);
}
function on_index_submit() {
	if(usr.value != 'admin' || password.value != '123') {
		setError("Error de credenciales");
	}
	else {
		window.location = "todomvc-master/index.html";
	}
}
function setError(str) {
	error.innerHTML = str;
}
