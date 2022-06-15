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
  <div class="container-fluid">
    <label>Tanggal</label>
    <form class="form" id="form">
      <div class="row">
        <div class="col-sm">
          <input type="date" name="tanggal" id="tanggal" class="form-control flatpicker-input active" required>
        </div>
        <div clas="col-sm">
          <button type="button" class="btn btn-primary" id="sortir">Sortir</button>
        </div>
      </div>
    </form>
  </div>



  <center>
    <h2><b> DATA TELUR</h2></b></br>
  </center>

  <table class="table table-hover table-bordered text-center" id="myData">
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

    </tbody>
  </table></br>
  <div class="row mb-5">
    <div class="col-sm-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title" id="totalBerat"></h2>
          <p class="card-text">Total Berat Telur</p>
        </div>
      </div>
    </div>
    <div class="col-sm-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title" id="totalTelur"></h2>
          <p class="card-text">Total Telur</p>
        </div>
      </div>
    </div>
    <div class="col-sm-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title" id="avgBerat"></h2>
          <p class="card-text">Rata-Rata Berat Telur</p>
        </div>
      </div>
    </div>
    <div class="col-sm-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title" id="telurBaik"></h2>
          <p class="card-text">Total Telur Kondisi Baik</p>
        </div>
      </div>
    </div>
    <div class="col-sm-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title" id="telurBuruk"></h2>
          <p class="card-text">Total Telur Kondisi Buruk</p>
        </div>
      </div>
    </div>
    <div class="col-sm-6">
      <div class="mt-5">
        <a class="btn btn-primary" href="<?= base_url('laporan') ?>" role="laporan">Laporan</a>
        <a class="btn btn-primary" href="<?= base_url('grafik') ?>" role="grafik">Grafik</a>
        <form id="testInputForm" class="mt-5">
          <input type="text" name="berat" id="berat" class="form-control" placeholder="Berat" required>
          <input type="text" name="kondisi" id="kondisi" class="form-control" placeholder="Kondisi" required>
          <button type="button" class="btn btn-primary mt-2" id="testInput">Test Input</button>
        </form>
      </div>
    </div>
  </div>
</div>

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
</script>