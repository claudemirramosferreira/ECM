/* global CoreCtrl */
function HomeCtrl(){
    
    CoreCtrl.apply(this, arguments);
    
//    this.serviceServicoCustomizado = this.inject("HomeServicoCustomizado");
    this.showAlert('msg.login.efetuato', 'success');
    this.mensagemBemVindo();
//    this.preencherAlertas();
//    this.ordenar();
}


$.extend(HomeCtrl.prototype, CoreCtrl.prototype);

/**
 * Mostra a mensagem de bem vindo com a tradução
 */
HomeCtrl.prototype.mensagemBemVindo = function(){
    
    var now  = new Date();
    if (now.getHours () >= 5 && now.getHours() < 12){
        this.scope('mensagemBemVindo', 'msg.bomdia'); 
    }else if(now.getHours () >= 12 && now.getHours () < 18){
        this.scope('mensagemBemVindo', 'msg.boatarde');
    }else {
        this.scope('mensagemBemVindo', 'msg.boanoite');
    }
};

HomeCtrl.prototype.slideToggleHeader = function(e){
    var item = $(e.target);
    $(".welcome").stop(true, true).slideToggle(334);
    $(item).children("a").stop(true, true).toggleClass("active-open-title");
};

HomeCtrl.prototype.slideToogle = function(e, itemServ){
    itemServ.csExpandido = !itemServ.csExpandido;
    this.updateServCustomizado(itemServ);
    var style = "alert-filho";
    if (itemServ.csExpandido === true) {
        style = "alert-filho-aberto";
    }
    
    itemServ.clazz = style;

    var item = $(e.target);
    
    item.removeClass(".alert-filho");
    item.removeClass(".alert-filho-aberto");
    
    item.next(".alert-filho").addClass(style);
    
    if(item.hasClass("alert-home")){
        item.next(".alert-filho").stop(true, true).toggleClass("alert-filho-aberto");
        item.stop(true, true).toggleClass("active-alert");     
    } else  if(item.hasClass("active-open")){
        item.parent(".alert-home").next().stop(true, true).toggleClass("alert-filho-aberto");
        item.stop(true, true).toggleClass("active-alert"); 
    }

};


//HomeCtrl.prototype.preencherAlertas = function(retorno) {
//    
//    this.serviceServicoCustomizado.obterServicosHome(
//            {id: this.$rootScope.ecmUsuario.id},
//            this.preencherAlertasSucesso.bind(this),
//            this.preencherAlertasErro.bind(this)
//        );
//}
//
//HomeCtrl.prototype.preencherAlertasSucesso = function(retorno) {
//    var alertas = retorno.element;
//    
//    for ( var i = 0; i < alertas.length; i++ ) {
//        var style = "alert-filho";
//        if (alertas[i].csExpandido === true) {
//            style = "alert-filho-aberto";
//        }
//                
//        alertas[i].clazz = style;
//
//    }
//    
//    this.scope("alertas", alertas);
//};
//
//HomeCtrl.prototype.preencherAlertasErro = function(retorno) {
//    console.log(retorno);
//}
//
//
//HomeCtrl.prototype.updateServCustomizado = function(item){
//    var servCustom = {id: item.id, csExpandido: item.csExpandido, nbOrdem: item.nbOrdem};
//    this.serviceServicoCustomizado.updateServico(servCustom);
//}
