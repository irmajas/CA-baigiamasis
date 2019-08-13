import React from 'react';
import './index.css';
import { API_ENDPOINTS } from "../utils/constants";
import { API_DOMAIN } from "../utils/constants";
import { put } from "../utils/put";



class Komentarai extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      komentarailist: [],
      loadingis: false

    }
    this.dochange = this.dochange.bind(this)
  }
  componentDidMount() {
    console.log(this.props)
    if (this.props.value !== 0) {

      const url = API_ENDPOINTS.komentaras + "/" + this.props.value;
      this.setState({ loadingis: true })
      fetch(`${API_DOMAIN}${url}`)
        .then(response => response.json())
        .then(data => {
          console.log("gauta")
          console.log(data);
          this.setState({
            komentarailist: data,
            isloading: false
          })

        })
        .catch(e => {
          console.log(e);
          this.setState({ ...this.state, isFetching: false });
        });
    }
  }

  componentDidUpdate(props) {

    const { refresh, value } = this.props;
    const url = API_ENDPOINTS.komentaras + "/" + value;
    if (props.refresh !== refresh) {
      this.setState({ loadingis: true })
      fetch(`${API_DOMAIN}${url}`)
        .then(response => response.json())
        .then(data => {
          console.log("gauta")
          console.log(data);
          this.setState({
            komentarailist: data,
            isloading: false
          })

        })
        .catch(e => {
          console.log(e);
          this.setState({ ...this.state, isFetching: false });
        });
    }
  }


  dochange(id, e) {

    let eev = e.currentTarget
    this.setState(prevState => {
      const komentarailist = prevState.komentarailist.map((item) => {
        if (item.id === id) {

          item.atsakymas = eev.value
        }
        return item

      });
      return {
        klausimailist: komentarailist,
        isloading: false
      }
    })

  }
  saveats(id, e) {
    e.preventDefault();
    const url = API_ENDPOINTS.uptadeKomentras + "/" + id
    const response = put(url, this.state.komentarailist[id - 1])

  }

  render() {
    let komentarailistas = this.state.komentarailist;
    console.log(komentarailistas)
    return (
      <section>
        <h4>Komentarai</h4>
        {this.state.isloading ? <div>Loading...</div> :
          komentarailistas.map(data =>
            (
              <form>
                <div key={data.id}>

                  <div className="data"> {data.autorius}  {data.komentdata} {data.id}</div>
                  <div className="tekstas">{data.komentaras}</div>
                  <div className="formlaukas">
                    <label>Jūsų atsakymas </label>
                    <input type="text" name="atsakymas" key={data.id} value={data.atsakymas} onChange={(e) => this.dochange(data.id, e)} />
                    <button className="formlaukas" onClick={(e) => this.saveats(data.id, e)}>Atsakyti</button>
                  </div>

                </div>
              </form>

            )
          )
        }
      </section>
    )
  }

}

export default Komentarai
