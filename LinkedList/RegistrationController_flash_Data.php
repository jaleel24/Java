<?php
class RegistrationController extends CI_Controller{
//<!-- i am making the constructor so that i can easily call the modle in short load database every time i use this class -->
  function __construct(){
    parent::__construct();
    $this->load->model('UserReg_Model','model');
  }
  public function index(){
    $this->load->helper('form');
    $this->load->view('p_forms/user_registration_form');
  }
  public function login_view(){
    $this->load->helper('form');
    $this->load->view('p_forms/login_form');
  }
  public function login_user(){
    $this->load->library('form_validation');
    $this->form_validation->set_rules('username','username','required|alpha|max_length[8]|trim');
    $this->form_validation->set_rules('email','email','required|trim|valid_email');
    $this->form_validation->set_rules('password','password','required|alpha_numeric|max_length[10]');
    $this->form_validation->set_error_delimiters("<p class='text-danger'>","</p>");
    if($this->form_validation->run() === TRUE){

      $username= $this->input->post('username');
      $email= $this->input->post('email');
      $password= $this->input->post('password');

      $this->load->model('UserReg_Model');
      $this->load->library('session');
      $userObject = $this->model->valid_userdb($username,$email,$password);
      if($userObject !== NULL){
        $this->session->set_flashdata('message','Succes login');
      }else{
          $this->session->set_flashdata('message','Sorry login failed');
      }
    redirect('RegistrationController/login_view');
    }else{
      $this->load->view('p_forms/login_form');
    }
}

  public function insert_user_data(){
    $object = new stdClass();
    $object= (object)$this->input->post(NULL,TRUE);
    print_r($object);
    $this->model->form_insert($object);
    echo "Data is added to the db successfuly";
  }
  public function showuser_data(){
    //first we will load the model to get the data and assign in the $data which we will pass in the arry named $list
    $data=$this->model->getuserdata();
    $list=array(
      'records'=>$data
    );
    // then we gonna load the helper form to load the form to present the data in the table
    $this->load->helper('form');
    //then we will load the view of the table in which we will show the data of the users
    $this->load->view('p_forms/userlist',$list);

  }
  public function Edit_userlist($id){
    $this->load->helper('form');
    //$user=$this->model->get_userBy_id();
    $this->load->model('get_userBy_id','model');
    $user=$this->model->get_userBy_id($id);
  //print_r($user);

  $this->load->view('p_forms/edit_form',['user'=>$user]);
  }
  public function update_user_data($id){
   //print_r($this->input->post());
  // print_r($id);
   //exit();
   $this->load->model('UserReg_Model','model');
   $object=new stdClass();
   $object=(object)$this->input->post(NULL,TRUE);


   if($this->model->update_user_data_db($id,$object)){
      redirect('RegistrationController/');


   }
   else{
     echo "Data did not get updated in the db successfuly";
   }
}
public function delete_userlist(){
  $this->load->model('UserReg_Model','model');
  $id = $this->uri->segment(3);
  if($this->model->delete_user_data_db($id)){
     redirect('RegistrationController/');
    echo "This particular id has been deleted";
  }
  else {
    echo "This particular id cannot be deleted";
  }

}
public function add_number_form($id){
  $this->load->helper('form');
 $data['user_id']=$id;
  $this->load->view('p_forms/add_number_form',$data);
}
public function insert_numbers(){

  $object= new stdClass();
  $object = (object)$this->input->post(NULL,TRUE);
  print_r($object);
  $this->load->model('UserReg_Model','model');
  $this->model->insert_phone_number($object);
  echo "Numbers has been added successfuly:)";
}

public function showuser_numbers(){
  $list = $this->model->showuser_numbersmodel();
  $data=array(
    'records'=> $list
  );
//  echo "<pre>";
//  print_r($data);
  //exit;
  $this->load->helper('form');
  $this->load->view('p_forms/user_numbers_list',$data);
}
public function Edit_usernumber($id){
  $user=$this->model->get_userBy_id_numbers($id);
  $this->load->helper('form');
  $this->load->view('p_forms/edit_number_form',['user'=>$user]);

}
public function update_usernumber_list($id){
 //print_r($this->input->post());
// print_r($id);
 //exit();
 $this->load->model('UserReg_Model','model');
 $object=new stdClass();
 $object=(object)$this->input->post(NULL,TRUE);


 if($this->model->update_user_numberlistdb($id,$object)){
    redirect('RegistrationController/');


 }
 else{
   echo "Data did not get updated in the db successfuly";
 }
}
public function delete_usernumber(){
  $this->load->model('UserReg_Model','model');
  $id= $this->uri->segment(3);
  if($this->model->delete_usernumberBy_id($id)){
    echo "deleted";
    //redirect('RegistrationController/');
  }
  else{
    echo "Numbers cannot be deleted so buzz off";
  }

}
}
 ?>
