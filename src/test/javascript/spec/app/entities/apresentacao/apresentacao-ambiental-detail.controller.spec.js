'use strict';

describe('Controller Tests', function() {

    describe('Apresentacao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockApresentacao, MockObra, MockOcorrenciaapresentacao, MockFoto;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockApresentacao = jasmine.createSpy('MockApresentacao');
            MockObra = jasmine.createSpy('MockObra');
            MockOcorrenciaapresentacao = jasmine.createSpy('MockOcorrenciaapresentacao');
            MockFoto = jasmine.createSpy('MockFoto');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Apresentacao': MockApresentacao,
                'Obra': MockObra,
                'Ocorrenciaapresentacao': MockOcorrenciaapresentacao,
                'Foto': MockFoto
            };
            createController = function() {
                $injector.get('$controller')("ApresentacaoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:apresentacaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
