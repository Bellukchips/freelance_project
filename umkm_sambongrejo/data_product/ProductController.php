<?php

require_once "../connect.php";

class ProductController
{
    public function getProduct()
    {
        global $conn;
        $query = "SELECT * FROM product";
        $data = array();
        $result = $conn->query($query);
        while ($row = mysqli_fetch_object($result)) {
            $data[] = $row;
        }
        $response = array(
            'status' => 1,
            'message' => 'Success get all product',
            'products' => $data
        );
        header('Content-Type: application/json');
        echo json_encode($response);
    }

    public function saveProduct()
    {
        global $conn;
        $name = $_POST['name'];
        $description = $_POST['description'];
        $price = $_POST['price'];

        $part = "./upload/";
        $filename = "img" . rand(9, 9999) . ".jpg";

        if ($_FILES['photo']) {
            $destinationfile = $part . $filename;
            if (move_uploaded_file($_FILES['photo']['tmp_name'], $destinationfile)) {
                $query = "INSERT INTO `product`(`id`, `name`, `description`, `price`,`photo`) VALUES (NULL,'$name','$description','$price','$filename');";
                $result = mysqli_query($conn, $query) or die(mysqli_error($conn));
                //check
                if ($result) {
                    $response = array(
                        'status' => 1,
                        'message' => 'Success create data'
                    );
                } else {
                    $response = array(
                        'status' => 0,
                        'message' => 'Failed create data'
                    );
                }
                echo json_encode($response);
            } else {
                $response = array(
                    'status' => 0,
                    'message' => 'Failed upload'
                );
                echo json_encode($response);
            }
        } else {
            $response = array(
                'status' => 0,
                'message' => 'error request'
            );
            echo json_encode($response);
        }
        header("Content-Type: application/json");
    }

    public function getProductById($id = 0)
    {
        global $conn;

        $query = "SELECT * FROM product WHERE id='$id' LIMIT 1";
        $data = array();
        $result = $conn->query($query);

        while ($row = mysqli_fetch_object($result)) {
            $data[] = $row;
        }

        $response = array(
            'status' => 1,
            'message' => 'Get product successfuly',
            'data' => $data
        );

        header('Content-type: application/json');
        echo json_encode($response);
    }

    public function updateProduct()
    {
        global $conn;
        $id = $_POST['id'];
        $name = $_POST['name'];
        $description = $_POST['description'];
        $price = $_POST['price'];

        $part = "./upload/";
        $filename = "product_" . rand(9, 9999) . ".jpg";
        if (isset($_FILES['photo'])) {
            $destinationfile = $part . $filename;
            if (move_uploaded_file($_FILES['photo']['tmp_name'], $destinationfile)) {
                $query = "UPDATE product SET name='$name',description='$description',price='$price',photo='$filename' where id='$id'";
                $result = mysqli_query($conn, $query) or die(mysqli_error($conn));
                //check
                if ($result) {
                    $response = array(
                        'status' => 1,
                        'message' => 'Success update data'
                    );
                } else {
                    $response = array(
                        'status' => 0,
                        'message' => 'Failed update data'
                    );
                }
                echo json_encode($response);
            }
        } else {
            $query = "UPDATE product SET name='$name',description='$description',price='$price' where id='$id'";
            $result = mysqli_query($conn, $query) or die(mysqli_error($conn));
            //check
            if ($result) {
                $response = array(
                    'status' => 1,
                    'message' => 'Success update data'
                );
            } else {
                $response = array(
                    'status' => 0,
                    'message' => 'Failed update data'
                );
            }
            echo json_encode($response);
        }


        header("Content-Type: application/json");
    }
    public function destroyProduct($id)
    {
        global $conn;
        $query = "DELETE FROM product WHERE id='$id'";
        if (mysqli_query($conn, $query)) {
            $response = array(
                'status' => 1,
                'message' => 'Deleted data successfully'
            );
        } else {
            $response = array(
                'status' => 0,
                'message' => 'Deleted data failed'
            );
        }
        header('Content-type: application/json');
        echo json_encode($response);
    }
}
