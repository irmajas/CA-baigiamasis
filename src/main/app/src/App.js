import React from 'react';
// import logo from './logo.svg';
import './App.css';
import Header from './components/Header';
import Irasai from './components/IrasaiList';
import Irasas from './components/Irasas';
import IrasasEdit from './components/IrasasEdit';
import IrasasAdd from './components/IrasasAdd';


import { API_ENDPOINTS } from "./components/utils/constants";
import { API_DOMAIN } from "./components/utils/constants";
import Komentarai from './components/KomentaruAts';

class App extends React.Component {
  constructor() {
    super()
    this.state = {
      irasid: 0,
      loadingis: true,
      refreshall: false
    }
    this.handler = this.handler.bind(this)
    this.handler2 = this.handler2.bind(this)
    this.handler3 = this.handler3.bind(this)
    this.handlernew = this.handlernew.bind(this)
    this.handleradmin = this.handleradmin.bind(this)
  }
  componentDidMount() {
    const url = API_ENDPOINTS.naujausias
    this.setState({ lodingis: true })
    fetch(`${API_DOMAIN}${url}`)
      .then(response => response.json())
      .then(data => {
        this.setState({
          irasid: data.latestid,
          loadingis: false,
          refresh: false,
          admin: false,
          refreshall: false,
          addnew: false
        })
      })
  }



  render() {
    const styles = { visibility: "visible" }
    if (this.state.admin) {
      styles.visibility = "visible"
    } else {
      styles.visibility = "hidden"
    }
    return (
      <div className="App" >
        <Header />
        <main>
          <div>
            {this.state.admin ? <button onClick={this.handleradmin}>Log out </button>
              :
              <button onClick={this.handleradmin}>Login as Admin </button>
            }
          </div>
          <div className="App--contenst row">
            <div className="App-content--left column1">
              <button style={styles} onClick={this.handlernew}>Naujas irasas </button>
              <Irasai handler={this.handler} refreshall={this.state.refreshall} />
            </div>
            <div className="App-content--right column2">
              {this.state.addnew ? <IrasasAdd refresh={this.state.refresh} handler3={this.handler3} handler2={this.handler2} /> :
                this.state.loadingis ? <h4>Loading... </h4> : this.state.admin ?
                  <div>
                    <IrasasEdit value={this.state.irasid} refresh={this.state.refresh} handler3={this.handler3} handler2={this.handler2} />
                    <Komentarai value={this.state.irasid} refresh={this.state.refresh} /> 
                  </div>
                  :
                  <Irasas value={this.state.irasid} refresh={this.state.refresh} />}
            </div>
          </div>
        </main>
      </div>
    );
  }

  handlernew() {

    this.setState({ addnew: true })

  }

  handler(idis) {

    this.setState(prevState => ({ irasid: idis, refresh: !prevState.refresh }))

  };

  handler2(id) {
    console.log("this is handler 2")
     console.log(this.state)
    this.setState(prevState => ({ refreshall: !prevState.refreshall }))
    this.setState({ addnew: false })
    this.setState({ irasid: id })
    this.setState(prevState => ({ refresh: !prevState.refresh }))
    console.log(this.state)
  };

  handler3(id) {
    console.log("here is handler  3")
   console.log("id is comming "+id)

        this.setState(prevState => ({
          irasid: id,
          loadingis: false,
          refreshall: !prevState.refreshall,
          refresh: !prevState.refresh,
          admin: prevState.admin
        }))
      
     
  }
  handleradmin() {
 
   
    this.setState(prevState => ({ admin: !prevState.admin }))
 
  };
}
export default App