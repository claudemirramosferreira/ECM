function RequiredField(){
    return {
        require: '^form',
        link: function ($scope, $element, $attrs, $formCtrl){
    //        var msgError = $scope.$eval($attr.requiredField);
            console.log($formCtrl);
            console.log($formCtrl[$attrs.name].$invalid);
            console.log($($element).prev()[0]);
            var v =  $formCtrl.$name + '.' + $attrs.name + '.$invalid'; 
            
            $($element).prev().attr("ng-class", "{error: "+v+"}")
            $($element).attr("ng-class", "{error: "+v+"}")
        }
    };
};