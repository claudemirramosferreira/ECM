function DisableAll() {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var disabledElement = (attrs.disableElementId) ? document.getElementById(attrs.disableElementId) : element[0];
            scope.$watch(attrs.disableAll, function (isDisabled) {
                if (isDisabled)
                    disableAll(disabledElement);
                else
                    enableAll(disabledElement);
            });

            scope.$on('$destroy', function () {
                enableAll(disabledElement);
            });
        }
    };
}


var disableAll = function (element) {
    angular.element(element).addClass('disable-all');
    element.style.color = 'gray';
    disableElements(element.getElementsByTagName('input'));
    disableElements(element.getElementsByTagName('button'));
    disableElements(element.getElementsByTagName('textarea'));
    disableElements(element.getElementsByTagName('select'));
    disableElements(element.getElementsByTagName('div'));
    element.addEventListener('click', preventDefault, true);
};


var enableAll = function (element) {
    angular.element(element).removeClass('disable-all');
    element.style.color = 'inherit';
    enableElements(element.getElementsByTagName('input'));
    enableElements(element.getElementsByTagName('button'));
    enableElements(element.getElementsByTagName('textarea'));
    enableElements(element.getElementsByTagName('select'));
    disableElements(element.getElementsByTagName('div'));
    element.removeEventListener('click', preventDefault, true);
};


var preventDefault = function (event) {
    event.stopPropagation();
    event.preventDefault();
    return false;
};


var disableElements = function (elements) {
    var len = elements.length;
    for (var i = 0; i < len; i++) {

        if (elements[i].disabled === false) {
            elements[i].disabled = true;
            elements[i].disabledIf = true;
        }

    }
};

var enableElements = function (elements) {
    var len = elements.length;
    for (var i = 0; i < len; i++) {
        if (elements[i].disabled === true && elements[i].disabledIf === true) {
            elements[i].disabled = false;
            elements[i].disabledIf = null;
        }
    }
};