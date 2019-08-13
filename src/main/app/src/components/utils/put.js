import { API_DOMAIN } from "./constants";

export const put = (url, request) => {
  console.log("**********************is put");
  console.log(url);
  console.log(request);
 return fetch(`${API_DOMAIN}${url}`, {
   body: JSON.stringify(request),
   method: "PUT",
   headers: { Accept: "application/json", "Content-type": "application/json" }
 }).then(resp => resp.json());
};