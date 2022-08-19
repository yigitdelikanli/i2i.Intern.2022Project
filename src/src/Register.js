import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import "react-phone-input-2/lib/style.css";



class Register extends React.Component {
  state = {
    name: "",
    msisdn: "",
    email: "",
    password: "",
    package_id: "",
    securityQuestion:"",
    nameError: null,
    emailError: null,
    passwordError: null,
  };

  onChangeHandler = (event) => {
    const target = event.target;
    var value = target.value;
    const name = target.name;
    this.setState({
      [name]: value,
    });
  };
  validate = () => {
    let nameError = "";
    let emailError = "";
    let passwordError = "";
    if (!this.state.name) {
      nameError = "Name field is required";
    }
    const reg = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
    if (!this.state.email || reg.test(this.state.email) === false) {
      emailError = "Email Field is Invalid ";
    }
    if (!this.state.password) {
      passwordError = "Password field is required";
    }
    if (emailError || nameError || passwordError) {
      this.setState({ emailError, nameError, passwordError });
      return false;
    }
    return true;
  };
  onSubmitHandler = (event) => {
    if (this.validate()) {
      event.preventDefault();
      this.componentDidMount();
      window.location.href = "/";
    }
  };
  
  
  async componentDidMount(){
    this.handleClick();
  }


  handleClick = () =>{
    var jsonData = {
      "msisdn": this.state.msisdn, 
      "email": this.state.email, 
      "name": this.state.name,
      "packageId" : parseInt(this.state.package_id),
      "password": this.state.password, 
      "securityQuestion": this.state.securityQuestion,
      "surname": this.state.surname  
    }
    var formData = new FormData();
    formData.append('json1', JSON.stringify(jsonData));


    fetch('http://34.140.158.254:8082/subs/register?MSISDN='+this.state.msisdn+'&email='+this.state.email+'&name='+this.state.name+'&packageId='+this.state.package_id+'&password='+this.state.password+'&securityQuestion='+this.state.securityQuestion+'&surname='+this.state.surname, { 

     method: 'POST', 
     headers:{"Content-Type": "application/json"},
     mode: 'cors',

     body: formData

  })
    
  }

  render() {
    return (
      <div className="App">
        <div class="container-fluid ps-md-0">
          <div class="row ">
            <div class="left col-md-8 col-lg-6">
              <div class="bg-image-2"></div>
              <div class="d-flex align-items-center ">
                <div class="container reg-container">
                  <div class="row">
                    <div class="col-md-9 col-lg-8 mx-auto">
                      <h3 class="register-heading mb-4">
                        Very Easy Very Professional!
                      </h3>

                      <form onSubmit={this.onSubmitHandler}>
                        <div class="form-check mb-3">
                          <label for="name">Enter your name</label>
                          <input
                            type="text"
                            className={
                              "form-control " +
                              (this.state.nameError ? "invalid" : "")
                            }
                            id="name"
                            name="name"
                            placeholder="Name"
                            onChange={this.onChangeHandler}
                          />
                        </div>

                        <div class="form-check mb-3">
                          <label for="Surname">Enter your surname</label>
                          <input
                            type="text"
                            className={"form-control "}
                            id="surname"
                            name="surname"
                            placeholder="Surname"
                            onChange={this.onChangeHandler}
                          />
                        </div>

                        <div class="form-check mb-3">
                          <label for="floatingPassword">
                            Enter your password
                          </label>
                          <input
                            type="password"
                            className={
                              "form-control " +
                              (this.state.passwordError ? "invalid" : "")
                            }
                            id="floatingPassword"
                            name="password"
                            placeholder="Password"
                            onChange={this.onChangeHandler}
                          />

                          <span className="text-danger">
                            {this.state.passwordError}
                          </span>
                        </div>

                        <div class="form-check mb-3">
                          <label for="msisdn">Enter phone number</label>
                          <input
                            className="form-control"
                            type="text"
                            id="msisdn"
                            name="msisdn"
                            placeholder="Enter phone number without 0 "
                            onChange={this.onChangeHandler}
                          />
                        </div>

                        <div class="form-check mb-3">
                          <label for="package">Package</label>
                          <select
                            class="form-select"
                            name = "package_id"
                            id="package_id"
                            onChange={this.onChangeHandler}
                          >
                            <option selected>Select Package</option>
                            <option value="1">EYECELL 3GB</option>
                            <option value="2">EYECELL 5GB</option>
                            <option value="3">EYECELL 7GB</option>
                            <option value="4">EYECELL 10GB</option>
                            <option value="5">LAVANTA</option>
                            <option value="6">BİZONTELE 15GB</option>
                          </select>
                        </div>
                        

                        <div class="form-check mb-3">
                          <label for="floatingInput">Enter your email</label>
                          <input
                            type="email"
                            className={
                              "form-control " +
                              (this.state.emailError ? "invalid" : "")
                            }
                            id="floatingInput"
                            name="email"
                            placeholder="name@example.com"
                            value={this.state.email}
                            onChange={this.onChangeHandler}
                          />

                          <span className="text-danger">
                            {this.state.emailError}
                          </span>
                        </div>

                        <div class="form-check mb-3">
                          <label for="securityQuestion">Security Question</label>
                          <input
                            type="text"
                            className={
                              "form-control "   
                            }
                            id="securityQuestion"
                            name="securityQuestion"
                            placeholder="Childhood street"
                            value={this.state.securityQuestion}
                            onChange={this.onChangeHandler}
                          />

                        </div>

                        <button
                          class="btn btn-primary btn-reg fw-bold mb-2 col-4"
                          type="submit"
                          
                        >
                          Register
                        </button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class=" d-md-flex col-md-4 col-lg-6 bg-image"></div>
          </div>
        </div>
      </div>
    );
  }
}
export default Register;
