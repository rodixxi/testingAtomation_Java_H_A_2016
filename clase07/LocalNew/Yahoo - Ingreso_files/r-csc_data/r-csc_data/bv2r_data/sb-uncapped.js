var sb = {"pixels":[37,5108,70,5189,9,41,45,12,39,171,58,4,15],"BR_APS":"3WV0GdQ2KBG8BJvvREA"};
var delay = 0;
for(var i=0;i<sb.pixels.length;i++) {
if (i % 3 == 0 && i != 0)
delay += 200;
(function (i) {
setTimeout(function () {
(new Image()).src='https'+'://'+'geo-um.btrll.com/v1/map_pixel/sb/'+sb.pixels[i]+'.png';
}, delay);
})(i);
}
