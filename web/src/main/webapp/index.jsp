
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <head>
        <title>Apartments</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel="stylesheet" href="css/common.css"/>
        <link type="text/css" rel="stylesheet" href="css/apartments.css"/>
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/index.js" type="text/javascript"></script>
    </head>
    <body>
        <%--header--%>
        <div class="header" id="topnavStart">
            <div class="title">
                <a style="pointer-events: none;" href="#">Apartments</a>
            </div>
            <div>
                <a href="#" id="showAll" onclick="showAll()">All</a>
                <a href="#" id="showAvailable" onclick="showAvailable()">Available</a>
                <a href="#" id="add" onclick="add()">Add apartment</a>
                <a href="#" id="deleteAll" onclick="deleteAll()">Delete All</a>
                <a href="#" onclick="showRestInfo()">Rest helper</a>
            </div>
        </div>

        <div class="row">
            <div id="id01" class="mask">
                <div class="modal animate">
                    <span onclick="document.getElementById('id01').style.display='none'" class="close" >&times;</span>
                    <table class="modalMain">
                        <tr>
                            <td>Duration in days</td>
                            <td>
                                <input id="duration" type="number" name="duration" pattern="[1-7]{1}" min="1" max="7" value="1" required>
                            </td>
                        </tr>

                        <tr>
                            <td align=center colspan=2>
                                <button class="button" onclick="book()">Book</button>
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
                        <div class="allRow">
                            <div class="id">
                                №:
                            </div>
                            <div class="date">
                                booked from:
                            </div>
                            <div class="date">
                                booked to:
                            </div>
                        </div>
                    </div>
                    <div id="available">
                        <div class="allRow">
                            <div class="id">
                                №:
                            </div>
                            <div class="date">
                                costs:
                            </div>
                            <div class="date">

                            </div>
                        </div>
                    </div>
                    <div id="apartments">
                    </div>
                    <div id="restInfo">
                        <div class="allRow">
                            <div class="url">
                                localhost:8089/Ajax/apartments
                            </div>
                            <div class="definition">
                                Show or delete all apartments
                            </div>
                        </div>
                        <div class="allRow">
                            <div class="url">
                                localhost:8089/Ajax/apartments/available
                            </div>
                            <div class="definition">
                                Show all available
                            </div>
                        </div>
                        <div class="allRow">
                            <div class="url">
                                localhost:8089/Ajax/apartment
                            </div>
                            <div class="definition">
                                To add new room
                            </div>
                        </div>
                        <div class="allRow">
                            <div class="url">
                                localhost:8089/Ajax/apartment/x
                            </div>
                            <div class="definition">
                                Show/delete/book apartment № x
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-1">
                <div class="warning">
                    Do not scroll down!
                </div>
                <div class="cat">
                    <img src="/image/ripndip.png">
                </div>
            </div>
        </div>
        <script>
            closeModal();
        </script>
    </body>
</html>