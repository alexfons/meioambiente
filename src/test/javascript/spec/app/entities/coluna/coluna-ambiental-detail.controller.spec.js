'use strict';

describe('Controller Tests', function() {

    describe('Coluna Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockColuna, MockLinha;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockColuna = jasmine.createSpy('MockColuna');
            MockLinha = jasmine.createSpy('MockLinha');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Coluna': MockColuna,
                'Linha': MockLinha
            };
            createController = function() {
                $injector.get('$controller')("ColunaAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:colunaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});