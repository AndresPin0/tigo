import logo from './logo.svg';
import './App.css';
import { axiosInstance } from './services/axios';

function App() {

  /*
    fetch(TEST_URL).then((response)=>{
      console.log("HICE FETCH prueba");
      console.log(response.json);
    });
  
  */
 axiosInstance.get("/auth/test").then((response)=>{
  console.log(response);
 });
  

    return(
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
}

export default App;
