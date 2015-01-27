describe('postCommentsCtrl', function() {

	var $httpBackend, $rootScope, createController;
	beforeEach(module('zlogger.controllers'));

	beforeEach(inject(function($injector) {
		$httpBackend = $injector.get('$httpBackend');
		$httpBackend.when('GET', '/post/1/commentaries')
			.respond([{message : "test"}, {message: "other test"}]);

		$rootScope = $injector.get('$rootScope');
		var $controller = $injector.get('$controller');

		createController = function() {
			return $controller('postCommentsCtrl', {'$scope' : $rootScope, 'postId': 1});
		};
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	it('should fetch list of commentaries', function(postId) {
		$httpBackend.expectGET('/post/1/commentaries');
		var controller = createController();
		$httpBackend.flush();
		expect($rootScope.commentaries.length).toBe(2);
	});
});