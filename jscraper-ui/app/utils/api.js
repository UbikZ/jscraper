import 'isomorphic-fetch';
import queryString from 'query-string';
import moment from 'moment';
import {signOut} from "../reducers/modules/authentication";
import {toastr, TYPE_ERROR} from "../reducers";

const URL_PREFIX = `${process.env.API_HOST || ''}/api`;

export const api = (url, globalState, config = {}, additional = {}) => new Promise((resolve, reject) => {
  config.headers = {'Authorization': `Bearer ${globalState.authentication.token}`};

  return fetch(`${URL_PREFIX}/${url}`, config)
    .then(res => {
      console.log(res);
      return res.json();
    })
    .then(json => {
      if (json.success) {
        resolve({...json, ...additional});
      } else {
        reject({...json, signOut: json.status === 500 && json.exception === 'io.jsonwebtoken.ExpiredJwtException'});
      }
    })
    .catch(err => {
      console.error("Internal Error :", err);
    });
});

export const handlesErrors = (dispatch, getState, data, action, message, title, cb) => {
  console.error(data);
  dispatch(action());

  if (data.signOut) {
    dispatch(signOut());
    title = 'Session expired.';
    message = 'You have been logged out. Please try to sign-in.';
  }

  toastr(TYPE_ERROR, message, title)(dispatch, getState);

  cb(dispatch, getState);
};

export const apiWrapper = (url, globalState, stateName, args, forbiden = [], config = {method: 'GET'}) => {
  const finalQs = Object.assign({}, globalState[stateName], args);

  return api(`${url}?${parseQs(finalQs, forbiden)}`, globalState, config, {qs: finalQs});
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