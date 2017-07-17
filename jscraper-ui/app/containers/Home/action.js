import {api} from '../../api/index';
import {toastr, TYPE_ERROR, TYPE_SUCCESS} from '../Toastr/action';

export const LOGIN_REQUEST = 'LOGIN_REQUEST';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';
export const LOGIN_FAILURE = 'LOGIN_FAILURE';

function loginRequest(creds) {
  return {
    type: LOGIN_REQUEST,
    isFetching: true,
    isAuthenticated: false,
    creds
  };
}

function loginSuccess(token, roles) {
  return {
    type: LOGIN_SUCCESS,
    isFetching: false,
    isAuthenticated: true,
    token,
    roles
  };
}

function loginFailure() {
  return {
    type: LOGIN_FAILURE,
    isFetching: false,
    isAuthenticated: false
  };
}

export function login(creds = {}) {
  const config = {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(creds)
  };

  return (dispatch, getState) => {
    dispatch(loginRequest(creds));
    return api('authenticate', getState(), config)
      .then(json => {
        const {token, roles} = json.data;
        dispatch(loginSuccess(token, roles.map(role => role.authority)));
        toastr(TYPE_SUCCESS, 'Authentication granted.')(dispatch, getState);
      })
      .catch(() => {
        dispatch(loginFailure());
        toastr(TYPE_ERROR, 'Wrong username or password.', 'Authentication failed')(dispatch, getState);
      });
  };
}