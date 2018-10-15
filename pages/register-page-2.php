<?php header('Access-Control-Allow-Origin: *'); ?>

<!DOCTYPE html>
<html lang="en">

<?php
    require_once("includes/head.php")
?>

<link rel="stylesheet" href="../assets/css/register.css">

<style type="text/css">
    body {
        font-family: Helvetica, sans-serif;
    }

    h2,
    h3 {
        margin-top: 0;
    }

    form {
        margin-top: 15px;
    }

    form>input {
        margin-right: 15px;
    }

    #results {
        float: right;
        margin: 20px;
        padding: 20px;
        border: 1px solid;
        background: #ccc;
    }

</style>

<body class="registration-page sidebar-collapse">

    <?php
        require_once("includes/header.php");
    ?>

    <div class="page-header">
        <div class="container register-form">

            <div id="my_camera" style="margin-left: 250px"></div>

            <!-- First, include the Webcam.js JavaScript Library -->
            <script type="text/javascript" src="webcam.js"></script>

            <!-- Configure a few settings and attach camera -->
            <script language="JavaScript">
                Webcam.set({
                    width: 600,
                    height: 460,
                    image_format: 'jpeg',
                    jpeg_quality: 90
                });
                Webcam.attach( '#my_camera' );
            </script>

            <!-- A button for taking snaps -->
            <form>
                <input type=button style="margin-left: 500px;" value="Take a selfie" class="btn btn-primary" onClick="take_snapshot()">
            </form>

            <form  onsubmit=''>
                <input type='text' name='otp' required = true placeholder = 'OTP' id = "otp-field">
                <input type ='hidden' name = 'user-id' value = '' id = "user-field">
                <input type = 'hidden' name = 'sessionn-id' value = '' id = "session-field">
                <input type = 'button'  onclick='sendOtp()' name = 'submit'>
            </form>
            <script>
               function sendOtp(){
                var xhttp = new XMLHttpRequest();
                var otp = document.getElementById("otp-field").value;
                var userId = document.getElementById("user-field").value;
                var  sessionId =  document.getElementById("session-field").value;
//                   var userId = 29;
//                   var  sessionId =  1;
                alert ("otp = "+otp+ " userId="+userId+" sessionnid = "+sessionId);
                xhttp.open("GET","http://192.168.43.52:8082/api/v1/userdata/verify-otp/"+otp+"/"+sessionId+"/"+userId+"/");
                xhttp.onreadystatechange = function (){
                    if(this.readyState == 4 && this.status == 200){
                        var content = this.responseText;
                        var ob = JSON.parse(content);
                        var url = "http://localhost/hkt/Front-End/pages/profile-page.php?name="+ob.firstName+" "+ob.lastName+"&address1="+ob.address1+"&address2="+ob.address2+"&aadhar="+ob.aadharURL+"&license="+ob.licenseURL+"&pan="+ob.panURL;
                        console.log("url = "+url);
                        window.location.href=(url);
                    }
                };
                xhttp.send();   
               }
            </script>
           <!--   Core JS Files   -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
            <script src="../assets/js/core/popper.min.js" type="text/javascript"></script>
            <script src="../assets/js/core/bootstrap-material-design.min.js" type="text/javascript"></script>
            <script src="../assets/js/plugins/moment.min.js"></script>
            <!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
            <script src="../assets/js/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
            <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
            <script src="../assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
            <!--	Plugin for Sharrre btn -->
            <script src="../assets/js/plugins/jquery.sharrre.js" type="text/javascript"></script>
            <!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
            <script src="../assets/js/material-kit.js?v=2.0.4" type="text/javascript"></script>

            <script src="../assets/js/register-response.js"></script>
           
            <!-- Code to handle taking the snapshot and displaying it locally -->
            <script language="JavaScript">
                function take_snapshot() {
                    var subject_id = 29;
                                    alert("the subject id is " + subject_id);
                                    $.ajax({
                                        method: "GET",
                                        url: "http://192.168.43.52:8082/api/v1/userdata/send-otp/" + subject_id,
                                        cache: false,
                                        processing: false,
                                        success: function(data) {
                                            console.log("data = "+data);
                                            alert(data);
                                            document.getElementById('user-field').value = subject_id;
                                            document.getElementById('session-field').value = data;
                                            
                                        }
                                    });
                    return;
                    // take snapshot and get image data
                    /*Webcam.snap(function(data_uri) {
                        // display results in page


                        Webcam.upload(data_uri, 'http://localhost/hkt/api/v1/recognize-user.php', function(code, text) {
                            console.log(text);

                            var obj = JSON.parse(text);

                            for (i = 0; i < obj.images.length; i++) {
                                if (obj.images[i].transaction.status == 'success') {
                                    var subject_id = obj.images[i].transaction.subject_id;
                                    alert("the subject id is " + subject_id);
                                    $.ajax({
                                        method: "GET",
                                        url: "http://192.168.43.52:8082/api/v1/userdata/send-otp/" + subject_id,
                                        cache: false,
                                        processing: false,
                                        success: function(data) {
                                            console.log("data = "+data);
                                            alert(data);
                                        }
                                    });
                                }
                            }
                        });
                    });*/
                }

            </script>
            
        </div>
    </div>
</body>

</html>
