var SERVER_URL = "http://localhost:8000/api";

function updateLeaderBoard() {
    $.ajax({
        url: SERVER_URL + "/leaders"
    }).then(function(data) {
        $('#leaderboard-body').empty();
        data.forEach(function(row) {
            $('#leaderboard-body').append('<tr><td>' + row.userId + '</td>' +
                '<td>' + row.totalScore + '</td>');
        });
    });
}

$(document).ready(function() {

    updateLeaderBoard();

    $("#refresh-leaderboard").click(function( event ) {
        updateLeaderBoard();
    });

});
