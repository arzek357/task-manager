angular.module('app').controller('in_taskController', function ($scope, $http,$routeParams) {
    const contextPath = 'http://localhost:8189/task-manager';
    const taskId = $routeParams.id;

    fillTable = function () {
        $http.get(contextPath + '/api/v1/tasks/'+taskId)
            .then(function (response) {
                $scope.task = response.data;
            });
    };

    fillTable();
});