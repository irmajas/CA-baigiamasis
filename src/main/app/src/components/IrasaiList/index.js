import React from 'react';
import './index.css';
import { API_ENDPOINTS } from "../utils/constants";
import { API_DOMAIN } from "../utils/constants";

class Irasai extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      irasailist: [],
      isloading: true
    }
  }
  componentDidMount() {
    const url = API_ENDPOINTS.irasas
    this.setState({ lodingjoke: true })
    fetch(`${API_DOMAIN}${url}`)
      .then(response => response.json())
      .then(data => {
        this.setState({
          irasailist: data,
          isloading: false
        })
      })
  }

  componentDidUpdate(props) {

    const { refreshall } = this.props;
    const url = API_ENDPOINTS.irasas
    if (props.refreshall !== refreshall) {
      this.setState({ loadingis: true })
      fetch(`${API_DOMAIN}${url}`)
        .then(response => response.json())
        .then(data => {
          this.setState({
            irasailist: data,
            isloading: false
          })
        })
    }
  }
  render() {
    const { irasailist } = this.state;

    return (
      <section className="Irasas">
        <h3>Naujausi blogai</h3>
        {this.state.isloading ? <div>Loading...</div> :
          irasailist.map(data =>
            (
              <div key={data.id}>
                < h4> {data.pavadinimas} </h4>
                <div className="data"> {data.irasodata}</div>
                <div className="tekstas">
                  <p>{data.tekstas} </p>
                  <button className="formlaukas" value={data.id} onClick={() => this.props.handler(data.id)}>Daugiau</button>
                </div>
              </div>
            )
          )

        }
      </section>
    );
  }
}

export default Irasai;
