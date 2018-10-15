
<?php header('Access-Control-Allow-Origin: *'); ?>    

<!DOCTYPE html>
<html lang="en">

<?php
    require_once("includes/head.php")
?>
<link rel="stylesheet" href="../assets/css/register.css">

<body class="registration-page sidebar-collapse">

    <?php
        require_once("includes/header.php");
    ?>

    <div class="page-header">
        <div class="container register-form">
            <div class="row">
                <div class="col-md-offset col-md-10">
                    <div class="title" style="text-align: center; color: #333; margin-bottom: 50px">
                        <h2>User Registration : </h2>
                    </div>
                    <form action="http://192.168.43.52:8082/api/v1/userdata/create/" id="myForm" method="POST" role="form" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="first-name">First Name : </label>
                                    <input type="text" class="form-control" name="firstName" id="first-name" placeholder="Enter Your First Name">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="last-name">Last Name : </label>
                                    <input type="text" class="form-control" name="lastName" id="last-name" placeholder="Enter Your Last Name">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="address-1">Address 1 : </label>
                                    <input type="text" class="form-control" name="address1" id="address-1" placeholder="Enter the first line of your address">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="address-2">Address 2 : </label>
                                    <input type="text" class="form-control" name="address2" id="address-2" placeholder="Enter the second line of your address">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="phone"> Phone Number : </label>
                                    <input type="text" class="form-control" name="phone" id="phone" placeholder="Enter Your Phone No : ">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="gender">Gender : </label>
                                    <input type="text" class="form-control" name="gender" id="gender" placeholder="Enter Your Gender">
                                </div>
                            </div>
                        </div>

                        <div class="row file">
                            <div class="col-md-4">
                                <label for="pan-card"> Upload a copy of your Pan Card : </label>
                                <label for="pan-card" class="btn btn-primary choose-file col-md-6">Upload File</label>
                                <input type="file" class="form-control-file" id="pan-card" name="pan">
                            </div>
                            <div class="col-md-4">
                                <label for="aadhar-file"> Upload a copy of your Aadhar Card : </label>
                                <label for="aadhar-file" class="btn btn-primary choose-file">Upload File</label>
                                <input type="file" class="form-control-file" id="aadhar-file" name="aadhar">
                            </div>

                            <div class="col-md-4">
                                <label for="drivers-license"> Upload a copy of your Drivers Lisence : </label>
                                <label for="drivers-license" class="btn btn-primary choose-file">Upload File</label>
                                <input type="file" class="form-control-file" id="drivers-license" name="license">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" id="submit-button" class="btn btn-primary col-md-12">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

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
</body>

</html>
