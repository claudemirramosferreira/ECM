/* global CoreCtrl */
function TopCtrl(){
    CoreCtrl.apply(this, arguments);
    this.definirIdioma();
    
    this.serviceAuth = this.inject('Auth');
}

$.extend(TopCtrl.prototype, CoreCtrl.prototype);

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
 * Mostra o popup de informação
 */
TopCtrl.prototype.mostrarSobre = function(){
    this.$modal.open({
        templateUrl: 'views/default/sobre.html',
        windowClass:'splash-modal'
    });
};


/**
 * Vai para a tela inicial
 */
TopCtrl.prototype.irTelaHome = function(){
    this.$location.path('/');
};

/**
 * Sair do sistema
 */
TopCtrl.prototype.sairSistema = function(){
    
    
    this.serviceAuth.logout({}, function(){
         var url = window.location.href;
            var arr = url.split("/");
            var result = arr[0] + "//" + arr[2];
   
        this.$cookies.remove('TOKEN');
        this.$location.path('/autenticar/usuario').search({});
    }.bind(this));
};