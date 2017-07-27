import {apiWrapper} from '../../api';
import {handlesErrors} from "../../api/index";

export const FETCH_FEEDITEMS_REQUEST = 'FETCH_FEEDITEMS_REQUEST';
export const FETCH_FEEDITEMS_SUCCESS = 'FETCH_FEEDITEMS_SUCCESS';
export const FETCH_FEEDITEMS_FAILURE = 'FETCH_FEEDITEMS_FAILURE';

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