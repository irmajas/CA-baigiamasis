import React, { useState } from 'react';
import './index.css';
import { useFetch } from "../utils/fetch-hook";
import { API_ENDPOINTS } from "../utils/constants";
import { post } from "../utils/post";


function Komentarai(props) {
  const irasid = props.value;
  const komenturl = API_ENDPOINTS.komentaras + "/" + irasid;
  const { loading, data, setData } = useFetch(komenturl);

  const addKomentaras = koment => {
    setData([...data, koment])
  };

  return (
    <section className="Komentarai">
      <h5 className="komentaras">Komentarai</h5>
      {loading ?
        <div>Loading...</div>
        :
        data.map(Item)
      }
      <AddKomentForm addKomentaras={addKomentaras} value={irasid} />
    </section>
  );
}
function Item(props) {

  return (
    <div key={props.id}>

      <div className="data"> {props.autorius}  {props.komentdata}</div>
      <div className="tekstas">{props.komentaras}</div>
      <div className="atsakymas">{props.atsakymas}</div>

      {/* <button  value={props.data.id} onClick={clicked}>Delete</button> */}
    </div>
  );
}
function AddKomentForm(props) {
  const initialFormState = { autorius: '', komentaras: '' };
  const [komentaras, setKomentaras] = useState(initialFormState);

  const handleInputChange = event => {
    const { name, value } = event.target;

    setKomentaras({ ...komentaras, [name]: value })
  };

  const createKomentaras = async event => {
    event.preventDefault();
    if (!komentaras.autorius || !komentaras.komentaras) return;
    console.log(komentaras);
    const response = await post(API_ENDPOINTS.addKomentaras + "/" + props.value, komentaras);

    props.addKomentaras(response);
    setKomentaras(initialFormState)
  };

  return (
    <form onSubmit={createKomentaras}>
      <div className="formlaukas">
        <label>Jūsų vardas </label>
        <input type="text" name="autorius" value={komentaras.autorius} onChange={handleInputChange} />
      </div>
      <div className="formlaukas">
        <label>Jūsų komentaras</label>
        <textarea type="text" cols="70" rows="3" name="komentaras" value={komentaras.komentaras} onChange={handleInputChange} />
      </div>

      <button className="formlaukas">Pridėti komentarą</button>
    </form>
  )
}
export default Komentarai;
