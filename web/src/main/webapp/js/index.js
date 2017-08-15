var bookingApartmentId = -1;

function showAll() {
    $('#restInfo').hide();
    $(document).ready(function () {
        $.ajax({
            url: '/Ajax',
            data: {
                command: "SHOW_ALL"
            },
            contentType: "application/json",
            success: function (data) {
                $('#title').html("All");

                // var array = $.parseJSON(data);
                perfectShow(data);
            }
        });
    });
    showMain();
}

function showAvailable() {
    $('#restInfo').hide();
    showMain();
    $(document).ready(function () {
        $.ajax({
            url: '/Ajax',
            data: {
                command: "SHOW_AVAILABLE"
            },
            contentType: "application/json",
            success: function (data) {
                $('#title').html("Available");

                perfectShowAvailable(data);
            }
        });
    });
}

function add() {
    $('#restInfo').hide();
    showMain();
    $(document).ready(function () {
        $.ajax({
            url: '/Ajax',
            data: {
                command: "ADD"
            },
            contentType: "application/json",
            success: function (data) {
                alert("New apartment is registered")
                $('#title').html("Available");
                perfectShow(data);
            }
        });
    });
}

function book() {
    $('#restInfo').hide();
    showMain();
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
                $('#title').html("All");
                perfectShow(data);
                bookingApartmentId = -1;
            }
        });
    });

    var bookModal = document.getElementById('id01')
    bookModal.style.display='none';
}

function deleteAll() {
    $('#restInfo').hide();
    $(document).ready(function () {
        $.ajax({
            url: '/Ajax',
            data: {
                command: "DELETE_ALL"
            },
            contentType: "application/json",
            success: function (data) {
                alert("There are no apartments now.")
                $('#main').css("visibility","hidden");
                $('#all').hide();
                $('#available').hide();
            }
        });
    });
}

function showBookModal(id) {
    var bookModal = document.getElementById('id01')
    bookModal.style.display='block';
    bookingApartmentId = id;
}


function perfectShow(array) {
    $('#apartments').empty();
    $('#available').hide();
    $('#all').show();

    for(var i = 0; i < array.length; i++){
        $('#apartments').append('<div class="allRow">' +
                ' <div class="id">' + array[i].apartmentId + '</div>' +
                ' <div class="date">' + array[i].bookedFrom + '</div>' +
                ' <div class="date">' + array[i].bookedTo + '</div>' +
                '</div>');
    }
}

function perfectShowAvailable(array) {
    $('#apartments').empty();
    $('#all').hide();
    $('#available').show();

    for(var i = 0; i < array.length; i++){
        $('#apartments').append(
            '<div class="allRow">' +
            ' <div class="id">' + array[i].apartmentId + '</div>' +
            ' <div class="date">' + '</div>' +
            ' <div class="date"><button class="button" onclick=\"showBookModal(' + array[i].apartmentId + ')\">Book</button></div>' +
            '</div>');
    }
}

function showMain() {
    var main = document.getElementById("main");
    main.style.visibility = "visible";
}

function showRestInfo() {
    showMain();
    $('#apartments').empty();
    $('#all').hide();
    $('#available').hide();
    $('#restInfo').show();
}

function closeModal(){
    // Get the modal
    var modal1 = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal1 ) {
            modal1.style.display = "none";
        }
    }
}