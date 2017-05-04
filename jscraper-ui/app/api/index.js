import 'isomorphic-fetch';
import queryString from 'query-string';
import moment from 'moment';

const URL_PREFIX = '/api';

export default function apiFetch(url, state, args, forbiden = []) {
  const finalQs = Object.assign({}, state, args);

  return new Promise((resolve, reject) => {
    fetch(`${URL_PREFIX}/${url}?${parseQs(finalQs, forbiden)}`)
      .then(res => res.json())
      .then(json => {
        if (!json.success) {
          throw new Error(json.data);
        }
        resolve({json, qs: finalQs});
      })
      .catch(error => {
        reject({error, qs: finalQs});
      });
  });
}

function parseQs(qs, forbiden = []) {
  let result = {};

  Object
    .keys(qs)
    .filter(key => !!qs[key] && !~forbiden.indexOf(key))
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