'use strict';

describe('Controller Tests', function() {

    describe('Contratoprojeto Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockContratoprojeto, MockContrato, MockResponsavel;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockContratoprojeto = jasmine.createSpy('MockContratoprojeto');
            MockContrato = jasmine.createSpy('MockContrato');
            MockResponsavel = jasmine.createSpy('MockResponsavel');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Contratoprojeto': MockContratoprojeto,
                'Contrato': MockContrato,
                'Responsavel': MockResponsavel
            };
            createController = function() {
                $injector.get('$controller')("ContratoprojetoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:contratoprojetoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
