import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import { TEST_URL} from './config/config';

function App() {
  console.log(TEST_URL);
  console.log("Alo");
  fetch(TEST_URL).then((response)=>{
    console.log("HICE FETCH prueba");
    console.log(response.json);
  });

  axios.get(TEST_URL).then(()=>{
    console.log("Hola");
  }).catch((e)=>{
    console.log(e);
  });

  return (
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
