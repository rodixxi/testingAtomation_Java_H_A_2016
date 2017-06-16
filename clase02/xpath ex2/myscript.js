function _submit(submitButton) {
	submitButton.disabled = true;
	document.getElementById("loader").style.visibility = "visible";
	var form = document.getElementById("form");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 500) {
			document.getElementById("loader").style.visibility = "hidden";
			submitButton.disabled = false;
			alert("Error");
		}
		else if(this.readyState == 4 && this.status == 200) {
			document.getElementById("loader").style.visibility = "hidden";
			submitButton.disabled = false;
			document.getElementById("check").style.visibility = "visible";
		}
  	};
  	xhttp.open(form.getAttribute("method"), form.getAttribute("action"), true);
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xhttp.send("name="+document.getElementById("name").value+"&xpath="+document.getElementById("xpath").value); 
}