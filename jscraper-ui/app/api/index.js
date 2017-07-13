import 'isomorphic-fetch';
import queryString from 'query-string';
import moment from 'moment';

const URL_PREFIX = `${process.env.API_HOST || ''}/api`;

export const api = (url, globalState, config = {}, additional = {}) => new Promise((resolve, reject) => {
  config.headers = {'Authorization': `Bearer ${globalState.authentication.token}`};

  return fetch(`${URL_PREFIX}/${url}`, config)
    .then(res => res.json())
    .then(json => {
      if (json.success) {
        resolve({...json, ...additional});
      } else {
        reject(json);
      }
    })
    .catch(err => console.error("Error :", err));
});

export const apiFetch = (url, globalState, stateName, args, forbiden = []) => {
  const finalQs = Object.assign({}, globalState[stateName], args);

  return api(`${url}?${parseQs(finalQs, forbiden)}`, globalState, {method: 'GET'}, {qs: finalQs});
};

function parseQs(qs, forbiden = []) {
  let result = {};

  Object
    .keys(qs)
    .filter(key => !~[undefined, null].indexOf(qs[key]) && !~forbiden.indexOf(key))
    .map(key => {
      const value = qs[key];

      if (value instanceof moment) {
        result[key] = value.format('YYYY-MM-DD');
      } else {
        result[key] = value;
      }
    });

  return queryString.stringify(result);
}