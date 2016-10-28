$(document).ready(function () {


    var listA = [];
    $('#btn').click(function () {

        var todoText = $("#input").val();
        $("#input").val("");
        $("ul").append("<li style='list-style: none'><span><i></i></span><a class='text' >" + todoText + "</a></li>");
        listA.push(todoText);
        console.log(listA)


    });

    $("#submitbutton").click(function () {
        var code = document.getElementById("codeHandler").value;
        console.log(code);

        $.ajax({
            type : "POST",
            url : "/holyFuck",
            data : {
                myArray: listA,
                product_code: code
            },
            success : function(response) {
                console.log(response);
                    window.location.href = response;

            },
            error: function(xhr, response) {
                console.log(response);
                alert('Error!  Status = ' + xhr.status);
            }

        });
        }
    );


});
