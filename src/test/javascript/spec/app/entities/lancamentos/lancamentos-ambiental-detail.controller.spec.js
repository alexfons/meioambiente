'use strict';

describe('Controller Tests', function() {

    describe('Lancamentos Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockLancamentos, MockFonte, MockPlanocontas;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockLancamentos = jasmine.createSpy('MockLancamentos');
            MockFonte = jasmine.createSpy('MockFonte');
            MockPlanocontas = jasmine.createSpy('MockPlanocontas');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Lancamentos': MockLancamentos,
                'Fonte': MockFonte,
                'Planocontas': MockPlanocontas
            };
            createController = function() {
                $injector.get('$controller')("LancamentosAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:lancamentosUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
