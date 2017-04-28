import 'isomorphic-fetch';
import queryString from 'query-string';

export const RECEIVE_ITEMS = 'RECEIVE_ITEMS';
export const RECEIVE_ITEMS_ERROR = 'RECEIVE_ITEMS_ERROR';

export function receiveFeedItems(feedItems) {
  return {type: RECEIVE_ITEMS, feedItems};
}

export function receiveFeedItemsError(error) {
  return {type: RECEIVE_ITEMS_ERROR, error};
}

export function fetchFeedItems(qs = {}) {
  return (dispatch) => {
    return fetch(`/api/feed-item?${queryString.stringify(qs)}`)
      .then(response => response.json())
      .then(json => {
        if (!json.success) {
          throw new Error(json.data);
        }
        dispatch(receiveFeedItems(json.data));
      })
      .catch(error => {
        dispatch(receiveFeedItemsError(error));
      });
  };
}