import React from 'react';
import './index.css';
import { API_ENDPOINTS } from "../utils/constants";
import { useEffect, useState } from "react";
import { API_DOMAIN } from "../utils/constants";
import { async } from 'q';
import KomentaruAts from '../KomentaruAts';
import { put } from "../utils/put";
import { delrest } from "../utils/delete";

function IrasasKitas(props) {

  const irasid=props.value;

  const url=API_ENDPOINTS.vienasirasas+"/"+irasid;

  const [pavadinimas, setPavadinimas] = useState('pavadinibbbbbmas');
  const [tekstas, setTekstas] = useState('tekstas');
  const [irasodata, setIrasoata] = useState('2019-01-01');

  useEffect(() => {
      const updateInfo = async () => {
          const data = await fetch(`${API_DOMAIN}${url}`)
              .then(result => result.json())

        setPavadinimas(data.pavadinimas);

          setTekstas(data.tekstas);
          setIrasoata(data.irasodata);
                  };
      updateInfo();
  }, []);

  console.log();
  return (
   <div className="Irasas--visas">
       <h1 className="Irasas--Pavadinimas">{pavadinimas}</h1>
       <p className="Irasas--data">{irasodata}</p>
       <hr className="Irasas--separator" />
       <p className="Irasas--Tekstas">{tekstas}</p>
       <EditIrasasForm  pavadinimas={pavadinimas} value={irasid}/>
       <KomentaruAts value={irasid}/>
   </div>
  )
}

function EditIrasasForm(props) {
  const [pav, setPav] = useState(props.pavadinimas);
  const initialFormState = { pavadinimas: props.pavadinimas, tekstas: 'Default tekstas' };

  const [irasas, setIrasas] = useState(initialFormState);

   const handleInputChange = event => {
    const {name, value} = event.target;

 setIrasas({...irasas, [name]: value})
 };

 const updateIrasas = async event => {

  event.preventDefault();
  if (!irasas.pavadinimas ||!irasas.tekstas) return;

const response = await put(API_ENDPOINTS.updateIrasas+"/"+props.value, irasas);

   // Atsakymas(response);
 //  setIrasas(initialFormState)
 };
  // console.log(props.pavadinimas);

  return (
    <form onSubmit={updateIrasas}>
      <div className="formlaukas">
        <label>Pavadinimas</label>

        <textarea type="text" cols="80" rows="3" name="pavadinimas" value={irasas.pavadinimas} defaultValue={initialFormState.pavadinimas} onChange={handleInputChange}/>
      </div>
      <div className="formlaukas">
        <label>Tekstas</label>
        <textarea type="text" cols="80" rows="35" name="tekstas" value={irasas.tekstas} defaultValue={initialFormState.tekstas} onChange={handleInputChange}/>
      </div>
      <button className="formlaukas">Įrašyti</button>

    </form>
  )
}
export default IrasasKitas;
