function OnLastRepeat() {
    return function ($scope, $element, $attr) {
        if ($scope.$last)
            setTimeout(function () {
                $scope.$apply(function () {
                    $scope.$eval($attr['onLastRepeat']);
                });
            }, 1);
    };
}

function MaskDate() {
    return {
        restrict: 'A',
        link: function (scope, el, at) {
            var format = at.dateFormat;
            scope.$watch(at.ngModel, function (date) {
                var result = moment(date).format(format);
                el.val(result);
            });
        }
    };
}

function Digits() {
    return {
        require: 'ngModel',
        link: function ($scope, $element, $attr, ngModel) {
            $scope.$watch($attr["ngModel"], function (input) {
                if (input && typeof input === "string") {
                    input = input.replace(/\D/g, "");
                    ngModel.$setViewValue(input);
                    ngModel.$render();
                }

                return input;
            });
        }
    };
}

function Fdatepicker() {
    return function ($scope, $element, $attr) {
        $($element).fdatepicker({
            language: 'pt-BR',
            format: 'dd/mm/yyyy'
        });
    };
}

function FdatepickerMesAno() {
    return function ($scope, $element, $attr) {
        $($element).fdatepicker({
            language: 'pt-BR',
            format: 'mm/yyyy',
            startView: 1,
            viewMode: 1,
            minViewMode: 1
        });
    };
}

function CurrencyFormatDirective() {
    return {
        require: 'ngModel',
        link: function ($scope, $element, $attr, ngModel) {
            $scope.$watch($attr['ngModel'], function (input) {
                if (input && typeof input === "string") {
                    ngModel.$setViewValue(input.currencyFormat(null, ",", "."));
                    ngModel.$render();
                }

                return input;
            });
        }
    };
}

function SelectFilter() {
    return {
        restrict: "E",
        scope: {itens: "=", ngModel: "=", field: "@", ngChange: "=", ngDisabled: "="},
        templateUrl: "views/default/components/select-filter.html",
        require: 'ngModel',
        link: function ($scope, $element, $attrs) {
        },
        controller: function ($scope, $timeout) {

            $scope.focusGained = false;
            $scope.focusGainedShow = function () {
                $timeout(function () {
                    $scope.focusGained = true;
                }, 100);
            };

            $scope.focusGainedHide = function () {
                if (!$scope.ngModel || !$scope.ngModel[$scope.field] || $scope.ngModel[$scope.field].length <= 0) {
                    if (typeof $scope.ngChange === "function") {
                        $scope.ngChange({});
                    }
                }

                $timeout(function () {
                    $scope.focusGained = false;
                }, 150);
            };

            $scope.comparatoSearch = function (actual, expected) {
                if (actual && typeof actual === "object" && $scope.field) {
                    if (actual[$scope.field] && expected && expected.length >= 3) {
                        var r = new RegExp("^" + expected, "gi"),
                                test = r.test(actual[$scope.field]);
                        return test;
                    } else {
                        return true;
                    }
                }
            };

            $scope.selectItem = function (item) {
                $scope.ngModel = angular.copy(item);
                $scope.focusGained = false;
                if (typeof $scope.ngChange === "function") {
                    $scope.ngChange(angular.copy(item));
                }
            };
        }
    };
}

function FormatReal() {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            var variavel = "";
            scope.$watch(attrs.formatReal, function (newValue, oldValue) {
                var model = attrs['ngModel'];
                if (angular.isDefined(newValue)) {
                    var valor = "";
                    valor = String(newValue).replace(",", "");
                    valor = valor.replace(/\./g, "");
                    var arr = String(valor).split("");
                    var tamanho = valor.length;
                    var i;
                    variavel = "";
                    for (i = 0; i < tamanho; i++) {
                        if (i == (tamanho - 2) && tamanho > 2) {
                            variavel += ","
                        }
                        if (i == (tamanho - 5) && tamanho > 5) {
                            variavel += "."
                        }
                        if (i == (tamanho - 8) && tamanho > 8) {
                            variavel += "."
                        }
                        variavel += arr[i];
                    }

                    scope[model] = variavel;
                    element.val(variavel);
                }
            });

        }
    };
}

function Sortable() {
    return function ($scope, $element, $attr) {
        var options = $scope.$eval($attr["sortable"]);
        $($element).sortable(options);
    };
}

function TelefoneFormat() {
    return {
        require: 'ngModel',
        link: function ($scope, $element, $attr, ngModel) {
            $scope.$watch($attr['ngModel'], function (input) {
                if (input) {
                    var formato = new RegExp("^([0-9]{2})([0-9]{4,5})([0-9]{4})$", "gi"), tamanho = 11;
                    input = input.replace(/\D/g, "");

                    if (input.length > tamanho) {
                        input = input.substring(0, tamanho);
                    }

                    if (formato.test(input)) {
                        input = input.replace(formato, "($1)$2-$3");
                    }

                    ngModel.$setViewValue(input);
                    ngModel.$render();
                }
                return input;
            });
        }
    };
}


function RemoverEspaco() {
    return {
        require: 'ngModel',
        link: function ($scope, $element, $attr, ngModel) {
            $scope.$watch($attr["ngModel"], function (input) {
                if (input && typeof input === "string") {
                    input = input.replace(/^\s+|\s+$/g, "");
                    ngModel.$setViewValue(input);
                    ngModel.$render();
                }

                return input;
            });
        }
    };
}

function NgEnter() {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if (event.which === 13) {
                scope.$apply(function () {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
}

function MaskedInput() {
    return function ($scope, $element, $attr) {
        $($element).mask($attr["maskedInput"], $scope.$eval($attr["maskedInputOptions"]));
    };
}