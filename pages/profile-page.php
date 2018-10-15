<!DOCTYPE html>
<html lang="en">

<?php
    require_once("includes/head.php")
?>

<?php
    $name = $_GET['name'];  
    $address1 = $_GET['address1'];
    $address2 = $_GET['address2'];
    $aadhar = $_GET['aadhar'];
    $pan = $_GET['pan'];
    $license = $_GET['license'];

?>
    
<link rel="stylesheet" href="../assets/css/profile-demo.css">
<link rel="stylesheet" href="../assets/css/profile.css">

<body class="profile-page sidebar-collapse">

    <?php
        require_once("includes/header.php")
    ?>

    <div class="page-header header-filter" data-parallax="true" style="background-image: url('../assets/img/city-profile.jpg');"></div>
    <div class="main main-raised">
        <div class="profile-content">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 ml-auto mr-auto">
                        <div class="profile">
                            <div class="avatar">
                                <img src="../assets/img/faces/christian.jpg" alt="Circle Image" class="img-raised rounded-circle img-fluid">
                            </div>
                            <div class="name">
                                <h3 class="title"><?php echo $name; ?></h3>
                                <h6>Designer</h6>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="addresses">
                    <div class="row">
                        <div class="col-md-12">
                            <label class="text-weight-bold address-label">Address 1 : </label>
                            <label class="text-weight-bold address"><?php echo $address1;?></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label class="text-weight-bold address-label">Address 2 : </label>
                            <label class="text-weight-bold address"><?php echo $address2; ?></label>
                        </div>
                    </div>
                </div>

                <div class="documents">
<!--
                       <?php
                               ?>
                        <div class="document">
                           <div class="row">
                            <div class="col-md-12">
                                <label for="c">
                                    Document</a>
                            </div>
                        </div>
                    </div>
                    <?php
                        
                    ?>
-->
                    
                    <div class="document">
                           <div class="row">
                            <div class="col-md-12">
                                <label for="c"> AAdhar </label>
                                <a href = '<?php echo $aadhar; ?>' target='_blank' class="btn btn-primary">View Document</a>
                            </div>
                        </div>
                    </div>
                    
                                        <div class="document">
                           <div class="row">
                            <div class="col-md-12">
                                <label for="c"> Pancard </label>
                                <a href = '<?php echo $pan; ?>' target='_blank' class="btn btn-primary">View Document</a>
                            </div>
                        </div>
                    </div>
                    
                                        <div class="document">
                           <div class="row">
                            <div class="col-md-12">
                                <label for="c"> License </label>
                                <a href = '<?php echo $license; ?>' target='_blank' class="btn btn-primary">View Document</a>
                            </div>
                        </div>
                    </div>
                
                <div class="clearfix"></div>
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
