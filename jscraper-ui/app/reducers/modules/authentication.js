import {api} from '../../utils/api';
import {toastr, TYPE_ERROR, TYPE_SUCCESS} from '../';

export const SIGNIN_REQUEST = 'jscraper/authentication/signInRequest';
export const SIGNIN_SUCCESS = 'jscraper/authentication/signInSuccess';
export const SIGNIN_FAILURE = 'jscraper/authentication/signInFailure';
export const SIGNOUT_REQUEST = 'jscraper/authentication/signOutRequest';
export const SIGNOUT_SUCCESS = 'jscraper/authentication/signOutSuccess';
export const SIGNOUT_FAILURE = 'jscraper/authentication/signOutFailure';

const lsToken = localStorage.getItem('token') || '';
const lsRoles = localStorage.getItem('roles') || '';

const initialState = {
  isAuthenticated: !!lsToken,
  isFetching: false,
  token: lsToken,
  roles: lsRoles.split(',')
};

export default function reducer(state = initialState, action) {
  const {isAuthenticated, isFetching, creds} = action;
  switch (action.type) {
    case SIGNIN_REQUEST: {
      return {
        ...state,
        isAuthenticated,
        isFetching,
        user: creds
      };
    }
    case SIGNIN_SUCCESS: {
      return {
        ...state,
        isAuthenticated,
        isFetching,
        token: action.token,
        roles: action.roles
      };
    }
    case SIGNIN_FAILURE: {
      return {
        ...state,
        isAuthenticated,
        isFetching
      };
    }
    case SIGNOUT_SUCCESS: {
      return {
        ...state,
        isAuthenticated,
        isFetching
      };
    }
    default:
      return state;
  }
}

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