<?php

require_once "TransaksiController.php";
//
$transaksi = new TransaksiController();
$method = $_SERVER["REQUEST_METHOD"];
switch ($method) {
    case 'POST':
        if (!empty($_POST['year'])) {
            $year = $_POST['year'];
            $transaksi->getTransactionByYear($year);
        } else {
            $transaksi->saveTransaction();
        }
        break;
    case 'GET':
        if (!empty($_GET['id'])) {
            $id = intval($_GET['id']);
            $transaksi->getTransactionByIdUser($id);
            break;
        } else {
            $transaksi->getAllTransaction();
            break;
        }
    default:
        // Invalid Request Method
        header("HTTP/1.0 405 Method Not Allowed");
        break;
}
