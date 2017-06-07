'use strict';

describe('Controller Tests', function() {

    describe('Movimentacao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockMovimentacao, MockListamovimentacao, MockContabancaria, MockFonte;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockMovimentacao = jasmine.createSpy('MockMovimentacao');
            MockListamovimentacao = jasmine.createSpy('MockListamovimentacao');
            MockContabancaria = jasmine.createSpy('MockContabancaria');
            MockFonte = jasmine.createSpy('MockFonte');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Movimentacao': MockMovimentacao,
                'Listamovimentacao': MockListamovimentacao,
                'Contabancaria': MockContabancaria,
                'Fonte': MockFonte
            };
            createController = function() {
                $injector.get('$controller')("MovimentacaoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:movimentacaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
