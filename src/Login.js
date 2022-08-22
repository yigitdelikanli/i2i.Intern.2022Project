import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import "react-phone-input-2/lib/style.css";
import { Link } from "react-router-dom";




class Login extends React.Component {
  state = { users: [], password: null, msisdn: "", passwordError: null };

  onChangeHandler = (event) => {
    const target = event.target;
    var value = target.value;
    const name = target.name;

    this.setState({
      [name]: value,
    });
  };
  validate=()=> {
    let passwordError = "";
    

    if (!this.state.password) {
      passwordError = "Password field is required";
    }
    if (passwordError) {
      this.setState({ passwordError });
      return false;
    }
    return true;
  }

  onSubmitHandler = (event) => {
    if(this.validate()){
      event.preventDefault();
      this.componentDidMount();
      this.checkUser();  
    }
  };

  async componentDidMount() {
    this.getUser();
  }

  getUser = () => {
    fetch("http://34.140.158.254:8082/"+this.state.msisdn+"/"+this.state.password)
      .then((Response) => Response.json())
      .then((data) => this.setState({ users: data }));
  };

  checkUser = () => {
    this.state.users.map((user) => (
      <div>{user.password === this.state.password && user.msisdn === this.state.msisdn ? this.navigateToInfo() : window.alert("Invalid") }</div> 
    ))

  }

  navigateToInfo = () => {
    window.sessionStorage.setItem("msisdn", this.state.msisdn);
    window.location.href = "/information"; 
    
  }
  
  render() {
    
    return (
      
      <div className="App">
        <div class="container-fluid ps-md-0">
          <div class="row">
            <div class="left col-md-8 col-lg-6">
              <div class="bg-image-2"></div>
              <div class="login d-flex align-items-center py-5">
                <div class="container">
                  <div class="row">
                    <div class="col-md-9 col-lg-8 mx-auto">
                      <h3 class="login-heading mb-4">
                        Very Easy Very Professional!
                      </h3>
                      <h4 class="login-heading mb-4">Login to Your Account</h4>
                     


                      <form onSubmit={this.onSubmitHandler}>
                        <div class="form-floating mb-3">
                          <input
                            className="form-control"
                            type="text"
                            id="msisdn"
                            name="msisdn"
                            placeholder="Enter phone number without 0 "
                            onChange={this.onChangeHandler}
                          />
                          <label for="msisdn">
                            Enter phone number without 0
                          </label>
                        </div>

                        
                        

                        <div class="form-floating mb-3">
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
                          <label for="floatingPassword">Password</label>
                          <span className="text-danger">
                            {this.state.passwordError}
                          </span>
                        </div>

                        <div class="form-check mb-3">
                          <input
                            class="form-check-input"
                            type="checkbox"
                            value=""
                            id="rememberPasswordCheck"
                          />
                          <label
                            class="form-check-label"
                            for="rememberPasswordCheck"
                          >
                            Remember me
                          </label>
                        </div>

                        <button
                          class="btn  btn-primary btn-login-1 fw-bold mb-2 col-4"
                          type="submit"
                          
                        >
                          Login
                        </button>

                        <Link to="/register">
                          <button
                            class="btn btn-outline-primary btn-login-2 fw-bold mb-2 col-4"
                            type="button"
                          >
                            Register
                          </button>
                        </Link>

                        <div class="text-center">
                          
                          <a class="small" href="/forgotPassword">
                            Forgot password?
                          </a>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
          </div>
        </div>
      </div>
      
    );
    
  }
}

export default Login;
