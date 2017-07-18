function onEnterSearch(el) {
	if(event.keyCode == 13) {
		window.location = window.location.href.replace("index.html","search.html?"+el.value);
	}
}
function search() {
	window.location = window.location.href.replace("index.html","search.html?"+document.getElementById("searchbox").value);
}