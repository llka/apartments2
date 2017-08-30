<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="context" scope="page" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <title>Apartments</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel="stylesheet" href="css/common.css"/>
        <link type="text/css" rel="stylesheet" href="css/apartments.css"/>
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/index.js"></script>
    </head>
    <body>
        <%--header--%>
        <c:import url="${context}/WEB-INF/jspf/header.jsp"/>

        <div class="row">
            <div id="id01" class="mask">
                <div class="modal animate">
                    <span id="closeCross" class="close" >&times;</span>
                    <table class="modalMain">
                        <tr>
                            <td>Duration in days</td>
                            <td>
                                <input id="duration" type="number" name="duration" pattern="[1-7]{1}" min="1" max="7" value="1" required>
                            </td>
                        </tr>

                        <tr>
                            <td align=center colspan=2>
                                <button id="bookBtn" class="button">Book</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="col-1"></div>
            <div class="col-10">
                <div class="description" id="main">
                    <h3 id="title" style="text-align: center;"></h3>
                    <div id="all">
                        <div class="apartmentsRow">
                            <div class="apartmentsRow__item apartmentsRow__item_size_s">
                                №:
                            </div>
                            <div class="apartmentsRow__item apartmentsRow__item_size_m">
                                booked from:
                            </div>
                            <div class="apartmentsRow__item apartmentsRow__item_size_m">
                                booked to:
                            </div>
                        </div>
                    </div>
                    <div id="available">
                        <div class="apartmentsRow">
                            <div class="apartmentsRow__item apartmentsRow__item_size_s">
                                №:
                            </div>
                            <div class="apartmentsRow__item apartmentsRow__item_size_m">
                                costs:
                            </div>
                            <div class="apartmentsRow__item apartmentsRow__item_size_m">

                            </div>
                        </div>
                    </div>
                    <div id="apartments">
                    </div>
                    <div id="restInfo">
                        <div class="restInfoRow">
                            <div class="restInfoRow__url">
                                GET  localhost:8089/Ajax/apartments
                            </div>
                            <div class="restInfoRow__definition">
                                Show all apartments
                            </div>
                        </div>
                        <div class="restInfoRow">
                            <div class="restInfoRow__url">
                                GET  localhost:8089/Ajax/apartments?available=true
                            </div>
                            <div class="restInfoRow__definition">
                                Show all available
                            </div>
                        </div>
                        <div class="restInfoRow">
                            <div class="restInfoRow__url">
                                GET  localhost:8089/Ajax/apartments/id
                            </div>
                            <div class="restInfoRow__definition">
                                Show apartment by id
                            </div>
                        </div>
                        <div class="restInfoRow">
                            <div class="restInfoRow__url">
                                POST  localhost:8089/Ajax/apartments
                            </div>
                            <div class="restInfoRow__definition">
                                Add apartment
                            </div>
                        </div>
                        <div class="restInfoRow">
                            <div class="restInfoRow__url">
                                DELETE  localhost:8089/Ajax/apartments
                            </div>
                            <div class="restInfoRow__definition">
                                Delete all apartments
                            </div>
                        </div>
                        <div class="restInfoRow">
                            <div class="restInfoRow__url">
                                DELETE  localhost:8089/Ajax/apartments/id
                            </div>
                            <div class="restInfoRow__definition">
                                Delete apartment by id
                            </div>
                        </div>
                        <div class="restInfoRow">
                            <div class="restInfoRow__url">
                                PUT  localhost:8089/Ajax/apartments/id?days=x
                            </div>
                            <div class="restInfoRow__definition">
                                Book apartment by id on x days
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-1">
                <div class="fun fun_direction_rightBottom">
                    <div class="fun__title">
                        Do not scroll down!
                    </div>
                    <div class="fun__image">
                        <img src="/image/ripndip.png">
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>