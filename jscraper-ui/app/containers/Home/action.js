import {api} from '../../api/index';

export const LOGIN_REQUEST = 'LOGIN_REQUEST';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_FAILURE = 'LOGIN_FAILURE';

function requestLogin(creds) {
  return {
    type: LOGIN_REQUEST,
    isFetching: true,
    isAuthenticated: false,
    creds
  };
}

function receiveLogin(token, roles) {
  return {
    type: LOGIN_SUCCESS,
    isFetching: false,
    isAuthenticated: true,
    token,
    roles
  };
}

function loginError(message) {
  return {
    type: LOGIN_FAILURE,
    isFetching: false,
    isAuthenticated: false,
    message
  };
}

export function loginUser(creds = {}) {
  const config = {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(creds)
  };

  return (dispatch, getState) => {
    dispatch(requestLogin(creds));
    return api('authenticate', getState(), config)
      .then(json => {
        const {token, roles} = json.data;
        dispatch(receiveLogin(token, roles.map(role => role.authority)));
      })
      .catch(json => {
        dispatch(loginError(json.error));
      });
  };
}