<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <center>
        <h1>Laporan Periode Tahun <?php echo $_GET['year'] ?></h1>
    </center>
    <table border="1" width="100%">
        <thead>
            <tr>
                <td>No</td>
                <td>ID Transaksi</td>
                <td>ID Konsumen</td>
                <td>Nama Konsumen</td>
                <td>Nama Produk</td>
                <td>Harga Produk</td>
                <td>Tanggal Beli</td>
                <td>Quantity</td>
                <td>Ongkir</td>
                <td>Total</td>
                <td>Tujuan</td>
            </tr>
        </thead>
        <tbody>
            <?php
            require_once '../connect.php';
            $year = $_GET['year'];
            $query = "SELECT jual.id as id_transaksi, jual.id_konsumen as id_konsumen, konsumen.name as name_konsumen, product.name as name_product,product.price as price_product, jual.tgl_beli as tanggal_beli,jual.bukti_pembayaran as bukti_pembayaran, jual.qty as quantity, jual.ongkir as ongkir, jual.total as total, jual.tujuan as tujuan, year(jual.tgl_beli) as tahun_laporan FROM jual inner join product on product.id=jual.id_product inner join konsumen on konsumen.id=jual.id_konsumen where year(jual.tgl_beli)='$year'";
            $result = mysqli_query($conn, $query);
            $no = 1;
            $cek = mysqli_query($conn, $query) or die(mysqli_error($conn));
            $data = mysqli_fetch_assoc($cek);
            if ($data == !'') {
            ?>
                <tr>
                    <td><?php echo $no++ ?></td>
                    <td><?php echo $data['id_transaksi'] ?></td>
                    <td><?php echo $data['id_konsumen'] ?></td>
                    <td><?php echo $data['name_konsumen'] ?></td>
                    <td><?php echo $data['name_product'] ?></td>
                    <td><?php echo $data['price_product'] ?></td>
                    <td><?php echo $data['tanggal_beli'] ?></td>
                    <td><?php echo $data['quantity'] ?></td>
                    <td><?php echo $data['ongkir'] ?></td>
                    <td><?php echo $data['total'] ?></td>
                    <td><?php echo $data['tujuan'] ?></td>
                </tr>
            <?php } else { ?>
                <tr>
                    <td colspan="11" align="center">Tidak ada data</td>
                </tr>
            <?php } ?>
        </tbody>
    </table>
    <script>
        window.print();
    </script>
</body>

</html>