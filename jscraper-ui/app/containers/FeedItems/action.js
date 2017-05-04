import apiFetch from '../../api';

export const RECEIVE_ITEMS = 'RECEIVE_ITEMS';
export const RECEIVE_ITEMS_ERROR = 'RECEIVE_ITEMS_ERROR';

export function receiveFeedItems(items, total, startDate, endDate, offset) {
  return {type: RECEIVE_ITEMS, items, total, startDate, endDate, offset};
}

export function receiveFeedItemsError(error) {
  return {type: RECEIVE_ITEMS_ERROR, error};
}

export function fetchFeedItems(args = {}) {
  return (dispatch, getState) => {
    args.lazy = false;

    return apiFetch('feed-item', getState().feedItems, args, ['items', 'total'])
      .then(element => {
        const {startDate, endDate, offset} = element.qs;
        const {data, total} = element.json;
        dispatch(receiveFeedItems(data, total, startDate, endDate, offset));
      })
      .catch(data => {
        dispatch(receiveFeedItemsError(data.error));
      });
  };
}