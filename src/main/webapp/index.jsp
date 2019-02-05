<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<LINK TYPE="text/css" rel="stylesheet" href="/resources/news.css"/>
<head>
    <title>
        news
    </title>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-route.min.js"></script>

</head>
<body>
<div class="roof">
    <b style="margin-bottom: 20px;"><spring:message code="key.newsManagment"/></b>
    <b style="margin-left: 65%;"><a href="?lang=ru" style="margin: 15px;">Russian</a> <a href="?lang=en"
                                                                                         style="margin: 15px;">English</a></b>
    <security:authorize access="isAnonymous()">
        <a href="/showLogin"> <spring:message code="key.signIn"/></a> / <a href="/showReg"> <spring:message
            code="key.registration"/></a>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <spring:message code="key.youLoggedAs"/><b> <sec:authentication property="principal.username"/></b>
        <form action="/logout" method="post" style="float: right;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input value="<spring:message code="key.signOut"/>" type="submit">
        </form>
    </security:authorize>
</div>
<div class="smenu">
    <h2 align="center"><spring:message code="key.news"/></h2>
    <h4><a href="#viewNewsList"><spring:message code="key.newsList"/></a></h4>
    <security:authorize access="isAuthenticated()">
        <h4><a href="#addNews"><spring:message code="key.addNews"/></a></h4>
    </security:authorize>
</div>

<div ng-app="newsApp">

    <div ng-view></div>

    <script type="text/ng-template" id="addNews.htm">
        <form name="newsForm">
            title <input ng-model="news.title" required placeholder="Enter your title"/>
            <h6></h6>
            brief <input ng-model="news.brief" required/>
            <h6></h6>
            date <input ng-model="news.date" required/>
            <h6></h6>
            content <input ng-model="news.content" required/>
            <h6></h6>
            <button type="submit" ng-click="save(news, newsForm)">Save</button>
        </form>
    </script>

    <script type="text/ng-template" id="editNews.htm">
        <form name="newsForm">
            title <input ng-model="news.title" required placeholder="Enter your title"/>
            <h6></h6>
            brief <input ng-model="news.brief" required/>
            <h6></h6>
            date <input ng-model="news.date" required/>
            <h6></h6>
            content <input ng-model="news.content" required/>
            <h6></h6>
            <button type="submit" ng-click="saveUser(news, newsForm)">Save</button>
        </form>
    </script>

    <script type="text/ng-template" id="viewNewsList.htm">
        <div ng-repeat="news in newsList" class="content" align="center">
            <b> title </b>
            <span style="padding-left: 20px"> {{news.title}} </span>
            <span style="float: right;"> <i><u>{{news.date}}</u></i></span>
            <h5>{{news.brief}}</h5>
            <span style="float: right;">
            <button type="submit" ng-click="viewNews(news)">view</button>
            <button type="submit" ng-click="editNews(news)">edit</button>
            <input type="checkbox" value="{{news.id}}" name="removedNews">
       </span>
            <h5></h5>
        </div>
        <input style="float: right;" type="submit" value="delete"/>
    </script>

    <script type="text/ng-template" id="viewNews.htm">
            title <p>{{news.title}}</p>
            <h6></h6>
            brief
            <p>{{news.brief}}</p>
            <h6></h6>
            date
            <p>{{news.date}}</p>
            <h6></h6>
            content
            <p>{{news.content}}</p>
            <h6></h6>
    </script>

</div>
<script>
    var newsApp = angular.module("newsApp", ['ngRoute']);

    newsApp.service("NewsService", function () {
        var saveDate = {}

        function set(data) {
            saveDate = data;
        }

        function get() {
            return saveDate;
        }

        return {
            set: set,
            get: get
        }
    });

    newsApp.config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider.when('/addNews', {
                templateUrl: 'addNews.htm',
                controller: 'AddNewsController'
            }).when('/editNews', {
                templateUrl: 'editNews.htm',
                controller: 'EditNewsController'
            }).when('/viewNewsList', {
                templateUrl: 'viewNewsList.htm',
                controller: 'NewsController'
            }).when('/viewNews', {
                templateUrl: 'viewNews.htm',
                controller: 'ViewNewsController'
            }).otherwise({
                redirectTo: '/viewNewsList'
            });
        }]);

    newsApp.controller("NewsController", function ($scope, $http, $location, NewsService) {
        $http.get("/newsR").then(function success(response) {
            $scope.newsList = response.data;
        });
        $scope.editNews = function (news) {
            NewsService.set(news);
            $location.path("/editNews");
        }
        $scope.viewNews = function (news) {
            NewsService.set(news.id);
            $location.path("/viewNews");
        }
        $scope.deleteNews = function (news) {
            NewsService.set(news.id);
            $location.path("/viewNews");
        }
    });

    newsApp.controller("AddNewsController", function ($scope, $http, $location) {
        $scope.response = {};
        $scope.save = function (news, newsForm) {
            if (newsForm.$valid) {
                $http.post("/newsR", news).then(function success(response) {
                    $location.path("/viewNewsList");
                });
            }
        }
    });

    newsApp.controller("EditNewsController", function ($scope, $http, $location, NewsService) {
        $scope.news = NewsService.get();
        $scope.saveUser = function (news, newsForm) {
            if (newsForm.$valid) {
                $http.put("/newsR", news).then(function success(response) {
                    $location.path("/viewNewsList");
                });
            }
        }
    });

    newsApp.controller("ViewNewsController", function ($scope, $http, NewsService) {
        $http.get("/newsR/" + NewsService.get()).then(function success(response) {
            $scope.news = response.data;
        });
    });
</script>
</body>
</html>