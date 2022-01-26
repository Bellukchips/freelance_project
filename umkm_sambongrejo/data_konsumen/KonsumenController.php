<?php
require_once "../connect.php";

class KonsumenController
{
    public function getAllKonsumen()
    {
        global $conn;
        $query = "SELECT * FROM konsumen";
        $data = array();
        $result = $conn->query($query);
        while ($row = mysqli_fetch_object($result)) {
            $data[] = $row;
        }
        $response = array(
            'status' => 1,
            'message' => 'Success get all konsumen',
            'konsumen' => $data
        );
        header('Content-Type: application/json');
        echo json_encode($response);
    }
    public function geKonsumenByEmail($email)
    {
        global $conn;
        error_reporting(0);
        $query = "SELECT * FROM konsumen WHERE email='$email'";
        $cek = mysqli_query($conn, $query) or die(mysqli_error($conn));
        $data = mysqli_fetch_assoc($cek);
        $response = array(
            'status' => 1,
            'message' => 'Get konsumen success',
            'id_user' => $data['id']
        );

        header('Content-type: application/json');
        echo json_encode($response);
    }
    public function saveKonsumen()
    {
        global $conn;
        $name = $_POST['name'];
        $gender = $_POST['gender'];
        $email = $_POST['email'];
        $password = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $duplicate = mysqli_query($conn, "select * from konsumen where email='$email'");
        if (mysqli_num_rows($duplicate) > 0) {
            $response = array(
                'status' => 1,
                'message' => 'Email is registered'
            );
            header('Content-Type: application/json');
            echo json_encode($response);
        } else {
            if(isset($password) && isset($gender)){
                $query = "INSERT INTO `konsumen`(`id`, `name`, `email`, `password`, `gender`) VALUES (NULL,'$name','$email','$password','$gender');";
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
                header('Content-Type: application/json');
                echo json_encode($response);
            }else{
                $query = "INSERT INTO `konsumen`(`id`, `name`, `email`) VALUES (NULL,'$name','$email');";
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
                header('Content-Type: application/json');
                echo json_encode($response);
            }
        }
    }
    public function updateKonsumen($id)
    {
        global $conn;
        $name = $_POST['name'];
        $gender = $_POST['gender'];
        $address = $_POST['address'];
        $email = $_POST['email'];
        $password = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $query = "UPDATE konsumen SET name='$name',email='$email',password='$password',address='$address',gender='$gender' where id='$id'";
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
        header('Content-Type: application/json');
        echo json_encode($response);
    }
    public function destroyKonsumen($id)
    {
        global $conn;
        $query = "DELETE FROM konsumen WHERE id='$id'";
        $query1 = "DELETE FROM jual where id_konsumen='$id'";
        if (mysqli_query($conn, $query)) {
            mysqli_query($conn, $query1);
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

    public function login()
    {
        global $conn;
        error_reporting(0);
        if (isset($_POST['email'])  && isset($_POST['password'])) {
            $email = $_POST['email'];
            $password = $_POST['password'];

            $query = "SELECT * FROM konsumen WHERE email='$email'";
            $cek = mysqli_query($conn, $query) or die(mysqli_error($conn));
            $data = mysqli_fetch_assoc($cek);
            $userPass = $data['password'];
            if (password_verify($password, $userPass)) {
                $response = array(
                    'status' => 1,
                    'message' => 'Login Successfuly',
                    'name' => $data['name'],
                    'email' => $data['email'],
                    'address' => $data['address'],
                    'gender' => $data['gender'],
                    'id_user' => $data['id']
                );
            } else {
                $response = array(
                    'status' => 0,
                    'message' => 'Login Failed'
                );
            }
            header('Content-type: application/json');
            echo json_encode($response);
        }
    }
}
