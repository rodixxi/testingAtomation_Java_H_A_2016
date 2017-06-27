function onEnterSearch(el, event) {
	if(event.keyCode == 13) {
		window.location = window.location.href.replace("index.html","search.html?"+encodeURIComponent(el.value));
	}
}
function search() {
	window.location = window.location.href.replace("index.html","search.html?"+encodeURIComponent(document.getElementById("searchbox").value));
}
