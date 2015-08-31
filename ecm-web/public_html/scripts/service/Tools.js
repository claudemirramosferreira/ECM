function Tools(){
    
}

/**
 * Validação de email
 * @param {type} email
 * @returns {Boolean}
 */
Tools.prototype.validarEmail = function(email){
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
};

/**
 * Validação CNPJ
 * @param {type} cnpj
 * @returns {Boolean}
 */
Tools.prototype.validarCNPJ = function(cnpj) {

    cnpj = cnpj.replace(/[^\d]+/g,'');

    if(cnpj == '') return false;

    if (cnpj.length != 14)
        return false;

    // LINHA 10 - Elimina CNPJs invalidos conhecidos
    if (cnpj == "00000000000000" || 
        cnpj == "11111111111111" || 
        cnpj == "22222222222222" || 
        cnpj == "33333333333333" || 
        cnpj == "44444444444444" || 
        cnpj == "55555555555555" || 
        cnpj == "66666666666666" || 
        cnpj == "77777777777777" || 
        cnpj == "88888888888888" || 
        cnpj == "99999999999999")
        return false; // LINHA 21

    // Valida DVs LINHA 23 -
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0,tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;

    tamanho = tamanho + 1;
    numeros = cnpj.substring(0,tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
          return false; // LINHA 49

    return true; // LINHA 51

};

Tools.prototype.validarData = function(data){
    if(typeof data !== "string" || !/^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/g.test(data)){
        return false;
    }else{
        var 
            d = data.split("/"),
            dataValida = new Date(d[2], d[1] - 1, d[0]);
        
        if(dataValida.getMonth() + 1 !== parseInt(d[1]) || dataValida.getDate() !== parseInt(d[0]) || dataValida.getFullYear() !== parseInt(d[2])){
            return false;
        }
        
        return true;
    }
};

Tools.prototype.converterMoeda = function(number){
    number = number+"";
    return parseFloat((number+"").replace(/\.*/g, "").replace(/\,+/g, "."));
};

Tools.prototype.criarData = function(dataString) {
    var dataList = dataString.split('/');
    if (this.validarData(dataString)) {
        return new Date(dataList[2], dataList[1]-1, dataList[0]);
    }
    return null;
};


Tools.prototype.greaterOrEqual = function (pDataInicial, pDataFinal){
    var dtInicialList = pDataInicial.split("/");
    var dtFinalList = pDataFinal.split("/");
    
    var dtInicial = new Date();
    dtInicial.setFullYear(dtInicialList[2], dtInicialList[1], dtInicialList[0]);
    
    var dtFinal = new Date();
    dtFinal.setFullYear(dtFinalList[2], dtFinalList[1], dtFinalList[0]);
    
    if(dtFinal >= dtInicial){
        return true;
    }else{
        return false;
    }
};


Tools.prototype.greaterActualDate = function (pDataInicial){
    return this.greater(pDataInicial, new Date().getTime() );
}

Tools.prototype.greater = function (pDataInicial, pDataFinal){
    
    var objDate = new Date();
    objDate.setYear(pDataInicial.split("/")[2]);
    objDate.setMonth(pDataInicial.split("/")[1]  - 1);//- 1 pq em js é de 0 a 11 os meses
    objDate.setDate(pDataInicial.split("/")[0]);

    if( objDate.getTime() > pDataFinal) {
      return false;
    }
    return true;
};

Tools.prototype.bloquearDataPassado = function(dt1, dt2) {
//    var checkin = new Date();     // FIXME continuar (Anderson)
    var checkin = $('#'+dt1).fdatepicker({
    language: 'pt-BR',
            format: 'dd/mm/yyyy'
    }).on('changeDate', function (ev) {

    if (ev.date.valueOf() <= checkout.date.valueOf() || checkout.date.valueOf() === 1437523200000) {
    var newDate = new Date(ev.date)
            newDate.setDate(newDate.getDate());
            checkout.update(newDate);
    }

    }).data('datepicker');
            var checkout = $('#'+dt2).fdatepicker({
    language: 'pt-BR',
            format: 'dd/mm/yyyy',
            onRender: function (date) {
            return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
            }
    }).on('changeDate', function (ev) {
    checkout.hide();
    }).data('datepicker');
};


Tools.prototype.bloquearDataFuturo = function(dt1, dt2) {
    var checkin = $('#'+dt1).fdatepicker({
    language: 'pt-BR',
            format: 'dd/mm/yyyy'
    }).on('changeDate', function (ev) {

    if (ev.date.valueOf() >= checkout.date.valueOf() || checkout.date.valueOf() === 1437523200000) {
    var newDate = new Date(ev.date)
            newDate.setDate(newDate.getDate());
            checkout.update(newDate);
    }

    }).data('datepicker');
            var checkout = $('#'+dt2).fdatepicker({
    language: 'pt-BR',
            format: 'dd/mm/yyyy',
            onRender: function (date) {
            return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
            }
    }).on('changeDate', function (ev) {
    checkout.hide();
    }).data('datepicker');
};

Tools = new Tools();