import React from 'react';
import './index.css';
import { API_ENDPOINTS } from "../utils/constants";
import { API_DOMAIN } from "../utils/constants";

import Komentarai from '../Komentarai';


class Irasas extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            irasas: {},
            loadingis: true
        }
    }
    componentDidMount() {
        const value = this.props.value;
        const url = API_ENDPOINTS.vienasirasas + "/" + value;
        this.setState({ loadingis: true })
        fetch(`${API_DOMAIN}${url}`)
            .then(response => response.json())
            .then(data => {
                this.setState({
                    irasas: data,
                    loadingis: false
                })
            })
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
                        irasas: data,
                        loadingis: false
                    })
                })
        }
      }
    render() {

        return (
            <div>
            {
                this.state.loadingis ? <div>Loading...</div> :
                    (
                        <div className="Irasas--visas">
                            <h1 className="Irasas--Pavadinimas">{this.state.irasas.pavadinimas}</h1>
                            <p className="Irasas--data">{this.state.irasas.irasodata}</p>
                            <hr className="Irasas--separator" />
                            <p className="Irasas--Tekstas">{this.state.irasas.tekstas}</p>
                            <Komentarai value={this.state.irasas.id} />
                        </div>
                    )

            }
            </div>
        )
    }
}
export default Irasas;
