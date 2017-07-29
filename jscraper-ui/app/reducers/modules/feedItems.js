import moment from 'moment';

import {apiWrapper, handlesErrors} from '../../utils/api';

export const FETCH_FEEDITEMS_REQUEST = 'jscraper/feedItems/fetchRequest';
export const FETCH_FEEDITEMS_SUCCESS = 'jscraper/feedItems/fetchSuccess';
export const FETCH_FEEDITEMS_FAILURE = 'jscraper/feedItems/fetchFailure';

const initialState = {
  items: [],
  total: 0,
  offset: 0,
  limit: 20,
  approved: true,
  isFetching: false,
  tags: [],
  startDate: moment(),
  endDate: moment()
};

export default function reducer(state = initialState, action) {
  const {items, total, startDate, endDate, offset, tags, isFetching, approved, error} = action;
  switch (action.type) {
    case FETCH_FEEDITEMS_REQUEST: {
      return {
        ...state,
        isFetching
      };
    }
    case FETCH_FEEDITEMS_SUCCESS: {
      return {
        ...state,
        items,
        total,
        startDate,
        endDate,
        offset,
        tags,
        isFetching,
        approved
      };
    }
    case FETCH_FEEDITEMS_FAILURE: {
      return {
        ...state,
        isFetching
      };
    }
    default:
      return state;
  }
}


export function fetchFeedItemsRequest() {
  return {
    type: FETCH_FEEDITEMS_REQUEST,
    isFetching: true
  };
}

export function fetchFeedItemsSuccess(items, total, startDate, endDate, offset, approved, tags) {
  return {
    type: FETCH_FEEDITEMS_SUCCESS,
    items,
    total,
    startDate,
    endDate,
    offset,
    approved,
    tags,
    isFetching: false
  };
}

export function fetchFeedItemsFailure() {
  return {
    type: FETCH_FEEDITEMS_FAILURE,
    isFetching: false
  };
}

export function fetchFeedItems(args = {}) {
  return (dispatch, getState) => {
    args.lazy = false;

    dispatch(fetchFeedItemsRequest());
    return apiWrapper('feed-item', getState(), 'feedItems', args, ['items', 'total'])
      .then(element => {
        const {data, total, qs} = element;
        const {startDate, endDate, offset, approved, tags} = qs;
        dispatch(fetchFeedItemsSuccess(data, total, startDate, endDate, offset, approved, tags));
      })
      .catch(data => handlesErrors(
        dispatch,
        getState,
        data,
        fetchFeedItemsFailure,
        'Cannot fetch feed items data right now.',
        'Fetch data failed'
      ));
  };
}