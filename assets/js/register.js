$(function () {
    Webcam.attach('#camera');


    //        alert("Button clicked")
    //        Webcam.snap(function (data_uri) {
    //            // snap complete, image data is in 'data_uri'
    //
    //            Webcam.upload(data_uri, '', function (code, text) {
    //                // Upload complete!
    //                // 'code' will be the HTTP response code from the server, e.g. 200
    //                // 'text' will be the raw response content
    //                console.log("Hello World");
    //                alert("Hello World");
    //            });
    //
    //        });

    //    
    //    $.ajax()



    $("#capture-button").click(function () {
        alert("Clicked");
        Webcam.snap(function (data_uri) {
            var data_test = {
                webcam: data_uri,
            };
            alert("Image Captured");
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "http://localhost/hkt/api/v1/recognize-user.php",
                data: {webcam: data_uri},
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {

                    $("#result").text(data);
                    console.log("SUCCESS : ", data);
                    $("#btnSubmit").prop("disabled", false);

                },
                error: function (e) {

                    $("#result").text(e.responseText);
                    console.log("ERROR : ", e);
                    $("#btnSubmit").prop("disabled", false);

                }
            });
        });
    });
});
