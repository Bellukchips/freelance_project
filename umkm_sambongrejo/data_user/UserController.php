<?php

require_once '../connect.php';

class UserController
{
 
    public function login()
    {
        global $conn;
        error_reporting(0);
        if (isset($_POST['email'])  && isset($_POST['password'])) {
            $username = $_POST['email'];
            $password = $_POST['password'];

            $query = "SELECT * FROM users WHERE email='$username'";
            $cek = mysqli_query($conn, $query) or die(mysqli_error($conn));
            $data = mysqli_fetch_assoc($cek);
            $userPass = $data['password'];
            if (password_verify($password, $userPass)) {
                $response = array(
                    'status' => 1,
                    'message' => 'Admin Login Successfuly'
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
