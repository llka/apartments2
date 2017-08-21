(function () {
    var bookingApartmentId = -1;

    var $restInfo, $title, $main, $all, $available, $apartments;

    //Caching Selectors
    $(document).ready(function () {
        $restInfo = $('#restInfo');
        $title = $('#title');
        $main = $('#main');
        $all = $('#all');
        $available = $('#available');
        $apartments = $('#apartments');
    });

    // function Selector_Cache() {
    //     var collection = {};
    //
    //     function get_from_cache( selector ) {
    //         if ( undefined === collection[ selector ] ) {
    //             collection[ selector ] = $( selector );
    //         }
    //
    //         return collection[ selector ];
    //     }
    //
    //     return { get: get_from_cache };
    // }
    //
    // var selectors = new Selector_Cache();
    //
    // // Usage $( '#element' ) becomes
    // selectors.get( '#element' );

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
                    alert("New apartment is registered")
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
                    duration: $('#duration').val()
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

        var bookModal = document.getElementById('id01')
        bookModal.style.display = 'none';
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
                    $main.css("visibility","hidden");
                    $all.hide();
                    $available.hide();
                }
            });
        });
    }

    function showBookModal(id) {
        var bookModal = document.getElementById('id01')
        bookModal.style.display = 'block';
        bookingApartmentId = id;
    }

    function perfectShow(array) {
        $apartments.empty();
        $available.hide();
        $all.show();

        for (var i = 0; i < array.length; i++) {
            $apartments.append('<div class="allRow">' +
                ' <div class="id">' + array[i].apartmentId + '</div>' +
                ' <div class="date">' + array[i].bookedFrom + '</div>' +
                ' <div class="date">' + array[i].bookedTo + '</div>' +
                '</div>');
        }
        $main.css("visibility","visible");
    }

    function perfectShowAvailable(array) {
        $apartments.empty();
        $all.hide();
        $available.show();

        for (var i = 0; i < array.length; i++) {
            $apartments.append(
                '<div class="allRow">' +
                ' <div class="id">' + array[i].apartmentId + '</div>' +
                ' <div class="date">' + '</div>' +
                ' <div class="date"><button class="button" onclick=\"showBookModal(' + array[i].apartmentId + ')\">Book</button></div>' +
                '</div>');
        }
        $main.css("visibility","visible");
    }

    function showRestInfo() {
        $main.css("visibility","visible");
        $apartments.empty();
        $all.hide();
        $available.hide();
        $restInfo.show();
    }

    function closeModalOnCross() {
        document.getElementById('id01').style.display = 'none';
    }

    $(document).ready(function(){
        $("#showAllBtn").click(showAll);
        $("#showAvailableBtn").click(showAvailable);
        $('#addBtn').click(add);
        $('#deleteAllBtn').click(deleteAll);
        $('#showRestInfoBtn').click(showRestInfo);
        $('#closeCross').click(closeModalOnCross);
        $('#bookBtn').click(book);
    });
 })();