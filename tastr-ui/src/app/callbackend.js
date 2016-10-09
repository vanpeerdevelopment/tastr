(function () {
    $.getJSON("/api/tasting", function (data) {
        var lis = data
            .map(function (tasting) {
                return "<li>" + tasting.naam + "</li>";
            });
        $("<ul/>", {html: lis.join("")}).appendTo("body");
    });
})();
