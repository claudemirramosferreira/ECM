/* global angular */

function CoreCtrl($injector, $scope) {

    this.$injector = $injector;
    this.$scope = $scope;

    var injected = ["$rootScope", "$translate", "$cookies", "$modal", "$location", "$routeParams", "$http", "$timeout", "md5"];

    angular.forEach(injected, function (objectName) {
        this[objectName] = this.inject(objectName);
    }.bind(this));

    this.alerts = [];
    this.scope('alerts', this.alerts);

    this.$rootScope.TOKEN = this.$cookies.get("TOKEN");
}

CoreCtrl.prototype.scope = function (name, value) {
    if (value === undefined) {
        return this.$scope[name];
    } else {
        name = name.split(",");
        for (var n in name) {
            this.$scope[name[n].trim()] = value;
        }
    }
};

CoreCtrl.prototype.inject = function (name) {
    return this.$injector.get(name);
};


CoreCtrl.prototype.showAlert = function (messagem, typeAlert) {

    $('html, body').animate({scrollTop: 0}, 'fast');

    this.alerts.push({
        msg: messagem,
        type: typeAlert
    });

    this.scope('alerts', this.alerts);
};

CoreCtrl.prototype.closeAlert = function (index) {
    this.alerts.splice(index, 1);
    this.scope('alerts', this.alerts);
};

CoreCtrl.prototype.cleanAlert = function () {
    this.alerts = [];
    this.scope('alerts', this.alerts);
};

CoreCtrl.prototype.hasValue = function (object) {

    var hasValue = false;

    angular.forEach(object, function (v) {
        if (!(v === undefined || v === null || v.length === 0)) {
            hasValue = true;
        }
    });

    return hasValue;
};

CoreCtrl.prototype.cancelar = function (item) {
    this.cleanAlert();
    this.showModalCancelar(item);
};

/**
 * Faz a exclusão
 * @param {object} item
 */
CoreCtrl.prototype.excluir = function (item) {
    this.cleanAlert();
    this.showModalExcluir(item);
};

/**
 * Cancela a exclusão
 */
CoreCtrl.prototype.excluirCancelar = function () {
};

/**
 * Confirmar a exclusão
 * @param {object} item
 */
CoreCtrl.prototype.excluirConfirmar = function (item) {
};

CoreCtrl.prototype.clickCancelar = function () {
};


CoreCtrl.prototype.clickConfirmar = function (item) {
};

/**
 * Resposta padrão
 * @param {object} resposta
 */
CoreCtrl.prototype.excluirResultado = function (resposta) {
    this.showAlert("Registro excluir com succeso", "success");
};

/**
 * Quando ocorre erro na exclusão
 */
CoreCtrl.prototype.excluirError = function () {
    this.showAlert("Erro ao tentar excluir o registro", "danger");
};

/**
 * Mostra o modal
 * @param {object} item
 */
CoreCtrl.prototype.showModalExcluir = function (item) {

    var
            ok = this.excluirConfirmar.bind(this, item),
            cancel = this.excluirCancelar.bind(this);

    this.$modal.open({
        templateUrl: 'views/default/excluir-modal.html',
        controller: function ($scope, $modalInstance) {
            $scope.ok = function () {
                ok();
                $modalInstance.close();
            };

            $scope.cancel = function () {
                cancel();
                $modalInstance.dismiss('cancel');
            };
        },
        windowClass: 'quest-modal'
    });
};

CoreCtrl.prototype.showModalCancelar = function (item) {

    var
            ok = this.clickConfirmar.bind(this, item),
            cancel = this.clickCancelar.bind(this);

    this.$modal.open({
        templateUrl: 'views/default/cancelar-modal.html',
        controller: function ($scope, $modalInstance) {
            $scope.ok = function () {
                ok();
                $modalInstance.close();
            };

            $scope.cancel = function () {
                cancel();
                $modalInstance.dismiss('cancel');
            };
        },
        windowClass: 'quest-modal'
    });
};

CoreCtrl.prototype.removerElementosJson = function (objects, elements) {
    if (Array.isArray(objects)) {
        for (var y in objects) {
            this.removerElementosJson(objects[y], elements);
        }
    } else {
        if (objects) {
            var keys = Object.keys(objects);
            for (var i in keys) {
                for (var x in elements) {
                    if (keys[i] === elements[x]) {
                        delete objects[keys[i]];
                    }
                }
            }
        }
    }
};

CoreCtrl.prototype.addElementosJson = function (objects, elements) {
    if (Array.isArray(objects)) {
        for (var y in objects) {
            this.addElementosJson(objects[y], elements);
        }
    } else {
        if (objects) {
            var keys = Object.keys(objects);
            for (var i in keys) {
                for (var x in elements) {
                    if (keys[i] === elements[x]) {
                    } else {
                        delete objects[keys[i]];
                    }
                }
            }
        }
    }
};

CoreCtrl.prototype.convertBinarioParaBlob = function (binario, tipoArquivo) {
    var contentType = tipoArquivo || '', sliceSize = sliceSize || 512,
            byteCharacters = atob(binario), byteArrays = [];

    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {

        var slice = byteCharacters.slice(offset, offset + sliceSize),
                byteNumbers = new Array(slice.length);

        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        byteArrays.push(new Uint8Array(byteNumbers));
    }

    var blob = new Blob(byteArrays, {type: contentType});
    var URL = window.URL || window.webkitURL;
    var blobUrl = URL.createObjectURL(blob);
    blob.url = blobUrl;

    return blob;
};

/**
 * Retorna a descricao do id na lista informada. 
 * Os itens da lista devem ter os atributos id e descricao.
 * @param {type} id
 * @param {type} lista
 * @returns {unresolved}
 */
CoreCtrl.prototype.getDescricaoPeloCodigo = function (id, lista) {
    var result;
    for (var index in lista) {
        if (lista[index].id === id) {
            result = lista[index].descricao;
            break;
        }
    }
    return result;
};
