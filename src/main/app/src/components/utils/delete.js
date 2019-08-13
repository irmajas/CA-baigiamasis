import { API_DOMAIN } from "./constants";


export const delrest = async(url) => {
  console.log("**********************is delete");
  console.log(url);
  return fetch(`${API_DOMAIN}${url}`, {
   method: "DELETE",
   headers: { Accept: "application/json", "Content-type": "application/json" }
 })

};