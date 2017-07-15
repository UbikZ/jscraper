import {
  FETCHING_TAGS, RECEIVE_TAGS, RECEIVE_TAGS_ERROR,
  DELETE_TAG_REQUEST, DELETE_TAG_SUCCESS, DELETE_TAG_FAILURE
} from './action';

const initialState = {
  items: [],
  total: 0,
  offset: 0,
  limit: 20,
  isFetching: false,
  isDeleting: false
};

export default function itemReducer(state = initialState, action) {
  const {items, total, offset, isFetching, isDeleting, error} = action;
  switch (action.type) {
    case FETCHING_TAGS: {
      return {
        ...state,
        isFetching
      };
    }
    case RECEIVE_TAGS: {
      return {
        ...state,
        items,
        total,
        offset,
        isFetching
      };
    }
    case RECEIVE_TAGS_ERROR: {
      return {
        ...state,
        error
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
        isDeleting,
        error
      };
    }
    default:
      return state;
  }
}