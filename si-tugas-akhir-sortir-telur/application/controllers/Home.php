<?php

class Home extends CI_Controller
{

    function __construct()
    {
        parent::__construct();
        $this->load->model('m_telur');
        $this->load->model('auth_model');
    }
    public function index()
    {
        if (!$this->auth_model->current_user()) {
            $this->load->view('login');
        } else {
            /**
             *  menampilkan semua data berdasarkan tanggal sekarang
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
            $data['judul'] = 'Halaman Home';
            $data['current_user'] = $this->auth_model->current_user();
            $this->load->view('templates/header', $data);
            $this->load->view('home/index', $data);
            $this->load->view('templates/footer');
        }
    }

    function data_telur()
    {
        /**
         *  ambil semua data dan di parse ke bentuk json
         */
        $data = $this->m_telur->SemuaData();
        echo json_encode($data);
    }

    function totalBerat()
    {

        /**
         *  ambil data berdasarkan tanggal yang dipilih pada form
         * kemudian data tersebut di parse kedalam json
         */
        $tanggal = $this->input->get("tanggal");

        if (empty($tanggal)) {
            date_default_timezone_set('Asia/Jakarta');
            $currentDate = date("Y-m-d");
            $data = $this->m_telur->TotalBerat($currentDate);
        } else {
            $data = $this->m_telur->TotalBerat($tanggal);
        }
        echo json_encode($data);
    }
    function totalTelur()
    {
        /**
         *  ambil data berdasarkan tanggal yang dipilih pada form
         * kemudian data tersebut di parse kedalam json
         */
        $tanggal = $this->input->get("tanggal");
        if (empty($tanggal)) {
            date_default_timezone_set('Asia/Jakarta');
            $currentDate = date("Y-m-d");
            $data = $this->m_telur->TotalTelur($currentDate);
        } else {
            $data = $this->m_telur->TotalTelur($tanggal);
        }
        echo json_encode($data);
    }
    function avgBeratTelur()
    {
        /**
         *  ambil data berdasarkan tanggal yang dipilih pada form
         * kemudian data tersebut di parse kedalam json
         */
        $tanggal = $this->input->get("tanggal");
        if (empty($tanggal)) {
            date_default_timezone_set('Asia/Jakarta');
            $currentDate = date("Y-m-d");
            $data = $this->m_telur->AvgBeratTelur($currentDate);
        } else {
            $data = $this->m_telur->AvgBeratTelur($tanggal);
        }
        echo json_encode($data);
    }
    function telurBaik()
    {
        /**
         *  ambil data berdasarkan tanggal yang dipilih pada form
         * kemudian data tersebut di parse kedalam json
         */
        $tanggal = $this->input->get("tanggal");
        if (empty($tanggal)) {
            date_default_timezone_set('Asia/Jakarta');
            $currentDate = date("Y-m-d");
            $data = $this->m_telur->TelurBaik($currentDate);
        } else {
            $data = $this->m_telur->TelurBaik($tanggal);
        }
        echo json_encode($data);
    }
    function telurBuruk()
    {
        /**
         *  ambil data berdasarkan tanggal yang dipilih pada form
         * kemudian data tersebut di parse kedalam json
         */
        $tanggal = $this->input->get("tanggal");
        if (empty($tanggal)) {
            date_default_timezone_set('Asia/Jakarta');
            $currentDate = date("Y-m-d");
            $data = $this->m_telur->TelurBuruk($currentDate);
        } else {
            $data = $this->m_telur->TelurBuruk($tanggal);
        }
        echo json_encode($data);
    }

    public function sort()
    {
        /**
         *  ambil data berdasarkan tanggal yang dipilih pada form
         * kemudian data tersebut di parse kedalam json
         */
        $tanggal = $this->input->get("tanggal");
        $data = $this->m_telur->TanggalSortir($tanggal);
        echo json_encode($data);
    }


    function postTest()
    {
        /// testing input data
        $berat = $this->input->get('berat');
        $kondisi = $this->input->get('kondisi');
        if (empty($berat) && empty($kondisi)) {
            return null;
        } else {
            $this->m_telur->InsertTelur($berat, $kondisi);
        }
    }
}
