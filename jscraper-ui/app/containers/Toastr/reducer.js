import {TOASTR_DISMISS, TOASTR_SEND} from './action';

const initialState = {
  notifs: []
};

export default function reducer(state = initialState, action) {
  const {id, tType, title, message} = action;
  switch (action.type) {
    case TOASTR_SEND: {
      return {
        ...state,
        notifs: [...state.notifs, {id, tType, title, message}]
      };
    }
    case TOASTR_DISMISS: {
      return {
        ...state,
        notifs: state.notifs.filter(({id}) => id !== action.id)
      };
    }
    default:
      return state;
  }
}