<!DOCTYPE html>
<html lang="en">

<?php
    require_once("includes/head.php")
?>
<link rel="stylesheet" href="../assets/css/register.css">

<body class="register-page sidebar-collapse">

    <?php
        require_once("includes/header.php");
    ?>

    <div class="page-header">
        <div class="container register-form">
            <div class="row">
                <div class="col-md-offset col-md-10">
                    <form action="" method="POST" role="form">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="first-name">First Name : </label>
                                    <input type="text" class="form-control" name="first-name" id="first-name" placeholder="Enter Your First Name">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="last-name">Last Name : </label>
                                    <input type="text" class="form-control" name="last-name" id="last-name" placeholder="Enter Your Last Name">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="address-1">Address 1 : </label>
                                    <input type="text" class="form-control" name="address-1" id="address-1" placeholder="Enter the first line of your address">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="address-2">Address 2 : </label>
                                    <input type="text" class="form-control" name="address-2" id="address-2" placeholder="Enter the second line of your address">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="age"> Age : </label>
                                    <input type="text" class="form-control" name="age" id="age" placeholder="Enter Your Age">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="last-name">Gender : </label>
                                    <input type="text" class="form-control" name="last-name" id="last-name" placeholder="Enter Your Gender">
                                </div>
                            </div>
                        </div>

                        <div class="row file">
                            <div class="col-md-4">
                                <label for="aadhar-file"> Upload a copy of your Pan Card : </label>
                                <label for="aadhar-file" class="btn btn-primary choose-file col-md-6">Upload File</label>
                                <input type="file" class="form-control-file" id="aadhar-file">
                            </div>
                            <div class="col-md-4">
                                <label for="aadhar-file"> Upload a copy of your Aadhar Card : </label>
                                <label for="aadhar-file" class="btn btn-primary choose-file">Upload File</label>
                                <input type="file" class="form-control-file" id="aadhar-file">
                            </div>

                            <div class="col-md-4">
                                <label for="aadhar-file"> Upload a copy of your Drivers Lisence : </label>
                                <label for="aadhar-file" class="btn btn-primary choose-file">Upload File</label>
                                <input type="file" class="form-control-file" id="aadhar-file">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-default">Take A Selfie</button>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary col-md-12">Submit</button>
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
</body>

</html>
