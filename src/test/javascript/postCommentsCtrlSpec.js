describe('postCommentsCtrl', function() {

	var $httpBackend, $rootScope, $interval, createController, getRequest, 
    testGetData;
  var TEST_POST_DATA = { message : 'test' };
  var TEST_REFRESH_INTERVAL = 300000;
  var TEST_ID = 1;
  var TEST_GET_LINK = '/post/1/commentaries';
  var TEST_POST_LINK = '/user/addcomment/1';
	
  beforeEach(module('zlogger.controllers'));

	beforeEach(inject(function($injector) {
    testGetData = [
      { message : 'test1' },
      { message : 'test2' },
      { message : 'test3'}];
		
    $httpBackend = $injector.get('$httpBackend');
		getRequest = $httpBackend.when('GET', TEST_GET_LINK)
			.respond(testGetData);
    $httpBackend.when('POST', TEST_POST_LINK)
      .respond(201, '');

		$rootScope = $injector.get('$rootScope');
		var $controller = $injector.get('$controller');
		$interval = $injector.get('$interval');

		createController = function() {
			return $controller('postCommentsCtrl', {'$scope' : $rootScope, 
        'postId': TEST_ID, 'REFRESH_INTERVAL' : TEST_REFRESH_INTERVAL});
		};
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	it('should fetch list of commentaries', function() {
		$httpBackend.expectGET(TEST_GET_LINK);
		var controller = createController();
		$httpBackend.flush();
		expect($rootScope.commentaries.length).toBe(testGetData.length);
	});

	it('should try to refresh commentaries', function () {
		$httpBackend.expectGET(TEST_GET_LINK);
		var controller = createController();
		$httpBackend.flush();

		$httpBackend.expectGET(TEST_GET_LINK);
    $interval.flush(TEST_REFRESH_INTERVAL);
    $httpBackend.flush();
	});

  it('should change commentaries model on refresh', function() {
    var TEST_MESSAGE = {message : 'test4'};

    $httpBackend.expectGET(TEST_GET_LINK);
    var controller = createController();
    $httpBackend.flush();

    testGetData.push(TEST_MESSAGE);
    $httpBackend.expectGET(TEST_GET_LINK);
    $interval.flush(TEST_REFRESH_INTERVAL);
    $httpBackend.flush();  

    expect($rootScope.commentaries[3].message).toBe(TEST_MESSAGE.message);
  });

  it('should submit data', function() {
    var controller = createController();
    $httpBackend.flush();
    
    controller.commentary = TEST_POST_DATA;
    $httpBackend.expectPOST(TEST_POST_LINK, TEST_POST_DATA); 
    controller.submit(true);
    $httpBackend.flush();
  });

  it('shouldn\'t allow sending new data before getting answer from server', function() {
    var controller = createController();
    $httpBackend.flush();
    controller.commentary = TEST_POST_DATA;

    $httpBackend.expectPOST(TEST_POST_LINK);
    controller.submit(true);
    controller.submit(true);
    $httpBackend.flush();
  });
});