import {apiWrapper} from '../../utils/api';
import {toastr, TYPE_ERROR, TYPE_SUCCESS} from '../';

export const FETCH_TAGS_REQUEST = 'jscraper/tags/fetchRequest';
export const FETCH_TAGS_SUCCESS = 'jscraper/tags/fetchSuccess';
export const FETCH_TAGS_FAILURE = 'jscraper/tags/fetchFailure';
export const DELETE_TAG_REQUEST = 'jscraper/tags/deleteRequest';
export const DELETE_TAG_SUCCESS = 'jscraper/tags/deleteSuccess';
export const DELETE_TAG_FAILURE = 'jscraper/tags/deleteFailure';

const initialState = {
  items: [],
  total: 0,
  offset: 0,
  limit: 20,
  isFetching: false,
  isDeleting: false
};

export default function reducer(state = initialState, action) {
  const {items, total, offset, isFetching, isDeleting, error} = action;
  switch (action.type) {
    case FETCH_TAGS_REQUEST: {
      return {
        ...state,
        isFetching
      };
    }
    case FETCH_TAGS_SUCCESS: {
      return {
        ...state,
        items,
        total,
        offset,
        isFetching
      };
    }
    case FETCH_TAGS_FAILURE: {
      return {
        ...state,
        isFetching
      };
    }
    case DELETE_TAG_REQUEST: {
      return {
        ...state,
        isDeleting
      };
    }
    case DELETE_TAG_SUCCESS: {
      return {
        ...state,
        isDeleting
      };
    }
    case DELETE_TAG_FAILURE: {
      return {
        ...state,
        isDeleting
      };
    }
    default:
      return state;
  }
}

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
      'tag', getState(), 'tags',
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
      'tag/' + id, getState(), 'tags',
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