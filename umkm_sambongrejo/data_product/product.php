<?php

require_once "ProductController.php";
//
$product = new ProductController();
$method = $_SERVER["REQUEST_METHOD"];
switch ($method) {
    case 'GET':
        if (!empty($_GET['id'])) {
            $id = intval($_GET['id']);
            $product->getProductById($id);
        } else {
            $product->getProduct();
        }
        break;
    case 'POST':
        if (!empty($_POST['id'])) {
            $product->updateProduct();
        } else {
            $product->saveProduct();
        }
        break;
    case 'DELETE':
        $id = intval($_GET['id']);
        $product->destroyProduct($id);
        break;
    default:
        // Invalid Request Method
        header("HTTP/1.0 405 Method Not Allowed");
        break;
}
