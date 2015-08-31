function HasError(){
    return function(errors, field){
        
        var error = "";
        
        if(errors[field]){
            error = '<small class="error">'+errors[field]+'</small>';
        }
        
        return error;
    };
};

function CpfFormato(){
    return function(cpf){
        if(cpf){
            cpf = cpf.replace(/\D/g, "").replace(/^([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$/, "$1.$2.$3-$4");
        }
        return cpf;
    };
}

function CurrencyFormatFilter() {
    return function(num, f, d, t){
        return num.currencyFormat(f, d, t);
    };
}

/*
f = número de casas decimais
d = separador de decimos (’,’ virgula por padrão)
t = separador de milhar (’.’ ponto por padrão)
*/
String.prototype.currencyFormat = function (f, d, t) {
    var n = (n = this.match(/\d/g)) ? n.join('').replace(/^0+/,'') : '0', f = (f) ? f : 2, d = (d) ? d : ',', t = (t) ? t : '.';
    if (n.length < f + 1) return '0' + d + ((n.length < f) ? new Array(f - n.length + 1).join('0') + n : n)
    else return n.substr(0, n.length - f).split('').reverse().join('').match(/\d{1,3}/g).join(t).split('').reverse().join('') + d + n.substr(n.length - f)
};