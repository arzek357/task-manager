angular.module('app').controller('in_projectController', function ($scope, $http,$routeParams) {
    const contextPath = 'http://localhost:8189/task-manager';
    const projectId = $routeParams.id;
    $scope.CreatedTasks=[];
    $scope.InProgressTasks=[];
    $scope.InReviewTasks=[];
    $scope.InReworkTasks=[];
    $scope.CompletedTasks=[];
    $scope.CanceledTasks=[];

    fillTable = function () {
        $http.get(contextPath + '/api/v1/projects/'+projectId)
            .then(function (response) {
                $scope.project = response.data;
                $scope.tasks = $scope.project.tasks;
                $scope.tasks.forEach(sortFunc)
            });
    };

    $scope.submitAddNewUser = function () {
        $http.post(contextPath + '/api/v1/projects/'+projectId+'/adduser', $scope.newUser)
            .then(function (response) {
                document.getElementById("newUser").value="";
                delete $scope.newUser;
            });
    };

    $scope.submitCreateNewTask = function () {
        $http.post(contextPath + '/api/v1/tasks/'+projectId, $scope.newTask)
            .then(function (response) {
                $scope.CreatedTasks.push(response.data);
                document.getElementById("newTaskName").value="";
                delete $scope.newTask;
            });
    };

    function sortFunc(item) {
        if(item.state === 'CREATED'){
            $scope.CreatedTasks.push(item);
        }
        if(item.state === 'IN_PROGRESS'){
            $scope.InProgressTasks.push(item);
        }
        if(item.state === 'IN_REVIEW'){
            $scope.InReviewTasks.push(item);
        }
        if(item.state === 'IN_REWORK'){
            $scope.InReworkTasks.push(item);
        }
        if(item.state === 'COMPLETED'){
            $scope.CompletedTasks.push(item);
        }
        if(item.state === 'CANCELED'){
            $scope.CanceledTasks.push(item);
        }
    }

    fillTable();
});