function a(a){a.style.height="1px",a.style.height=25+a.scrollHeight+"px"}function verifyXpath(a,c){b(a,c)}function b(a,b){for(var f,c=document.evaluate(a.value,document,null,XPathResult.ORDERED_NODE_ITERATOR_TYPE,null),d=document.evaluate(atob(b),document,null,XPathResult.ORDERED_NODE_ITERATOR_TYPE,null),e=!1;f=c.iterateNext();){if(f!=d.iterateNext()){e=!1;break}e=!0}a.style.color=e?"green":"red"}window.onload=function(){var b=document.getElementById("ex"),c=document.getElementById("exShow");c.innerHTML=b.innerHTML.replace(new RegExp("\n\t\t\t","g"),"\n"),a(c)};