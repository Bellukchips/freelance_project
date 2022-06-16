<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" type="text/css" href="<?php echo base_url() . 'assets/css/bootstrap.min.css' ?>">
  <link rel="stylesheet" href="<?php echo base_url('assets/css/'); ?>style.css">
  <link rel="stylesheet" type="text/css" href="<?php echo base_url() . 'assets/css/bootstrap.css' ?>">
  <link rel="stylesheet" type="text/css" href="<?php echo base_url() . 'assets/css/jquery.dataTables.css' ?>">
  <link rel="stylesheet" type="text/css" href="<?php echo base_url() . 'assets/css/toastr.min.css' ?>">
  <script src="<?php echo base_url() . 'assets/js/charts/loader.js' ?>"></script>
  <!-- <script src="https://www.gstatic.com/charts/loader.js"></script> -->

  <title><?php echo $judul; ?></title>
</head>

<body onload="initClock()">

  <!--<nav class="navbar navbar-light bg-primary">
    <img src="<?php echo base_url('assets'); ?>/images/telur.png" width="70" height="70" class="mr-3" alt="">
    <h4>SISTEM INFORMASI SORTIR TELUR AYAM (<?php echo $current_user->name; ?>)</h4>
    <a href="<?php echo base_url() ?>auth/logout" class="btn btn-outline-danger" style="color:white ;" type="submit">Logout</a>
  </nav>-->
	  <nav class="navba navbar-light bg-primary">
    <div class="d-flex">
      <div class="p-2">
        <img src="<?php echo base_url('assets'); ?>/images/telur.png" width="70" height="70" class="mr-3" alt="">

      </div>
      <div class="p-2 mt-4">
        <!-- <h4>SISTEM INFORMASI SORTIR TELUR AYAM (<?php echo $current_user->name; ?>)</h4> -->
        <h4>SISTEM INFORMASI SORTIR TELUR AYAM</h4>

      </div>
      <div class="ml-auto mt-4 p-2">

        <a href="<?php echo base_url() ?>auth/logout" class="btn btn-outline-danger" style="color:white ;" type="submit">Logout</a>

      </div>
    </div>

  </nav>
