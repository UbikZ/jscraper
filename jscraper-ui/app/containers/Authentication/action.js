import {api} from '../../api/index';
import {toastr, TYPE_ERROR, TYPE_SUCCESS} from '../Toastr/action';

export const SIGNIN_REQUEST = 'SIGNIN_REQUEST';
export const SIGNIN_SUCCESS = 'SIGNIN_SUCCESS';
export const SIGNIN_FAILURE = 'SIGNIN_FAILURE';
export const SIGNOUT_REQUEST = 'SIGNOUT_REQUEST';
export const SIGNOUT_SUCCESS = 'SIGNOUT_SUCCESS';
export const SIGNOUT_FAILURE = 'SIGNOUT_FAILURE';


function signInRequest(creds) {
  return {
    type: SIGNIN_REQUEST,
    isFetching: true,
    isAuthenticated: false,
    creds
  };
}

function signInSuccess(token, roles) {
  return {
    type: SIGNIN_SUCCESS,
    isFetching: false,
    isAuthenticated: true,
    token,
    roles
  };
}

function signInFailure() {
  return {
    type: SIGNIN_FAILURE,
    isFetching: false,
    isAuthenticated: false
  };
}

function signOutRequest() {
  return {
    type: SIGNOUT_REQUEST,
    isAuthenticated: true
  };
}

function signOutSuccess() {
  return {
    type: SIGNOUT_SUCCESS,
    isAuthenticated: false
  };
}

function signOutFailure() {
  return {
    type: SIGNOUT_FAILURE,
    isAuthenticated: true
  };
}

export function signOut() {
  return (dispatch, getState) => {
    dispatch(signOutRequest());

    localStorage.removeItem('token');
    localStorage.removeItem('roles');

    if (localStorage.getItem('token') || localStorage.getItem('roles')) {
      dispatch(signOutFailure());
      toastr(TYPE_ERROR, 'Cannot remove token from the service.', 'Logout is impossible')(dispatch, getState);
    } else {
      dispatch(signOutSuccess());
    }
  };
}

export function signIn(creds = {}) {
  const config = {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(creds)
  };

  return (dispatch, getState) => {
    dispatch(signInRequest(creds));
    return api('authenticate', getState(), config)
      .then(json => {
        const {token, roles} = json.data;
        const roleList = roles.map(role => role.authority);

        localStorage.setItem('token', token);
        localStorage.setItem('roles', roleList);

        dispatch(signInSuccess(token, roleList));
        toastr(TYPE_SUCCESS, 'Authentication granted.')(dispatch, getState);
      })
      .catch(data => {
        console.error(data);
        dispatch(signInFailure());
        toastr(TYPE_ERROR, 'Wrong username or password.', 'Authentication failed')(dispatch, getState);
      });
  };
}