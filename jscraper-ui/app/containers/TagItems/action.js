import {apiWrapper} from '../../api';
import {toastr, TYPE_ERROR, TYPE_SUCCESS} from "../Toastr/action";

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

export function fetchTagsFailure() {
  return {
    type: FETCH_TAGS_FAILURE,
    isFetching: false
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

function deleteTagFailure() {
  return {
    type: DELETE_TAG_FAILURE,
    isDeleting: false
  };
}

export function fetchTags(args = {}) {
  return (dispatch, getState) => {
    args.lazy = false;
    if (!args.label) {
      delete args.label;
    }

    dispatch(fetchTagsRequest());
    return apiWrapper(
      'tag', getState(), 'tagItems',
      args,
      ['items', 'total']
    ).then(element => {
      const {data, total, qs} = element;
      const {offset} = qs;
      dispatch(fetchTagsSuccess(data, total, offset));
    }).catch(data => {
      console.error(data);
      dispatch(fetchTagsFailure());
      toastr(TYPE_ERROR, 'Cannot fetch tags data right now.', 'Fetch data failed')(dispatch, getState);
    });
  };
}

export function deleteTag(id) {
  return (dispatch, getState) => {
    dispatch(deleteTagRequest());

    return apiWrapper(
      'tag/' + id, getState(), 'tagItems',
      {id},
      ['id', 'items', 'total', 'offset', 'limit', 'isDeleting', 'isFetching'],
      {method: 'DELETE'}
    ).then(() => {
      dispatch(deleteTagSuccess());
      fetchTags()(dispatch, getState);
      toastr(TYPE_SUCCESS, `Tag '${id}' has been deleted.`)(dispatch, getState);
    }).catch(data => {
      console.error(data);
      dispatch(deleteTagFailure());
      toastr(TYPE_ERROR, `Cannot delete tag '${id}'.`, 'Delete failed')(dispatch, getState);
    });
  };
}