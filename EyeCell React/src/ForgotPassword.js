import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";

//http://34.140.158.254:8082/forgotp?email=Aydemir081%40gmail.com&securityQuestion=sefa

class ForgotPassword extends React.Component {
  state = { email: "", securityQuestion: "" };

  onChangeHandler = (event) => {
    const target = event.target;
    var value = target.value;
    const name = target.name;

    this.setState({
      [name]: value,
    });
  };

  

  onSubmitHandler = (event) => {
      event.preventDefault();
      this.componentDidMount();
      window.location.href = "/";
  };
  async componentDidMount(){
    this.handleClick();
  }

  handleClick = () => {
    var jsonData = {
      email: this.state.email,
      securityQuestion: this.state.securityQuestion  
    };
    var formData = new FormData();
    formData.append("json1", JSON.stringify(jsonData));

    fetch(
      "http://34.140.158.254:8082/forgotp?email="+this.state.email+"&securityQuestion="+this.state.securityQuestion,
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        mode: "cors",

        body: formData,
      }
    );
  };

  render() {
    return (
      <div className="App">
        <div class="container-fluid ps-md-0">
          <div class="row">
            <div class="left col-md-8 col-lg-6">
              <div class="bg-image-2"></div>
              <div class="login d-flex align-items-center">
                <div class="container">
                  <div class="row">
                    <div class="col-md-9 col-lg-8 mx-auto">
                      <form onSubmit={this.onSubmitHandler}>
                        <div class="form-check mb-3">
                          <label for="floatingInput">Enter your email</label>
                          <input
                            type="email"
                            className={"form-control "}
                            id="floatingInput"
                            name="email"
                            placeholder="name@example.com"
                            style={{ marginBottom: "20px" }}
                            value={this.state.email}
                            onChange={this.onChangeHandler}
                          />
                        </div>
                        <div class="form-check mb-3">
                          <label for="securityQuestion">
                            Security Question
                          </label>
                          <input
                            type="text"
                            className={"form-control "}
                            id="securityQuestion"
                            name="securityQuestion"
                            placeholder="Childhood street"
                            value={this.state.securityQuestion}
                            onChange={this.onChangeHandler}
                          />
                        </div>
                        <button
                          class="btn  btn-primary btn-login-1 fw-bold mb-2 col-4"
                          type="submit"
                          style={{ marginLeft: "25px" }}
                          
                        >
                          Send
                        </button>
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

export default ForgotPassword;
