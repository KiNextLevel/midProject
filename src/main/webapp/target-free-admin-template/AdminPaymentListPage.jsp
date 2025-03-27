<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>결제 확인 페이지</title>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="assets/materialize/css/materialize.min.css" media="screen,projection"/>
    <!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FontAwesome Styles-->
    <link href="assets/css/font-awesome.css" rel="stylesheet"/>
    <!-- Morris Chart Styles-->
    <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet"/>
    <!-- Custom Styles-->
    <link href="assets/css/custom-styles.css" rel="stylesheet"/>
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="assets/js/Lightweight-Chart/cssCharts.css">
</head>
<body>
<div id="wrapper">
    <nav class="navbar navbar-default top-navbar" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand waves-effect waves-dark" href="adminPage.do"><i class="large material-icons">track_changes</i>
                <strong>target</strong></a>

            <div id="sideNav" href=""><i class="material-icons dp48">toc</i></div>
        </div>

        <ul class="nav navbar-top-links navbar-right">
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown4"><i
                    class="fa fa-envelope fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown3"><i
                    class="fa fa-tasks fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown2"><i
                    class="fa fa-bell fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
            <li><a class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown1"><i
                    class="fa fa-user fa-fw"></i> <b>John Doe</b> <i
                    class="material-icons right">arrow_drop_down</i></a></li>
        </ul>
    </nav>
    <!-- Dropdown Structure -->
    <ul id="dropdown1" class="dropdown-content">
        <li><a href="#"><i class="fa fa-user fa-fw"></i> My Profile</a>
        </li>
        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
        </li>
        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
        </li>
    </ul>
    <ul id="dropdown2" class="dropdown-content w250">
        <li>
            <a href="#">
                <div>
                    <i class="fa fa-comment fa-fw"></i> New Comment
                    <span class="pull-right text-muted small">4 min</span>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <div>
                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                    <span class="pull-right text-muted small">12 min</span>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <div>
                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                    <span class="pull-right text-muted small">4 min</span>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <div>
                    <i class="fa fa-tasks fa-fw"></i> New Task
                    <span class="pull-right text-muted small">4 min</span>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <div>
                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                    <span class="pull-right text-muted small">4 min</span>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a class="text-center" href="#">
                <strong>See All Alerts</strong>
                <i class="fa fa-angle-right"></i>
            </a>
        </li>
    </ul>
    <ul id="dropdown3" class="dropdown-content dropdown-tasks w250">
        <li>
            <a href="#">
                <div>
                    <p>
                        <strong>Task 1</strong>
                        <span class="pull-right text-muted">60% Complete</span>
                    </p>
                    <div class="progress progress-striped active">
                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60"
                             aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                            <span class="sr-only">60% Complete (success)</span>
                        </div>
                    </div>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <div>
                    <p>
                        <strong>Task 2</strong>
                        <span class="pull-right text-muted">28% Complete</span>
                    </p>
                    <div class="progress progress-striped active">
                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="28"
                             aria-valuemin="0" aria-valuemax="100" style="width: 28%">
                            <span class="sr-only">28% Complete</span>
                        </div>
                    </div>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <div>
                    <p>
                        <strong>Task 3</strong>
                        <span class="pull-right text-muted">60% Complete</span>
                    </p>
                    <div class="progress progress-striped active">
                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
                             aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                            <span class="sr-only">60% Complete (warning)</span>
                        </div>
                    </div>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <div>
                    <p>
                        <strong>Task 4</strong>
                        <span class="pull-right text-muted">85% Complete</span>
                    </p>
                    <div class="progress progress-striped active">
                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="85"
                             aria-valuemin="0" aria-valuemax="100" style="width: 85%">
                            <span class="sr-only">85% Complete (danger)</span>
                        </div>
                    </div>
                </div>
            </a>
        </li>
        <li class="divider"></li>
        <li>
    </ul>
    <ul id="dropdown4" class="dropdown-content dropdown-tasks w250 taskList">
        <li>
            <div>
                <strong>John Doe</strong>
                <span class="pull-right text-muted">
                                        <em>Today</em>
                                    </span>
            </div>
            <p>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...</p>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <div>
                <strong>John Smith</strong>
                <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
            </div>
            <p>Lorem Ipsum has been the industry's standard dummy text ever since an kwilnw...</p>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">
                <div>
                    <strong>John Smith</strong>
                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                </div>
                <p>Lorem Ipsum has been the industry's standard dummy text ever since the...</p>
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a class="text-center" href="#">
                <strong>Read All Messages</strong>
                <i class="fa fa-angle-right"></i>
            </a>
        </li>
    </ul>
    <!--/. NAV TOP  -->
    <!--/. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">

                <li>
                    <a href="boardPage.do" class="waves-effect waves-dark"><i class="fa fa-dashboard"></i> 게시글 페이지</a>
                </li>
                <li>
                    <a href="adminPaymentListPage.do"
                       class="waves-effect waves-dark"><i class="fa fa-desktop"></i> 결제 내역 페이지</a>
                </li>
                <li>
                    <a href="../Metronic-Shop-UI-master/theme/AdminReportPage.jsp" class="waves-effect waves-dark"><i
                            class="fa fa-bar-chart-o"></i> 신고 회원 관리 페이지</a>
                </li>
                <li>
                    <a href="tab-panel.html" class="waves-effect waves-dark"><i class="fa fa-qrcode"></i> 게시글 관리 페이지</a>
                </li>

                <li>
                    <a href="table.html" class="actie-menu waves-effect waves-dark"><i class="fa fa-table"></i>
                        Responsive Tables</a>
                </li>
                <li>
                    <a href="form.html" class="waves-effect waves-dark"><i class="fa fa-edit"></i> Forms </a>
                </li>


                <li>
                    <a href="#" class="waves-effect waves-dark"><i class="fa fa-sitemap"></i> Multi-Level Dropdown<span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="#">Second Level Link</a>
                        </li>
                        <li>
                            <a href="#">Second Level Link</a>
                        </li>
                        <li>
                            <a href="#">Second Level Link<span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="#">Third Level Link</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Link</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Link</a>
                                </li>

                            </ul>

                        </li>
                    </ul>
                </li>
                <li>
                    <a href="empty.html" class="waves-effect waves-dark"><i class="fa fa-fw fa-file"></i> Empty Page</a>
                </li>
            </ul>

        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div class="header">
            <h1 class="page-header">
                결제 내역
            </h1>
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">Tables</a></li>
                <li class="active">Data</li>
            </ol>

        </div>

        <div id="page-inner">

            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="card">
                        <div class="card-action">
                            Advanced Tables
                        </div>
                        <div class="card-content">
                            <div class="table-responsive">                                            <!-- 이게 정렬 -->
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>결제 날짜</th>
                                        <th>이메일</th>
                                        <th>결제 금액</th>
                                        <th>결제 상품</th>
                                        <th>CSS grade</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="data" items="${datas}">
                                        <tr class="odd gradeX">
                                            <td>data.paymentDate</td>
                                            <td>data.paymentEmail</td>
                                            <td>data.paymentPrice</td>
                                            <td class="center">data.productNum</td>
                                            <td class="center">X</td>
                                        </tr>
                                    </c:forEach>
                                    <tr class="odd gradeX">
                                        <td>이거 어디지</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                    </tr>
                                    <tr class="odd gradeX">
                                        <td>1</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                    </tr>
                                    <tr class="odd gradeX">
                                        <td>2</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                    </tr>
                                    <tr class="even gradeC">
                                        <td>Trident</td>
                                        <td>Internet Explorer 5.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">5</td>
                                        <td class="center">C</td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>여기 이름</td>
                                        <td>Internet Explorer 5.5</td>
                                        <td>Win 95+</td>
                                        <td class="center">5.5</td>
                                        <td class="center">A</td>
                                    </tr>
                                    <tr class="even gradeA">
                                        <td>티모</td>
                                        <td>Internet Explorer 6</td>
                                        <td>Win 98+</td>
                                        <td class="center">6</td>
                                        <td class="center">A</td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>모르가나</td>
                                        <td>Internet Explorer 7</td>
                                        <td>Win XP SP2+</td>
                                        <td class="center">7</td>
                                        <td class="center">A</td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>ㄱ</td>
                                        <td>Internet Explorer 7</td>
                                        <td>Win XP SP2+</td>
                                        <td class="center">7</td>
                                        <td class="center">A</td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>ㄴ</td>
                                        <td>Internet Explorer 7</td>
                                        <td>Win XP SP2+</td>
                                        <td class="center">7</td>
                                        <td class="center">A</td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
            <!-- /. ROW  -->

            <!-- /. ROW  -->

            <footer><p>All right reserved. Template by: <a
                    href="https://webthemez.com/admin-template/">WebThemez.com</a></p></footer>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->


    <!-- jQuery Js -->
    <script src="assets/js/jquery-1.10.2.js"></script>

    <!-- Bootstrap Js -->
    <script src="assets/js/bootstrap.min.js"></script>

    <script src="assets/materialize/js/materialize.min.js"></script>

    <!-- Metis Menu Js -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/js/morris/morris.js"></script>


    <script src="assets/js/easypiechart.js"></script>
    <script src="assets/js/easypiechart-data.js"></script>

    <script src="assets/js/Lightweight-Chart/jquery.chart.js"></script>
    <!-- DATA TABLE SCRIPTS -->
    <script src="assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
    <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable();
        });
    </script>
    <!-- Custom Js -->
    <script src="assets/js/custom-scripts.js"></script>

</div>
</body>

</html>