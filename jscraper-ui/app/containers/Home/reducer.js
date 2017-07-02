import {LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT_SUCCESS} from './action';

const initialState = {
  isAuthenticated: !!localStorage.getItem('id_token'),
  isFetching: false,
  id_token: null
};

export default function itemReducer(state = initialState, action) {
  switch (action.type) {
    case LOGIN_REQUEST: {
      return {
        ...state,
        isAuthenticated: true,
        isFetching: true,
        user: action.creds
      };
    }
    case LOGIN_SUCCESS: {
      return {
        ...state,
        isAuthenticated: true,
        isFetching: false
      };
    }
    case LOGIN_FAILURE: {
      return {
        ...state,
        isAuthenticated: false,
        isFetching: false,
        errorMessage: action.message
      };
    }
    case LOGOUT_SUCCESS: {
      return {
        ...state,
        isFetching: false,
        isAuthenticated: false
      };
    }
    default:
      return state;
  }
}