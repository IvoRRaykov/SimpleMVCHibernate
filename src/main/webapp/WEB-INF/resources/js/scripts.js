$(document).ready(function () {

    var listA = [];
    $('#btn').click(function () {

        var todoText = $("#input").val();
        $("#input").val("");
        $("ul").append("<li style='list-style: none'><span><i></i></span><a class='text' >" + todoText + "</a></li>");
        listA.push(todoText);


    });



    $("#submitbutton").click(function () {
        var code = document.getElementById("codeHandler").value;

        $.ajax({
            type : "POST",
            url : "/attachSongs",
            data : {
                myArray: listA,
                product_code: code
            },
            complete: function() {
                window.location.replace("/product/manage?message");
            },
            success : function(data) {
                if (data.redicrect) {
                    window.location.replace("/product/manage?message");
                }

            },
            error: function(xhr, response) {
                window.location.replace("/product/manage?message");
            }

        });
        }
    );

    $('.wtf').click(function(){
        var id = $(this).attr("id");
        $('#dpc'.concat(id)).toggle(function () {
            var test = ($(this).css('display') == 'none')? "none" : "block";
            $(this).css('display', test);

        });
    });


});


