export const TOASTR_SEND = 'TOASTR_SEND';
export const TOASTR_DISMISS = 'TOASTR_DISMISS';

export const TYPE_INFO = 'info';
export const TYPE_SUCCESS = 'success';
export const TYPE_WARNING = 'warning';
export const TYPE_ERROR = 'error';

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