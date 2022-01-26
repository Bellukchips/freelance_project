<?php

require_once "UserController.php";
//
$users = new UserController();
$method = $_SERVER["REQUEST_METHOD"];
switch ($method) {
    case 'POST':
        $users->login();
        break;
    default:
        // Invalid Request Method
        header("HTTP/1.0 405 Method Not Allowed");
        break;
}
