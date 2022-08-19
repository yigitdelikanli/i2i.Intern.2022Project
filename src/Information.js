import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import "https://cdnjs.cloudflare.com/ajax/libs/react/17.0.0/umd/react.production.min.js"
import "https://cdnjs.cloudflare.com/ajax/libs/react-dom/17.0.0/umd/react-dom.production.min.js"
import { Link } from "react-router-dom";
import logo from "./images/logo_final_1.png";


class Information extends React.Component {
  state = {
    infos: [],
    usageInfos:[],
    amountData: null,
    amountSms: null,
    amountVoice: null,
    duration: null,
    packageId: null,
    packageName: "",
    data: null,
    sms: null,
    voice: null,
    price: null
  };

  async componentDidMount() {
    this.getUser();
    this.getUsage();
  }

  getUser = () => {
    fetch(
      "http://34.140.158.254:8082/package/packageInfoInList?MSISDN=" +
        window.sessionStorage.getItem("msisdn")
    )
      .then((Response) => Response.json())
      .then((data) => this.setState({ infos: data }))
      .then(this.checkData);
  };
  getUsage=()=>{
    fetch(
      "http://34.140.158.254:8082/balance/balanceByMSISDNinList?MSISDN=" +
        window.sessionStorage.getItem("msisdn")
    )
      .then((Response) => Response.json())
      .then((data) => this.setState({ usageInfos: data }))
      .then(this.checkUsage);

  }

  checkData = () => {
    this.state.infos.map((info) =>
      this.setState({
        amountData: info.amountData,
        amountSms: info.amountSms,
        amountVoice: info.amountVoice,
        duration: info.duration,
        packageId: info.packageId,
        packageName: info.packageName,
      })
    );
  };
  checkUsage = ()=>{
    this.state.usageInfos.map((usageInfo) =>
      this.setState({
        data: usageInfo.data,
        sms: usageInfo.sms,
        voice: usageInfo.voice,
        price: usageInfo.price
      })
    );

  }
  
  render() {
    return (
      
      <div class="container-fluid cont-fluid">
        <img src={logo} id="logo" alt="logo" />

        <Link to="/">
          <button class="btn btn-light">Log out</button>
        </Link>

        <div class="box remaining-use">
          <h3 id="header">My Remaining Use</h3>
          <div class="percent">
            <svg>
            
              <circle cx="70" cy="70" r="70" />
              <circle cx="70" cy="70" r="70"  stroke="#03a9f4"  strokeDashoffset={440-(440*this.state.voice)/this.state.amountVoice} />
            </svg>
            <div class="number">
              <h4>
              {(this.state.voice*100/this.state.amountVoice).toFixed(2)}<span>%</span>
              </h4>
            </div>
            <div className="info-dakika">
              <h3 id="dakika">
                {this.state.voice}/{this.state.amountVoice} <span>DK</span>
              </h3>
            </div>
            <div className="info-internet">
              <h3 id="internet">
                {this.state.data}/{this.state.amountData} <span>MB</span>
              </h3>
            </div>
            <div className="info-sms">
              <h3 id="sms">
                {this.state.sms}/{this.state.amountSms} <span>SMS</span>
              </h3>
            </div>
          </div>

          <div class="percent2">
            <svg>
              <circle cx="70" cy="70" r="70"   />
              <circle cx="70" cy="70" r="70"stroke="#03a9f4" strokeDashoffset={440-(440*this.state.data)/this.state.amountData} />
            </svg>
            <div class="number2">
              <h4>
              {(this.state.data*100/this.state.amountData).toFixed(2)}<span>%</span>
              </h4>
            </div>
          </div>

          <div class="percent3">
            <svg>
              <circle  cx="70" cy="70" r="70" />
              <circle cx="70" cy="70" r="70" stroke="#03a9f4" strokeDashoffset={440-(440*this.state.sms)/this.state.amountSms}/>
            </svg>
            <div class="number3">
              <h4>
                {(this.state.sms*100/this.state.amountSms).toFixed(2)}<span>%</span> 
              </h4>
            </div>
          </div>
        </div>
        <div class="box user-info">
          <h3 id="header" >User info</h3>
          <div>
            <h2 id="packageName">Package Name : {this.state.packageName}</h2>
            <h2 id="packageName">Price : {this.state.price}</h2>
          </div>
        </div>
        <div class="box date">
          <h3 id="header">When will it ends</h3>
          <div>
            <h2 id="duration">{this.state.duration} days remaining</h2>
          </div>
        </div>
      </div>
    );
  }
}

export default Information;
