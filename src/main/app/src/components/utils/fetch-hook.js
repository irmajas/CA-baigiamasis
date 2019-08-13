import {useEffect, useState } from "react";
import {API_DOMAIN} from "./constants";

const useFetch = url => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  //  eslint-disable-next-line react-hooks/exhaustive-deps
  async function fetchData() {
    try {
      const response = await fetch(`${API_DOMAIN}${url}`);
   
      const json = await response.json();
    
      if (!response.ok) {
        throw Error(response.statusText);
      } else {
        setData(json);
        setLoading(false);
      }
    } catch (error) {
      console.log(error);
    }
  }
 
  useEffect(() => {
  
    fetchData();
// eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return {loading, data, setData};
};

export {useFetch};