$( document ).ready(function() {
    alert($("#OSENIAS").html());
    
    function atualizaDados($class,$classHeight) {
        alert("ok")
        var parametro = $classHeight;
        var vetMaior = []; 
        $class.each( function (index, value){       

            vetMaior = $(this).height();

            if( parametro < vetMaior ){
                parametro = vetMaior;     
            }
        });
        $class.css({"height":parametro});
    };
    
    var panel = $("#a-panel");
    var panelHeight = $("#a-panel").height();
    atualizaDados(panel,panelHeight);
    
    //var panela = $(".panel-a");
    //var panelHeighta = $(".panel-a").height();
    //atualizaDados(panela,panelHeighta);

});