<?php

class Grafik extends CI_Controller
{
    function __construct()
    {
        parent::__construct();
        $this->load->model('m_telur');
    }
    public function index()
    {
        /**
         * 
         * ambil semua data berdasarkan tanggal sekarang
         */
        
        date_default_timezone_set('Asia/Jakarta');
        $currentDate = date("Y-m-d");
        $data = array(
            'telur' => $this->m_telur->SemuaData(),
            'grafikTelur' => $this->m_telur->GrafikTelur($currentDate, $currentDate)
        );
        $data['judul'] = 'Halaman Grafik';
        $this->load->view('templates/header', $data);
        $this->load->view('grafik/index', $data);
        $this->load->view('templates/footer');
    }

    function sortRange()
    {
         /**
         * ambil semua data berdasarkan tanggal yang di pilih pada form
         * 
         */
        $awal = $this->input->post("tgl_awal");
        $akhir = $this->input->post("tgl_akhir");
        $data = array(
            'telur' => $this->m_telur->DataRangeDate($awal, $akhir),
            'grafikTelur' => $this->m_telur->GrafikTelur($awal, $akhir)
        );
        $data['judul'] = 'Halaman Grafik';
        $this->load->view('templates/header', $data);
        $this->load->view('grafik/index', $data);
        $this->load->view('templates/footer');
    }
}
