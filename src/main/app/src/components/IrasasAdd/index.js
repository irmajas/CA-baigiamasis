import React from 'react';
import './index.css';
import { API_ENDPOINTS } from "../utils/constants";
// import { API_DOMAIN } from "../utils/constants";

import { post } from "../utils/post";


class IrasasAdd extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      irasas: {}
    }

    this.editIrasas = this.editIrasas.bind(this)
    this.handleInputChange = this.handleInputChange.bind(this)
  }


  handleInputChange(e) {
    this.setState({
      irasas: Object.assign(
        {},
        this.state.irasas,
        { [e.target.name]: e.target.value }
      )
    })
  }
  editIrasas = async event => {
    event. preventDefault();
    if (!this.state.irasas.pavadinimas || !this.state.irasas.tekstas) return;

    const response = await post(API_ENDPOINTS.addIrasas, this.state.irasas);
    
    const newid=response.id
     console.log(" new id is "+newid)
    this.props.handler2(newid)
  };
  render() {


    return (
      <div className="Irasas--visas">
        <form onSubmit={this.editIrasas}>
          <div className="formlaukas">
            <label>Pavadinimas</label>
            <textarea type="text" cols="80" rows="3" name="pavadinimas" value={this.state.irasas.pavadinimas} onChange={(event) => this.handleInputChange(event)} />
          </div>
          <div className="formlaukas">
            <label>Tekstas</label>
            <textarea type="text" cols="80" rows="50" name="tekstas" value={this.state.irasas.tekstas} onChange={(event) => this.handleInputChange(event)} />
          </div>
          <div className="formlaukas mygtuk">
            <button >Įrašyti</button>
          </div>
        </form>
      </div>
    )
  }
}
export default IrasasAdd;
