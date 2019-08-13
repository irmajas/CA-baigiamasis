import React from 'react';
import './index.css';
import { API_ENDPOINTS } from "../utils/constants";
import { API_DOMAIN } from "../utils/constants";
import { put } from "../utils/put";
import { delrest } from "../utils/delete";

class IrasasEdit extends React.Component {
  constructor(props) {

    super(props)
    this.state = {
      irasas: {
        id: this.props.value,
        pavadinimas: "",
        tekstas: "",
        irasodata: ""
      },
      loadingis: true
    }
    this.dochange = this.dochange.bind(this)
    this.updateIrasas = this.updateIrasas.bind(this)
    this.deleteIrasas = this.deleteIrasas.bind(this)
  }
  componentDidUpdate(props) {

    const { refresh, value } = this.props;
    const url = API_ENDPOINTS.vienasirasas + "/" + value;
    if (props.refresh !== refresh) {
      this.setState({ loadingis: true })
      fetch(`${API_DOMAIN}${url}`)
        .then(response => response.json())
        .then(data => {
          this.setState({
            irasas: {
              id: data.id,
              pavadinimas: data.pavadinimas,
              tekstas: data.tekstas,
              irasodata: data.irasodata
            },
            loadingis: false
          })
        })
    }
  }

  componentDidMount() {

    const url = API_ENDPOINTS.vienasirasas + "/" + this.props.value;
    this.setState({ loadingis: true })
    fetch(`${API_DOMAIN}${url}`)
      .then(response => response.json())
      .then(data => {
        this.setState({
          irasas: {
            id: data.id,
            pavadinimas: data.pavadinimas,
            tekstas: data.tekstas,
            irasodata: data.irasodata
          },
          loadingis: false
        })
      })

  }

  dochange(e) {
    this.setState({
      irasas: Object.assign(
        {},
        this.state.irasas,
        { [e.target.name]: e.target.value }
      ),
      loadingis: {}
    })
    console.log(this.state)
  }
  updateIrasas(e) {
     e.preventDefault();
    const url = API_ENDPOINTS.updateIrasas + "/" + this.props.value
    const response = put(url, this.state.irasas)
    
    this.props.handler3(this.state.irasas.id)
  }
  deleteIrasas(e) {
    e.preventDefault();
    if (window.confirm("Ar tikrai norite pašalinti šį įrašą ?")) {
      const url = API_ENDPOINTS.deleteIrasas + "/" + this.props.value
      const response = delrest(url)
      
     
      const urlnew = API_ENDPOINTS.naujausias
      fetch(`${API_DOMAIN}${urlnew}`)
      .then(response => response.json())
      .then(data => {
        console.log(data)
        this.props.handler3(data.latestid)
      })
     // this.props.handler3()
    }
 

  }
  render() {
    let pavadin = this.state.irasas.pavadinimas
    let tekst = this.state.irasas.tekstas
    return (
      <div>
        {this.state.isloading ? <div>Loading...</div> :
          <div>
            <form onSubmit={this.updateIrasas}>
              <div className="container">
                <p>Paskutinė įrašo redagavimo data: {this.state.irasas.irasodata}</p>
                <button className="formlaukas" onClick={this.deleteIrasas}>Pašalinti įrašą </button>
              </div>
              <br />

              <button className="formlaukas">Įrašyti</button>


              <div className="formlaukas">
                <label>Pavadinimas</label>
                <textarea type="text" cols="80" rows="3" name="pavadinimas" value={pavadin} onChange={this.dochange} />
              </div>
              <div className="formlaukas">
                <label>Tekstas</label>
                <textarea type="text" cols="80" rows="35" name="tekstas" value={tekst} onChange={this.dochange} />
              </div>

            </form>
            {/* 
            <Komentarai value={this.props.value} /> */}
          </div>
        }
      </div>

    )
  }
}



export default IrasasEdit;
