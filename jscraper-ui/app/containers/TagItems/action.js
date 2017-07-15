import {apiWrapper} from '../../api';

export const FETCH_TAGS_REQUEST = 'FETCH_TAGS_REQUEST';
export const FETCH_TAGS_SUCCESS = 'FETCH_TAGS_SUCCESS';
export const FETCH_TAGS_FAILURE = 'FETCH_TAGS_FAILURE';
export const DELETE_TAG_REQUEST = 'DELETE_TAG_REQUEST';
export const DELETE_TAG_SUCCESS = 'DELETE_TAG_SUCCESS';
export const DELETE_TAG_FAILURE = 'DELETE_TAG_FAILURE';

export function fetchTagsRequest() {
  return {
    type: FETCH_TAGS_REQUEST,
    isFetching: true
  };
}

export function fetchTagsSuccess(items, total, offset) {
  return {
    type: FETCH_TAGS_SUCCESS,
    items,
    total,
    offset,
    isFetching: false
  };
}

export function fetchTagsFailure(error) {
  return {
    type: FETCH_TAGS_FAILURE,
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

export function fetchTags(args = {}) {
  return (dispatch, getState) => {
    args.lazy = false;
    if (!args.label) {
      delete args.label;
    }

    dispatch(fetchTagsRequest());
    return apiWrapper('tag', getState(), 'tagItems', args, ['items', 'total'])
      .then(element => {
        const {data, total, qs} = element;
        const {offset} = qs;
        dispatch(fetchTagsSuccess(data, total, offset));
      })
      .catch(data => {
        dispatch(fetchTagsFailure(data.error));
      });
  };
}

export function deleteTag(id) {
  return (dispatch, getState) => {
    dispatch(deleteTagRequest());

    return apiWrapper('tag/' + id, getState(), 'tagItems', {id}, ['id', 'items', 'total', 'offset', 'limit', 'isDeleting', 'isFetching'], {method: 'DELETE'})
      .then(() => {
        dispatch(deleteTagSuccess());
        fetchTags()(dispatch, getState);
      })
      .catch(data => {
        dispatch(deleteTagFailure(data.error));
      });
  };
}