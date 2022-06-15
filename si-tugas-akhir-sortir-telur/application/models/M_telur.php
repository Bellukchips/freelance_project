<?php

class M_telur extends CI_Model
{
    public function SemuaData()
    {
        // sort data by current date
        date_default_timezone_set('Asia/Jakarta');
        $currentDate = date("Y-m-d");
        return $this->db->where("Tanggal", $currentDate)->get('tbtelur')->result();
    }

    public function TanggalSortir($tanggal)
    {
         // sort data by current date
        return $this->db->where("Tanggal", $tanggal)->get('tbtelur')->result();
    }

    public function TotalBerat($tanggal = null)
    {
        // sort total berat by current date
        if ($tanggal == null) {

            $query = $this->db->query("select SUM(Berat) as total_telur from tbtelur")->row();
        } else {

            $query = $this->db->query("select SUM(Berat) as total_telur from tbtelur where Tanggal='$tanggal'")->row();
        }
        return $query->total_telur;
    }

    public function TelurBaik($tanggal = null)
    {
        // sort total berat by current date
        if ($tanggal == null) {
            $query = $this->db->query("select COUNT(Telur) as telur from tbtelur where Kondisi='Baik'")->row();
        } else {

            $query = $this->db->query("select COUNT(Telur) as telur from tbtelur where Tanggal='$tanggal' and Kondisi='Baik'")->row();
        }
        return $query->telur;
    }

    public function TelurBuruk($tanggal = null)
    {
        // sort total berat by current date
        if ($tanggal == null) {
            $query = $this->db->query("select COUNT(Telur) as telur from tbtelur where Kondisi='Buruk'")->row();
        } else {

            $query = $this->db->query("select COUNT(Telur) as telur from tbtelur where Tanggal='$tanggal' and Kondisi='Buruk'")->row();
        }
        return $query->telur;
    }

    public function TotalTelur($tanggal = null)
    {
        // sort total berat by current date
        if ($tanggal == null) {
            $query = $this->db->query("select COUNT(Telur) as telur from tbtelur")->row();
        } else {

            $query = $this->db->query("select COUNT(Telur) as telur from tbtelur where Tanggal='$tanggal'")->row();
        }
        return $query->telur;
    }
    public function AvgBeratTelur($tanggal = null)
    {
        // sort total berat by current date
        if ($tanggal == null) {

            $query = $this->db->query("select AVG(Berat) as rata_rata from tbtelur")->row();
        } else {
            $query = $this->db->query("select AVG(Berat) as rata_rata from tbtelur where Tanggal='$tanggal'")->row();
        }
        return $query->rata_rata;
    }

    public function InsertTelur($berat, $kondisi)
    {
        /**
         *  insert data test
         */
        date_default_timezone_set('Asia/Jakarta');
        $currentDate = date("Y-m-d");
        return $this->db->query("INSERT INTO tbtelur VALUES(null,'$berat','$kondisi','$currentDate')");
    }
    public function DataRangeDate($first, $end)
    {
         /**
         *  mengambil data berdasarkan range tanggal yang dipilih
         */
        $query = $this->db->query("select * from tbtelur where Tanggal between '$first' and '$end'");
        return $query->result();
    }
    public function TotalBeratByRange($first, $end)
    {
        /**
         *  mengambil data berdasarkan range tanggal yang dipilih
         */
        $query = $this->db->query("select SUM(Berat) as total_telur from tbtelur where Tanggal between '$first' and '$end'")->row();
        return $query->total_telur;
    }
    public function TotalTelurByRange($first, $end)
    {
        // sort total berat by current date
        /**
         *  mengambil data berdasarkan range tanggal yang dipilih
         */
        $query = $this->db->query("select COUNT(Telur) as telur from tbtelur where Tanggal between '$first' and '$end'")->row();
        return $query->telur;
    }


    public function TelurBaikByRange($first, $end)
    {
        /**
         *  mengambil data berdasarkan range tanggal yang dipilih
         */
        $query = $this->db->query("select COUNT(Telur) as telur from tbtelur where  Kondisi='Baik' and Tanggal between '$first' and '$end'")->row();

        return $query->telur;
    }

    public function TelurBurukByRange($first, $end)
    {
        /**
         *  mengambil data berdasarkan range tanggal yang dipilih
         */
        $query = $this->db->query("select COUNT(Telur) as telur from tbtelur where  Kondisi='Buruk' and Tanggal between '$first' and '$end'")->row();

        return $query->telur;
    }

    public function AvgBeratTelurByRange($first, $end)
    {
        /**
         *  mengambil data berdasarkan range tanggal yang dipilih
         */
        $query = $this->db->query("select AVG(Berat) as rata_rata from tbtelur where Tanggal between '$first' and '$end'")->row();
        return $query->rata_rata;
    }

    public function GrafikTelur($first, $end)
    {
        /**
         *  mengambil data berdasarkan range tanggal yang dipilih
         */
        $query = "SELECT COUNT(*) AS total, Kondisi FROM tbtelur where Tanggal between '$first' and '$end' GROUP BY Kondisi";

        return $this->db->query($query)->result_array();
    }
}
