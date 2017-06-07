'use strict';

describe('Controller Tests', function() {

    describe('Fisico Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFisico, MockObra, MockObraatividade;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFisico = jasmine.createSpy('MockFisico');
            MockObra = jasmine.createSpy('MockObra');
            MockObraatividade = jasmine.createSpy('MockObraatividade');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Fisico': MockFisico,
                'Obra': MockObra,
                'Obraatividade': MockObraatividade
            };
            createController = function() {
                $injector.get('$controller')("FisicoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:fisicoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
