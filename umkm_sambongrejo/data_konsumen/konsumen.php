<?php

require_once "KonsumenController.php";
//
$konsumen = new KonsumenController();
$method = $_SERVER["REQUEST_METHOD"];
switch ($method) {
    case 'GET':
        if (!empty($_GET['email'])) {
            $email = $_GET['email'];
            $konsumen->geKonsumenByEmail($email);
        } else {
            $konsumen->getAllKonsumen();
        }
        break;
    case 'POST':
        if (!empty($_GET['id'])) {
            $id = intval($_GET['id']);
            $konsumen->updateKonsumen($id);
        } else if(empty($_POST['name'])){
            $konsumen->login();
        }else {
            $konsumen->saveKonsumen();
        }
        break;
    case 'DELETE':
        $id = intval($_GET['id']);
        $konsumen->destroyKonsumen($id);
        break;
    default:
        // Invalid Request Method
        header("HTTP/1.0 405 Method Not Allowed");
        break;
}
