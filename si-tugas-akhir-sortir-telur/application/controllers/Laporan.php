<?php

class Laporan extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('m_telur');
    }
    public function index()
    {

        /**
         *  ambbil data berdasarkan tanggal sekarang
         */
        date_default_timezone_set('Asia/Jakarta');
        $currentDate = date("Y-m-d");
        $data = array(
            'telur' => $this->m_telur->SemuaData(),
            "berat" => $this->m_telur->TotalBerat($currentDate),
            "total" => $this->m_telur->TotalTelur($currentDate),
            "avgBerat" => $this->m_telur->AvgBeratTelur($currentDate),
            'tBaik' => $this->m_telur->TelurBaik($currentDate),
            'tBuruk' => $this->m_telur->TelurBuruk($currentDate)
        );
        $data['judul'] = 'Halaman Laporan';
        $this->load->view('templates/header', $data);
        $this->load->view('laporan/index', $data);
        $this->load->view('templates/footer');
    }

    function sortRange()
    {
        /**
         *  ambil data berdasarkan tanggal yang dipilih pada form
         * 
         */
        $awal = $this->input->post("tgl_awal");
        $akhir = $this->input->post("tgl_akhir");
        $data = array(
            'telur' => $this->m_telur->DataRangeDate($awal, $akhir),
            "berat" => $this->m_telur->TotalBeratByRange($awal, $akhir),
            "total" => $this->m_telur->TotalTelurByRange($awal, $akhir),
            "avgBerat" => $this->m_telur->AvgBeratTelurByRange($awal, $akhir),
            'tBaik' => $this->m_telur->TelurBaikByRange($awal, $akhir),
            'tBuruk' => $this->m_telur->TelurBurukByRange($awal, $akhir)
        );
        $data['judul'] = 'Halaman Laporan';
        $this->load->view('templates/header', $data);
        $this->load->view('laporan/index', $data);
        $this->load->view('templates/footer');
    }
}
