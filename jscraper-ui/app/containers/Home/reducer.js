import {LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT_SUCCESS} from './action';

const initialState = {
  isAuthenticated: !!localStorage.getItem('token'),
  isFetching: false,
  token: localStorage.getItem('token')
};

export default function itemReducer(state = initialState, action) {
  const {isAuthenticated, isFetching, creds} = action;
  switch (action.type) {
    case LOGIN_REQUEST: {
      return {
        ...state,
        isAuthenticated,
        isFetching,
        user: creds
      };
    }
    case LOGIN_SUCCESS: {
      return {
        ...state,
        isAuthenticated,
        isFetching,
        token: action.token,
        roles: action.roles
      };
    }
    case LOGIN_FAILURE: {
      return {
        ...state,
        isAuthenticated,
        isFetching,
        errorMessage: action.message
      };
    }
    case LOGOUT_SUCCESS: {
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