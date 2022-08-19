import React from 'react'
import {BrowserRouter , Routes, Route} from 'react-router-dom';
import Information from './Information';
import Login from './Login';
import Register from "./Register"




function App(){
    
    return (
      <div>
        <BrowserRouter>
            <Routes>
              <Route path="/" element={<Login/>}/>
              <Route path="register/*" element={<Register/>} />
              <Route path="information/*" element={<Information/>} />

            </Routes>
        </BrowserRouter>
      </div>
    );
  
}

export default App;
