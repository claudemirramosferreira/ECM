/* global CoreCtrl */
function TopCtrl(){
    CoreCtrl.apply(this, arguments);
    this.definirIdioma();
    
//    this.serviceAuth = this.inject('Auth');  FIXME continuar seguranca
}

$.extend(TopCtrl.prototype, CoreCtrl.prototype);

TopCtrl.prototype.redirecionar = function(){
    // FIXME continuar
}


/**
 * Muda o idioma
 */
TopCtrl.prototype.mudarIdioma = function(){
    var id = this.$translate.use();
    id = (id === 'en_Us')?'pt_Br':'en_Us';
    
    this.$translate.use(id);
    this.$cookies.put('idioma', id);
    this.definirIdiomaTexto(id);
};

/**
 * Define o idioma
 */
TopCtrl.prototype.definirIdioma = function(){
    var id = this.$cookies.get('idioma');
    id = id?id:'pt_Br';
    this.$translate.use(id);
    this.definirIdiomaTexto(id);
};

/**
 * Muda o texto do idioma
 * @param id
 */
TopCtrl.prototype.definirIdiomaTexto = function(id){
    this.scope('idioma', (id === 'en_Us')?'Português':'English');
};


/**
 * Vai para a tela inicial
 */
TopCtrl.prototype.irTelaHome = function(){
    this.$location.path('/');
};
