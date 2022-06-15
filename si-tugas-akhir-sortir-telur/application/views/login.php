<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<?php echo base_url('assets/css/'); ?>login.css">

    <title>LOGIN</title>
</head>
<style>

</style>

<body class="text-center">


    <div class="wrapper fadeInDown">
        <?php if ($this->session->flashdata('message_login_error')) : ?>
            <div class="invalid-feedback">
                <?= $this->session->flashdata('message_login_error') ?>
            </div>
        <?php endif ?>
        <div id="formContent">
            <!-- Tabs Titles -->

            <!-- Icon -->
            <div class="fadeIn first p-4">
                <h3>Aplikasi Sortir Telur</h3>
            </div>

            <!-- Login Form -->
            <form method="POST" action="<?php echo base_url() ?>auth/login">
                <input type="text" id="login" class="fadeIn second <?= form_error('username') ? 'invalid' : '' ?>" name="username" placeholder="username" value="<?= set_value('username') ?>" required>
                <div class="invalid-feedback">
                    <?= form_error('username') ?>
                </div>
                <input type="password" id="password" class="fadeIn third <?= form_error('username') ? 'invalid' : '' ?>" name="password" placeholder="password" value="<?= set_value('password') ?>" required>
                <div class="invalid-feedback">
                    <?= form_error('password') ?>
                </div>
                <input type="submit" class="fadeIn fourth" value="Log In">
            </form>
        </div>
    </div>


</body>

</html>