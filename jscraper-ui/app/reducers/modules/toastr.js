export const TOASTR_SEND = 'jscraper/toastr/send';
export const TOASTR_DISMISS = 'jscraper/toastr/dismiss';

export const TYPE_INFO = 'info';
export const TYPE_SUCCESS = 'success';
export const TYPE_WARNING = 'warning';
export const TYPE_ERROR = 'error';

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

export function toastrSend(id, tType, title, message) {
  return {
    type: TOASTR_SEND,
    id,
    tType,
    title,
    message
  };
}

export function toastrDismiss(id) {
  return {
    type: TOASTR_DISMISS,
    id
  };
}

export function toastr(type, message, title = undefined, dismissTimeout = 3000) {
  const id = new Date().getTime();

  return dispatch => {
    dispatch(toastrSend(id, type, title, message));

    setTimeout(() => dispatch(toastrDismiss(id)), dismissTimeout);
  };
}