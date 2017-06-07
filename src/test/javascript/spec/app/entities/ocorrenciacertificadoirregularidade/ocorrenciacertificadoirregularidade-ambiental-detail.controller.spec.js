'use strict';

describe('Controller Tests', function() {

    describe('Ocorrenciacertificadoirregularidade Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockOcorrenciacertificadoirregularidade, MockOcorrencia;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockOcorrenciacertificadoirregularidade = jasmine.createSpy('MockOcorrenciacertificadoirregularidade');
            MockOcorrencia = jasmine.createSpy('MockOcorrencia');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Ocorrenciacertificadoirregularidade': MockOcorrenciacertificadoirregularidade,
                'Ocorrencia': MockOcorrencia
            };
            createController = function() {
                $injector.get('$controller')("OcorrenciacertificadoirregularidadeAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:ocorrenciacertificadoirregularidadeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
