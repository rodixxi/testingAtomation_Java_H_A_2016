function complete() {
	var word = window.location.href.substr(window.location.href.indexOf("?")+1);
	var children = document.body.children;
	var gChildren;
	var x = 0;
	var y;
	var currentNode;
	var gCurrent;
	var a;
	var code;
	var p;
	var req;
	window.document.body.innerHTML = window.document.body.innerHTML.replace(/%s/g,decodeURIComponent(word));
	while(x < children.length) {
		currentNode = children[x++];
		req = currentNode.getAttribute("req");
		if(req) {
			if(word.indexOf(req) == -1) {
				currentNode.style.display = "none";
			}
		}
		currentNode.id = "Item"+Math.floor((Math.random() * 99) + 1);
		gChildren = currentNode.children;
		a = currentNode.children[0];
		a.href += '?'+word;
		code = currentNode.children[2];
		p = currentNode.children[3];
		code.innerHTML = a.href;
	}
}

function subComplete() {
	var word = decodeURIComponent(window.location.href.substr(window.location.href.indexOf("?")+1));
	document.title = document.title.replace(/%s/g,word);
	window.document.body.innerHTML = window.document.body.innerHTML.replace(/%s/g,word);
}