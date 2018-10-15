<?php
    require_once("constants.php");
    require_once("kairos.php");
    
    $target_dir = "uploads/";
    $target_file = $target_dir . basename($_FILES["webcam"]["name"]);
    $uploadOk = 1;
    $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
    // Check if image file is a actual image or fake image
    if(isset($_FILES["webcam"]["tmp_name"])) {
        $check = getimagesize($_FILES["webcam"]["tmp_name"]);
        if($check !== false) {
            $uploadOk = 1;
        } else {
            $uploadOk = 0;
        }
    }
    // Check if file already exists
    if (!file_exists($target_file)) {
        // Check file size
        if ($_FILES["webcam"]["size"] > 500000) {
            $uploadOk = 0;
        }
        // Allow certain file formats
        if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
        && $imageFileType != "gif" ) {
            $uploadOk = 0;
        }
        move_uploaded_file($_FILES["webcam"]["tmp_name"], $target_file);
    } 
    else {
        move_uploaded_file($_FILES["webcam"]["tmp_name"], $target_file);
    }
    
    $kairos = new Kairos("463f4986", "f1c20907256dddc23fe11fd40697cb7b");
    $type = pathinfo($target_file, PATHINFO_EXTENSION);
    $data = file_get_contents($target_file);
    $base64 = 'data:image/' . $type . ';base64,' . base64_encode($data);
    $argumentArray =  array(
        "image" => $base64,
        "gallery_name" => GALLERY_NAME
    );
    $response   = $kairos->recognize($argumentArray);
    echo $response;
    
?>
