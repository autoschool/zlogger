describe('blogCtrl', function() {

	var $httpBackend, $rootScope, $interval, createController, getRequest, 
    testGetData;
  var TEST_POST_DATA = { title: 'test', message : 'test' };
  var TEST_POST_LINK = '/user/addpost';
	
  beforeEach(module('zlogger.controllers'));

	beforeEach(inject(function($injector) {
		
    $httpBackend = $injector.get('$httpBackend');
    $httpBackend.when('POST', TEST_POST_LINK)
      .respond(201, '');

		$rootScope = $injector.get('$rootScope');
		var $controller = $injector.get('$controller');

		createController = function() {
			return $controller('blogCtrl', { '$scope' : $rootScope });
		};
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

  it('should submit data', function() {
    var controller = createController();
    spyOn(controller, 'windowReload').andCallFake(function(){});
    
    controller.post = TEST_POST_DATA;
    $httpBackend.expectPOST(TEST_POST_LINK, TEST_POST_DATA); 
    controller.submit(true);
    $httpBackend.flush();
  });

  it('shouldn\'t allow sending new data before getting answer from server', function() {
    var controller = createController();
    spyOn(controller, 'windowReload').andCallFake(function(){});
    controller.post = TEST_POST_DATA;

    $httpBackend.expectPOST(TEST_POST_LINK);
    controller.submit(true);
    controller.submit(true);
    $httpBackend.flush();
  });
});