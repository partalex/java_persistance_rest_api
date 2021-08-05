$(document).ready(function () {
    var API_KEY = "";
    var search = "";
    var duration = "any";
    var order = "relevance";
    var maxResults = 10
    var search_results;
    first_video = ""

    $("#myForm").submit(function (e) {
        e.preventDefault();

        search = $("#search").val();

        API_KEY = "AIzaSyDwuhGJZgTyvEckmFGqbqWJN4G2RQRfnhM";

        var url = `https://www.googleapis.com/youtube/v3/search?key=${API_KEY}
          &part=snippet&q=${search}&maxResults=${maxResults}&order=${order}&videoDuration=${duration}&type=video`;

        $.ajax({
            method: "GET",
            url: url,
            beforeSend: function () {
                $("#btn").attr("disabled", true);
                $("#results").empty();
            },
            success: function (data) {
                search_results = data;
                console.log(search_results);
                search_results["items"].forEach(element => console.log(element));
                $("#btn").attr("disabled", false);
                // displayVideos(data);
            },
        });
    });

    $("#search").change(function () {
        search = $("#search").val();
    });


});