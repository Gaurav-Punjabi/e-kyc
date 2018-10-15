//$(document).ready(function () {
//
//    $("#submit-button").click(function (event) {
//
//        alert("Submit Clicked");
//        
//        //stop submit the form, we will post it manually.
//        event.preventDefault();
//        
//        // Get form
//        var form = $('#myForm');
//
//		// Create an FormData object 
//        var data = new FormData(form);
//
//		// disabled the submit button
//        $("#submit-button").prop("disabled", true);
//
//        $.ajax({
//            type: "POST",
//            enctype: 'multipart/form-data',
//            url: "http://192.168.43.52:8082/api/v1/userdata/create/",
//            data: form.serialize(),
//            processData: false,
//            contentType: false,
//            cache: false,
//            timeout: 600000,
//            success: function (data) {
//
//                $("#result").text(data);
//                console.log("SUCCESS : ", data);
//                $("#submit-button").prop("disabled", false);
//                alert("Sucess");
//
//            },
//            error: function (e) {
//
//                $("#result").text(e.responseText);
//                console.log("ERROR : ", e);
//                $("#submit-button").prop("disabled", false);
//
//            }
//        });
//
//    });
//
//});
////
////
//$("form#myForm").submit(function(e) {
//    e.preventDefault();
//    var formData = new FormData(this);    
//
//    $.post($(this).attr("action"), formData, function(data) {
//        alert(data);
//    });
//});
//
//
////
//$("#submit-button").on("click", function(e) {
//
//    var form = $("#myForm");
//
//    // you can't pass Jquery form it has to be javascript form object
//    var formData = new FormData(form[0]);
//
//    //if you only need to upload files then 
//    //Grab the File upload control and append each file manually to FormData
//    //var files = form.find("#fileupload")[0].files;
//
//    //$.each(files, function() {
//    //  var file = $(this);
//    //  formData.append(file[0].name, file[0]);
//    //});
//    var request = XMLHttpRequest();
//    request.open("POST","http://192.168.43.52:8082/api/v1/userdata/create/");
//    request.send(formData);
//    if ($(form).valid()) {
//        $.ajax({
//            type: "POST",
//            url: $(form).prop("action"),
//            //dataType: 'json', //not sure but works for me without this
//            data: formData,
//            contentType: false, //this is requireded please see answers above
//            processData: false, //this is requireded please see answers above
//            //cache: false, //not sure but works for me without this
//            error   : ErrorHandler,
//            success : successHandler
//        });
//    }
//});