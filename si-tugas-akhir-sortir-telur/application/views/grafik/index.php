

<div class="container mt-4">
    <div class="dateTime">
        <div class="date">
            <span id="dayname">Day</span>,
            <span id="daynum">00</span>
            <span id="month">Month</span>
            <span id="year">Year</span>
        </div>
        <div class="time">
            <span id="hour">00</span>:
            <span id="minutes">00</span>:
            <span id="seconds">00</span>
            <span id="period">AM</span>
        </div>
    </div>

    <div class="container-fluid noprint">
        <form action="<?= base_url() ?>grafik/sortRange" method="post" class="form-inline">
            <div class="form-group">
                <label>Tanggal Awal </label>
                <input type="date" name="tgl_awal" id="basic-datepicker" class="form-control">
            </div>
            <div class="form-group mx-4">
                <label>Tanggal Akhir</label>
                <input type="date" name="tgl_akhir" id="basic-datepicker" class="form-control">
            </div>
            <div class="mt-4">
                <button type="submit" class="btn btn-primary ">Sortir</button>
                <button type="button" class="btn btn-danger  print">Print</button>
                <a class="btn btn-primary " href="<?= base_url('home') ?>">Home</a>
            </div>
        </form>

    </div>



    <center>
        <h2><b> DATA TELUR</h2></b></br>
    </center>

    <table class="table table-hover table-bordered text-center">
        <thead class="thead-light">
            <tr>
                <th style="text-align: center">No</th>
                <th scope="col" style="text-align: center">Tanggal</th>
                <th scope="col" style="text-align: center">Telur ke-</th>
                <th scope="col" style="text-align: center">Berat(gr)</th>
                <th scope="col" class="align-middle" style="text-align: center">Kondisi</th>
            </tr>
        </thead>

        <tbody id="dataTelur">
            <?php foreach ($telur as $data => $value) {
            ?>
                <tr>
                    <td><?php echo $data + 1 ?></td>
                    <td><?php echo $value->Tanggal ?></td>
                    <td><?php echo $value->Telur ?></td>
                    <td><?php echo $value->Berat ?></td>
                    <td><?php echo $value->Kondisi ?></td>
                </tr>
            <?php   # code...
            } ?>
        </tbody>
    </table></br>
    <div id="piechart" style="width: 1000px; height: 1000px;">

    </div>

</div>
<style>
    @media print {
        .noprint {
            visibility: hidden;
        }
    }
</style>
<script>
    // setting jam
    // mengambil jam dan tanggal sekarang
    function updateClock() {
        var now = new Date();
        var dname = now.getDay(),
            mo = now.getMonth(),
            dnum = now.getDate(),
            yr = now.getFullYear(),
            hour = now.getHours(),
            min = now.getMinutes(),
            sec = now.getSeconds()
        if (hour > 11) {
            pe = "PM"
        } else {
            pe = "AM"
        }

        // membuat array bulan dan hari
        var months = ["Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"];
        var week = ["Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"];

        // array nama element div
        var ids = ["dayname", "month", "daynum", "year", "hour", "minutes", "seconds", "period"];
        var values = [week[dname], months[mo], dnum, yr, hour, min, sec, pe];

        // semua element akan di isi sesuai index 
        for (var i = 0; i < ids.length; i++) {
            document.getElementById(ids[i]).firstChild.nodeValue = values[i]
        }

    }

    /// init fungsi clock

    function initClock() {
        updateClock()
        window.setInterval("updateClock()", 1);
    }


    /// membuat chart PIE menggunakan library google charts
    google.charts.load('current', {
        'packages': ['corechart']
    });
    google.charts.setOnLoadCallback(drawChart);

    // gambar chart dengan element kondisi dan total
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Kondisi', 'Total'],
            <?php
            foreach ($grafikTelur as $telur) {
                echo "['" . $telur['Kondisi'] . "'," . $telur['total'] . "],";
            }
            ?>
        ]);
        // mengaktifkan UI 3D
        var options = {
            title: 'Grafik Telur Berdasarkan Kondisi',
            is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
    }
</script>