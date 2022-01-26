<?php

require_once "../connect.php";

class TransaksiController
{
    public function saveTransaction()
    {
        global $conn;
        $id_product = $_POST['id_product'];
        $id_konsumen = $_POST['id_konsumen'];
        $tgl_beli = date("Y/m/d");
        $total = $_POST['total'];
        $ongkir = $_POST['ongkir'];
        $tujuan = $_POST['tujuan'];
        $qty = $_POST['qty'];
        $part = "./upload/";
        $filename = "bukti_pembayaran" . rand(9, 9999) . ".jpg";

        if ($_FILES['bukti_pembayaran']) {
            $destinationfile = $part . $filename;
            if (move_uploaded_file($_FILES['bukti_pembayaran']['tmp_name'], $destinationfile)) {
                $query = "INSERT INTO `jual`(`id`, `id_product`, `id_konsumen`, `tgl_beli`, `bukti_pembayaran`, `qty`, `total`, `ongkir`, `tujuan`) VALUES (NULL,'$id_product','$id_konsumen','$tgl_beli','$filename','$qty','$total','$ongkir','$tujuan')";
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

    public function getAllTransaction()
    {
        global $conn;
        $query = "SELECT jual.id as id_transaksi, jual.id_konsumen as id_konsumen, konsumen.name as name_konsumen, product.name as name_product,product.price as price_product, jual.tgl_beli as tanggal_beli,jual.bukti_pembayaran as bukti_pembayaran, jual.qty as quantity, jual.ongkir as ongkir, jual.total as total, jual.tujuan as tujuan, year(jual.tgl_beli) as tahun_laporan FROM jual inner join product on product.id=jual.id_product inner join konsumen on konsumen.id=jual.id_konsumen;";
        $data = array();
        $result = $conn->query($query);
        while ($row = mysqli_fetch_object($result)) {
            $data[] = $row;
        }
        $response = array(
            'status' => 1,
            'message' => 'Success get all transaction',
            'transaction' => $data
        );
        header('Content-Type: application/json');
        echo json_encode($response);
    }
    public function getTransactionByYear($year)
    {
        global $conn;
        $query = "SELECT jual.id as id_transaksi, jual.id_konsumen as id_konsumen, konsumen.name as name_konsumen, product.name as name_product, product.price as price_product, jual.tgl_beli as tanggal_beli,jual.bukti_pembayaran as bukti_pembayaran, jual.qty as quantity, jual.ongkir as ongkir, jual.total as total, jual.tujuan as tujuan, year(jual.tgl_beli) as tahun_laporan FROM jual inner join product on product.id=jual.id_product inner join konsumen on konsumen.id=jual.id_konsumen where year(jual.tgl_beli)='$year'";
        $data = array();
        $result = $conn->query($query);
        while ($row = mysqli_fetch_object($result)) {
            $data[] = $row;
        }
        $response = array(
            'status' => 1,
            'message' => 'Success get transaction',
            'transaction' => $data
        );
        header('Content-Type: application/json');

        echo json_encode($response);
    }
    public function getTransactionByIdUser($id)
    {
        global $conn;
        $query = "SELECT jual.id as id_transaksi, jual.id_konsumen as id_konsumen, konsumen.name as name_konsumen, product.name as name_product, product.price as price_product, jual.tgl_beli as tanggal_beli,jual.bukti_pembayaran as bukti_pembayaran, jual.qty as quantity, jual.ongkir as ongkir, jual.total as total, jual.tujuan as tujuan FROM jual inner join product on product.id=jual.id_product inner join konsumen on konsumen.id=jual.id_konsumen where jual.id_konsumen='$id'";
        $data = array();
        $result = $conn->query($query);
        while ($row = mysqli_fetch_object($result)) {
            $data[] = $row;
        }
        $response = array(
            'status' => 1,
            'message' => 'Success get transaction',
            'transaction' => $data
        );
        header('Content-Type: application/json');
        echo json_encode($response);
    }

}
