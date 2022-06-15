$(function () {
	// memanggil function untuk ambil data menggunakan ajax
	getData();
	totalBerat();
	totalTelur();
	avgBeratTelur();
	telurBaik();
	telurBuruk();

	// set data table
	$("#myData").dataTable({
		autoWidth: false,
		columnDefs: [
			{
				searchable: false,
				orderable: false,
				targets: 0,
			},
		],
		order: [[1, "asc"]],
		ajax: {
			url: "Home/data_telur",
			dataSrc: "",
		},
		columns: [
			{ data: null },
			{ data: "Tanggal" },
			{ data: "Telur" },
			{ data: "Berat" },
			{ data: "Kondisi" },
		],
		fnRowCallback: function (nRow, aData, iDisplayIndex) {
			$("td:first", nRow).html(iDisplayIndex + 1);
			return nRow;
		},
	});

	// fungsi untuk mengambil data menggunakan ajax
	function getData() {
		$.ajax({
			type: "ajax",
			url: "Home/data_telur",
			async: true,
			dataType: "json",
			success: function (data) {
				var html = "";
				var count = 1;
				var i;
				// fetch data dengan looping
				// dan masukan element html pada variable html
				for (i = 0; i < data.length; i++) {
					html +=
						"<tr>" +
						"<td>" +
						count++ +
						"</td>" +
						"<td>" +
						data[i].Tanggal +
						"</td>" +
						"<td>" +
						data[i].Telur +
						"</td>" +
						"<td>" +
						data[i].Berat +
						"</td>" +
						"<td>" +
						data[i].Kondisi +
						"</td>" +
						"</tr>";
				}
				// kemudian di masukan ke dalam element id dengan nama dataTelur
				$("#dataTelur").html(html);
			},
		});
	}

	// fungsi mengambil data dengan json
	function totalBerat() {
		var form = $("#form").serialize();
		$.ajax({
			type: "ajax",
			url: "home/totalBerat",
			async: true,
			method: "GET",
			data: form,
			dataType: "json",
			success: function (data) {
				// check jika data 0 element dengan id totalBerat akan di isi 0
				if (data == null) {
					$("#totalBerat").html("0");
				} else {
					$("#totalBerat").html(data);
				}
			},
		});
	}

	// fungsi mengambil data dengan json

	function totalTelur() {
		var form = $("#form").serialize();
		$.ajax({
			type: "ajax",
			url: "home/totalTelur",
			async: true,
			method: "GET",
			data: form,
			dataType: "json",
			success: function (data) {
				// check jika data 0 element dengan id totalBerat akan di isi 0

				if (data == null) {
					$("#totalTelur").html("0");
				} else {
					$("#totalTelur").html(data);
				}
			},
		});
	}

	// fungsi mengambil data dengan json

	function avgBeratTelur() {
		var form = $("#form").serialize();
		$.ajax({
			type: "ajax",
			url: "home/avgBeratTelur",
			async: true,
			data: form,
			method: "GET",
			dataType: "json",
			success: function (data) {
				// check jika data 0 element dengan id totalBerat akan di isi 0

				if (data == null) {
					$("#avgBerat").html("0");
				} else {
					$("#avgBerat").html(data);
				}
			},
		});
	}
	// fungsi mengambil data dengan json

	function telurBaik() {
		var form = $("#form").serialize();
		$.ajax({
			type: "ajax",
			url: "home/telurBaik",
			async: true,
			data: form,
			method: "GET",
			dataType: "json",
			success: function (data) {
				// check jika data 0 element dengan id totalBerat akan di isi 0

				if (data == null) {
					$("#telurBaik").html("0");
				} else {
					$("#telurBaik").html(data);
				}
			},
		});
	}

	// fungsi mengambil data dengan json

	function telurBuruk() {
		var form = $("#form").serialize();
		$.ajax({
			type: "ajax",
			url: "home/telurBuruk",
			async: true,
			data: form,
			method: "GET",
			dataType: "json",
			success: function (data) {
				// check jika data 0 element dengan id totalBerat akan di isi 0

				if (data == null) {
					$("#telurBuruk").html("0");
				} else {
					$("#telurBuruk").html(data);
				}
			},
		});
	}

	// jika tombol button dengan id sortir di klik
	$("#sortir").click(function () {
		// dan jika value pada input text  == "" maka akan memaggil TOAST notification
		var date = $("#tanggal").val();
		if (date == "" || date == null) {
			toastr
				.error("Isi Tanggal Sortir")
				.css({ width: "300px", "max-width": "1000px", "font-size": "20px" });
		} else {
			// jika tidak akan memanggil fungsi sortir data
			sortirData();
			totalBerat();
			totalTelur();
			telurBaik();
			telurBuruk();
			avgBeratTelur();
		}
	});

	// jika tombol button dengan id testInput diklik
	$("#testInput").click(function () {
		var form = $("#testInputForm").serialize();
		// ajax akan mengambil value pada form input dengan id testInputForm
		// dan mengirimny ke server
		$.ajax({
			type: "ajax",
			url: "home/postTest",
			method: "GET",
			async: true,
			data: form,
			success: function (data) {
				$("#berat").val("");
				$("kondisi").val("");
				totalBerat();
				totalTelur();
				telurBaik();
				telurBuruk();

				avgBeratTelur();
				getData();
				/// menampilkan notifikasi TOAST

				toastr
					.success("Data Berhasil Di Input")
					.css({ width: "300px", "max-width": "1000px", "font-size": "20px" });
			},
			error: function (data) {
				toastr
					.success("Data Gagal Di Input")
					.css({ width: "300px", "max-width": "1000px", "font-size": "20px" });
			},
		});
	});

	// fungsi untuk sortir data
	function sortirData() {
		var form = $("#form").serialize();
		// ajax akan mengambil value pada form input dengan id testInputForm
		// dan mengirimny ke server
		$.ajax({
			type: "ajax",
			url: "home/sort",
			method: "GET",
			async: true,
			data: form,
			dataType: "json",
			success: function (data) {
				var html = "";
				var count = 1;
				var i;
				for (i = 0; i < data.length; i++) {
					html +=
						"<tr>" +
						"<td>" +
						count++ +
						"</td>" +
						"<td>" +
						data[i].Tanggal +
						"</td>" +
						"<td>" +
						data[i].Telur +
						"</td>" +
						"<td>" +
						data[i].Berat +
						"</td>" +
						"<td>" +
						data[i].Kondisi +
						"</td>" +
						"</tr>";
				}
				$("#tanggal").val("");
				$("#dataTelur").html(html);
			},
		});
	}

	// fungsi print
	$(".print").click(function () {
		window.print();
	});
});
