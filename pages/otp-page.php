<?php header('Access-Control-Allow-Origin: *'); ?>    



<!DOCTYPE html>
<html lang="en">

<?php
    require_once("includes/head.php");
?>
<link rel="stylesheet" href="../assets/css/register.css">

<body class="registration-page sidebar-collapse" style="background-color: #fff;">

   <?php
        if(isset($_GET('subject_id')))
            echo "got it";
    ?>
   
    <?php
        require_once("includes/header.php");
        echo $subject_id;
    ?>
    <div class="page-header">
        <div class="container register-form">
            <img src="scanned.gif" alt="" style="width: 600px; margin-left:250px;">
            <form action="http://192.168.43.52/api/v1/userdata/add-image/" method="POST">
                
            </form>
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
