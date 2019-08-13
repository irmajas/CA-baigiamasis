import { API_DOMAIN } from "./constants";

export const post = (url, request) => {
  console.log("**********************is post");
  console.log(url);
  console.log(request);
 return fetch(`${API_DOMAIN}${url}`, {
   body: JSON.stringify(request),
   method: "POST",
   headers: { Accept: "application/json", "Content-type": "application/json" }
 }).then(resp => resp.json());
};