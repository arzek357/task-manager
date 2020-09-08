angular.module('app').controller('projectsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/task-manager';
    fillTable = function () {
        $http.get(contextPath + '/api/v1/projects')
            .then(function (response) {
                $scope.ProjectsList = response.data;
            });
    };

    $scope.submitCreateNewProject = function () {
            $scope.status={success : true};
        if($scope.newProject.name!=null){
            $scope.status={success : false};
            $http.post(contextPath + '/api/v1/projects', $scope.newProject)
                .then(function (response) {
                    $scope.ProjectsList.push(response.data);
                    document.getElementById("newProjectName").value=null;
                    document.getElementById("newProjectDescription").value=null;
                    delete $scope.newProject;
                });
        }
    }

    fillTable();
});