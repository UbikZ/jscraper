import 'isomorphic-fetch';

export const RECEIVE_ITEMS = 'RECEIVE_ITEMS';
export const RECEIVE_ITEMS_ERROR = 'RECEIVE_ITEMS_ERROR';

export function receiveFeedItems(feedItems) {
  return {type: RECEIVE_ITEMS, feedItems};
}

export function receiveFeedItemsError(error) {
  return {type: RECEIVE_ITEMS_ERROR, error};
}

export function fetchFeedItems() {
  return (dispatch) => {
    return fetch('/api/feed-item?start-date=2017-04-26')
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