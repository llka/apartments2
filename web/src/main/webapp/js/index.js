(function () {
    var bookingApartmentId = -1;
    var bookButtonsId = [];

    var $restInfo, $title, $main, $all, $available, $apartments, $modal;

    //Caching Selectors
    $(document).ready(function () {
        $restInfo = $('#restInfo');
        $title = $('#title');
        $main = $('#main');
        $all = $('#all');
        $available = $('#available');
        $apartments = $('#apartments');
        $modal = $('#id01');
    });

    function showAll() {
        $restInfo.hide();
        $(document).ready(function () {
            $.ajax({
                url: '/Ajax',
                data: {
                    command: "SHOW_ALL"
                },
                contentType: "application/json",
                success: function (data) {
                    $title.html("All");
                    perfectShow(data);
                }
            });
        });
    }

    function showAvailable() {
        $restInfo.hide();
        $(document).ready(function () {
            $.ajax({
                url: '/Ajax',
                data: {
                    command: "SHOW_AVAILABLE"
                },
                contentType: "application/json",
                success: function (data) {
                    $title.html("Available");
                    perfectShowAvailable(data);
                }
            });
        });
    }

    function add() {
        $restInfo.hide();
        $(document).ready(function () {
            $.ajax({
                url: '/Ajax',
                data: {
                    command: "ADD"
                },
                contentType: "application/json",
                success: function (data) {
                    alert("New apartment is registered");
                    $title.html("All");
                    perfectShow(data);
                }
            });
        });
    }

    function book() {
        $restInfo.hide();
        $(document).ready(function () {
            $.ajax({
                url: '/Ajax',
                data: {
                    command: "BOOK",
                    apartmentId: bookingApartmentId,
                    duration: $('#duration').val() * 24
                },
                contentType: "application/json",
                success: function (data) {
                    alert("You have booked apartment number " + bookingApartmentId);
                    $title.html("All");
                    perfectShow(data);
                    bookingApartmentId = -1;
                }
            });
        });

        $modal.hide();
    }

    function deleteAll() {
        $restInfo.hide();
        $(document).ready(function () {
            $.ajax({
                url: '/Ajax',
                data: {
                    command: "DELETE_ALL"
                },
                contentType: "application/json",
                success: function (data) {
                    alert("There are no apartments now.")
                    $main.css("visibility", "hidden");
                    $all.hide();
                    $available.hide();
                }
            });
        });
    }

    function showBookModal(id) {
        bookingApartmentId = id;
        $modal.show();
    }

    function perfectShow(array) {
        $apartments.empty();
        $available.hide();
        $all.show();

        for (var i = 0; i < array.length; i++) {
            $apartments.append('<div class="apartmentsRow">' +
                ' <div class="apartmentsRow__item apartmentsRow__item_size_s">' + array[i].apartmentId + '</div>' +
                ' <div class="apartmentsRow__item apartmentsRow__item_size_m">' + array[i].bookedFrom + '</div>' +
                ' <div class="apartmentsRow__item apartmentsRow__item_size_m">' + array[i].bookedTo + '</div>' +
                '</div>');
        }
        $main.css("visibility", "visible");
    }

    function perfectShowAvailable(array) {
        $apartments.empty();
        $all.hide();
        $available.show();

        bookButtonsId.splice(0, bookButtonsId.length);

        for (var i = 0; i < array.length; i++) {
            $apartments.append(
                '<div class="apartmentsRow">' +
                ' <div class="apartmentsRow__item apartmentsRow__item_size_s">' + array[i].apartmentId + '</div>' +
                ' <div class="apartmentsRow__item apartmentsRow__item_size_m">' + '</div>' +
                ' <div class="apartmentsRow__item apartmentsRow__item_size_m"><button id=\"showBookModalBtn_' + array[i].apartmentId + '\" class=\"button\"' +
                '>Book</button></div>' +
                '</div>');
            bookButtonsId.push("#showBookModalBtn_" + array[i].apartmentId);
        }
        $main.css("visibility", "visible");
        $(document).ready(function () {
            for (var j = 0; j < bookButtonsId.length; j++) {
                $(bookButtonsId[j]).click(function () {
                    showBookModal(this.id.split("_")[1]);
                });
            }
        });
    }

    function showRestInfo() {
        $main.css("visibility", "visible");
        $apartments.empty();
        $all.hide();
        $available.hide();
        $restInfo.show();
    }

    function closeModalOnCross() {
        $modal.hide();
    }

    $(document).ready(function () {
        $("#showAllBtn").click(showAll);
        $("#showAvailableBtn").click(showAvailable);
        $('#addBtn').click(add);
        $('#deleteAllBtn').click(deleteAll);
        $('#showRestInfoBtn').click(showRestInfo);
        $('#closeCross').click(closeModalOnCross);
        $('#bookBtn').click(book);
    });
})();