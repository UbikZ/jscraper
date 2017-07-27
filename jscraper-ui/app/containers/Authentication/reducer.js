import {SIGNIN_FAILURE, SIGNIN_REQUEST, SIGNIN_SUCCESS, SIGNOUT_SUCCESS} from './action';

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