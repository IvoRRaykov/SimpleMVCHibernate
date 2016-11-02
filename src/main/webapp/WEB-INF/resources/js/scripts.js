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
        console.log(ctx);
        $.ajax({
            type : "POST",
            url : ctx.concat("/product/doAttachSongs"),
            data : {
                myArray: listA,
                product_code: code
            },
            complete: function() {
                window.location.replace(ctx.concat("/product/manage?message"));
            },
            success : function(data) {
                if (data.redicrect) {
                    window.location.replace(ctx.concat("/product/manage?message"));
                }

            },
            error: function(xhr, response) {
                window.location.replace(ctx.concat("/product/manage?message"));
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


