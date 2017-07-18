// JScript File




/* Client-side access to querystring name=value pairs
Version 1.3
28 May 2008
	           	
License (Simplified BSD):
http://adamv.com/dev/javascript/qslicense.txt
*/
function Querystring(qs) { // optionally pass a querystring to parse
    this.params = {};

    if (qs == null) qs = location.search.substring(1, location.search.length);
    if (qs.length == 0) return;

    // Turn <plus> back to <space>
    // See: http://www.w3.org/TR/REC-html40/interact/forms.html#h-17.13.4.1
    qs = qs.replace(/\+/g, ' ');
    var args = qs.split('&'); // parse out name/value pairs separated via &

    // split out each name=value pair
    for (var i = 0; i < args.length; i++) {
        var pair = args[i].split('=');
        var name = decodeURIComponent(pair[0]);

        var value = (pair.length == 2)
	           			? decodeURIComponent(pair[1])
	           			: name;

        this.params[name] = value;
    }
}

Querystring.prototype.get = function(key, default_) {
    var value = this.params[key];
    return (value != null) ? value : default_;
}

Querystring.prototype.contains = function(key) {
    var value = this.params[key];
    return (value != null);
}

function isCuit(source, args)
{ 
    var suma ; 
    var resto ; 
    var verif ; 
    nro = args.Value; 

    var pos = nro.split("") ; 
    if ( ! /^\d{11}$/.test ( nro ) ) 
    {
       
        return false ; 
    }
    var y = 0 ; 
    while ( y < pos.length ) 
    { 
        suma = ( pos [0] * 5 + pos [1] * 4 + pos [ 2 ] * 3 + pos [ 
        3 ] * 2 + pos [ 4 ] * 7 + pos [ 5 ] * 6 + pos [ 6 ] * 5 + pos [ 7 ] * 
        4 + pos [ 8 ] * 3 + pos [ 9 ] * 2 ) ; 
        resto = suma % 11 ; 
        if ( resto == 0 ) { 
            verif = 0 ; 
            break ; 
        } 
        else if ( resto == 1 && ( pos[1] == 0 || pos[6] == 7 ) ) { 
            pos [ 1 ] = 4 ; 
            continue ; 
        } 
        else { 
            verif = 11 - resto ; 
            break ; 
        } 
        y += 1 ; 
    } 
    
    args.IsValid= (pos[10]== verif); 
    
    
    return;
}

