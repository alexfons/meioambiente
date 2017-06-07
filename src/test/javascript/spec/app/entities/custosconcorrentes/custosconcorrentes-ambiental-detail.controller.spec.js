'use strict';

describe('Controller Tests', function() {

    describe('Custosconcorrentes Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCustosconcorrentes, MockPlanocontas, MockFonte, MockCategoriainversao;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCustosconcorrentes = jasmine.createSpy('MockCustosconcorrentes');
            MockPlanocontas = jasmine.createSpy('MockPlanocontas');
            MockFonte = jasmine.createSpy('MockFonte');
            MockCategoriainversao = jasmine.createSpy('MockCategoriainversao');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Custosconcorrentes': MockCustosconcorrentes,
                'Planocontas': MockPlanocontas,
                'Fonte': MockFonte,
                'Categoriainversao': MockCategoriainversao
            };
            createController = function() {
                $injector.get('$controller')("CustosconcorrentesAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:custosconcorrentesUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
