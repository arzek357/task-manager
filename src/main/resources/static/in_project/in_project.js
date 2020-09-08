angular.module('app').controller('in_projectController', function ($scope, $http,$routeParams) {
    const contextPath = 'http://localhost:8189/task-manager';
    const projectId = $routeParams.id;

    fillTable = function () {
        $http.get(contextPath + '/api/v1/projects/'+projectId)
            .then(function (response) {
                $scope.TasksList = response.data.tasks;
            });
    };

    $scope.submitCreateNewTask = function () {
        $http.post(contextPath + '/api/v1/projects'+projectId, $scope.newTask)
            .then(function (response) {
                $scope.TasksList.push(response.data);
                document.getElementById("newProjectName").value="";
                document.getElementById("newProjectDescription").value="";
            });
    };

    fillTable();
});