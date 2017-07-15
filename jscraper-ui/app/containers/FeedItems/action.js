import {apiWrapper} from '../../api';

export const FETCHING_ITEMS = 'FETCHING_ITEMS';
export const RECEIVE_ITEMS = 'RECEIVE_ITEMS';
export const RECEIVE_ITEMS_ERROR = 'RECEIVE_ITEMS_ERROR';

export function fetchingFeedItems() {
  return {
    type: FETCHING_ITEMS,
    isFetching: true
  };
}

export function receiveFeedItems(items, total, startDate, endDate, offset, approved, tags) {
  return {
    type: RECEIVE_ITEMS,
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

export function receiveFeedItemsError(error) {
  return {
    type: RECEIVE_ITEMS_ERROR,
    error
  };
}

export function fetchFeedItems(args = {}) {
  return (dispatch, getState) => {
    args.lazy = false;

    dispatch(fetchingFeedItems());
    return apiWrapper('feed-item', getState(), 'feedItems', args, ['items', 'total'])
      .then(element => {
        const {data, total, qs} = element;
        const {startDate, endDate, offset, approved, tags} = qs;
        dispatch(receiveFeedItems(data, total, startDate, endDate, offset, approved, tags));
      })
      .catch(data => {
        dispatch(receiveFeedItemsError(data.error));
      });
  };
}