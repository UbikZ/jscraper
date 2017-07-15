import {apiWrapper} from '../../api';

export const FETCHING_TAGS = 'FETCHING_TAGS';
export const RECEIVE_TAGS = 'RECEIVE_TAGS';
export const RECEIVE_TAGS_ERROR = 'RECEIVE_TAGS_ERROR';
export const DELETE_TAG_REQUEST = 'DELETE_TAG_REQUEST';
export const DELETE_TAG_SUCCESS = 'DELETE_TAG_SUCCESS';
export const DELETE_TAG_FAILURE = 'DELETE_TAG_FAILURE';

export function fetchingTagItems() {
  return {
    type: FETCHING_TAGS,
    isFetching: true
  };
}

export function receiveTagItems(items, total, offset) {
  return {
    type: RECEIVE_TAGS,
    items,
    total,
    offset,
    isFetching: false
  };
}

export function receiveTagItemsError(error) {
  return {
    type: RECEIVE_TAGS_ERROR,
    error
  };
}

function deleteTagRequest() {
  return {
    type: DELETE_TAG_REQUEST,
    isDeleting: true
  };
}

function deleteTagSuccess() {
  return {
    type: DELETE_TAG_SUCCESS,
    isDeleting: false
  };
}

function deleteTagFailure(message) {
  return {
    type: DELETE_TAG_FAILURE,
    isDeleting: false,
    message
  };
}

export function fetchTagItems(args = {}) {
  return (dispatch, getState) => {
    args.lazy = false;
    if (!args.label) {
      delete args.label;
    }

    dispatch(fetchingTagItems());
    return apiWrapper('tag', getState(), 'tagItems', args, ['items', 'total'])
      .then(element => {
        const {data, total, qs} = element;
        const {offset} = qs;
        dispatch(receiveTagItems(data, total, offset));
      })
      .catch(data => {
        dispatch(receiveTagItemsError(data.error));
      });
  };
}

export function deleteTag(id) {
  return (dispatch, getState) => {
    dispatch(deleteTagRequest());

    return apiWrapper('tag/' + id, getState(), 'tagItems', {id}, ['id', 'items', 'total', 'offset', 'limit', 'isDeleting', 'isFetching'], {method: 'DELETE'})
      .then(() => {
        dispatch(deleteTagSuccess());
        fetchTagItems()(dispatch, getState);
      })
      .catch(data => {
        dispatch(deleteTagFailure(data.error));
      });
  };
}