angular.module('app').controller('in_projectController', function ($scope, $http,$routeParams) {
    const contextPath = 'http://localhost:8189/task-manager';
    const projectId = $routeParams.id;

    fillTable = function () {
        $http.get(contextPath + '/api/v1/projects/'+projectId)
            .then(function (response) {
                $scope.project = response.data;
            });
    };

    $scope.submitCreateNewTask = function () {
        $http.post(contextPath + '/api/v1/tasks/'+projectId, $scope.newTask)
            .then(function (response) {
                $scope.project.tasks.push(response.data);
                document.getElementById("newTaskName").value="";
                delete $scope.newTask;
            });
    };

    fillTable();
});