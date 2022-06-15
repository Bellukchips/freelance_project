<?php

class Auth extends CI_Controller
{

    public function __construct()
    {
        parent::__construct();
        $this->load->model('auth_model');
    }
    public function index()
    {
        show_404();
    }

    public function login()
    {
        $rules = $this->auth_model->rules();
        $this->form_validation->set_rules($rules);

        if ($this->form_validation->run() == FALSE) {
            return $this->load->view('login');
        }

        $username = $this->input->post('username');
        $password = $this->input->post('password');

        if ($this->auth_model->login($username, $password)) {
            redirect('home');
        } else {
            $this->session->set_flashdata('message_login_error', 'Login Gagal, pastikan username dan passwrod benar!');
        }

        $this->load->view('login');
    }

    public function logout()
    {
        $this->auth_model->logout();
        redirect(site_url());
    }
}
