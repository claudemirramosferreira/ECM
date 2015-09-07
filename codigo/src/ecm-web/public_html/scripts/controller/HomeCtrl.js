/* global CoreCtrl */
function HomeCtrl(){
    
    CoreCtrl.apply(this, arguments);
    
    this.showAlert('msg.login.efetuato', 'success');
    this.mensagemBemVindo();
    
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