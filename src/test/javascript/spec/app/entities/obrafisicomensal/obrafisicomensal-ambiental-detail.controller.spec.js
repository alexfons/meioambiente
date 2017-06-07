'use strict';

describe('Controller Tests', function() {

    describe('Obrafisicomensal Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockObrafisicomensal, MockFisico, MockAtividadeexecutadamensal;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockObrafisicomensal = jasmine.createSpy('MockObrafisicomensal');
            MockFisico = jasmine.createSpy('MockFisico');
            MockAtividadeexecutadamensal = jasmine.createSpy('MockAtividadeexecutadamensal');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Obrafisicomensal': MockObrafisicomensal,
                'Fisico': MockFisico,
                'Atividadeexecutadamensal': MockAtividadeexecutadamensal
            };
            createController = function() {
                $injector.get('$controller')("ObrafisicomensalAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:obrafisicomensalUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
