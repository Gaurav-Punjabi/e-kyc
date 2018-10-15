<?php
    require_once("constants.php");
    require_once("kairos.php");
    
    echo "Hello World";

    if(isset($_POST["name"])) {
        $name = $_POST["name"];
        $target_dir = "uploads/";
        $target_file = $target_dir . basename($_FILES["webcam"]["name"]);
        $uploadOk = 1;
        $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));

        echo "Name has been caught";

        // Check if image file is a actual image or fake image
        if(isset($_FILES["webcam"]["tmp_name"])) {
            echo "Files have been caught";
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
            echo "Image does not exits";
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
            echo "Image exits";
            move_uploaded_file($_FILES["webcam"]["tmp_name"], $target_file);
        }

        echo $target_file;

        $kairos = new Kairos("463f4986", "f1c20907256dddc23fe11fd40697cb7b");
        $type = pathinfo($target_file, PATHINFO_EXTENSION);
        $data = file_get_contents($target_file);
        $base64 = 'data:image/' . $type . ';base64,' . base64_encode($data);
        $argumentArray =  array(
            "image" => $base64,
            "subject_id" => $name,
            "gallery_name" => GALLERY_NAME
        );
        $response   = $kairos->enroll($argumentArray);
        echo $response;
    }
    
?>
