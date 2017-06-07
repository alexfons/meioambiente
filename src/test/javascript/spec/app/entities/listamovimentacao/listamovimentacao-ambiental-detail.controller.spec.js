'use strict';

describe('Controller Tests', function() {

    describe('Listamovimentacao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockListamovimentacao, MockPlanocontas, MockMovimentacao;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockListamovimentacao = jasmine.createSpy('MockListamovimentacao');
            MockPlanocontas = jasmine.createSpy('MockPlanocontas');
            MockMovimentacao = jasmine.createSpy('MockMovimentacao');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Listamovimentacao': MockListamovimentacao,
                'Planocontas': MockPlanocontas,
                'Movimentacao': MockMovimentacao
            };
            createController = function() {
                $injector.get('$controller')("ListamovimentacaoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:listamovimentacaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
