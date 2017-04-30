import 'isomorphic-fetch';
import queryString from 'query-string';
import moment from 'moment';

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
    return fetch(`/api/feed-item?${queryString.stringify(parseQueryString(qs))}`)
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

function parseQueryString(queryString) {
  const qs = Object.assign({}, queryString);
  Object.keys(qs).map((objectKey) => {
    const value = qs[objectKey];

    if (value instanceof moment) {
      qs[objectKey] = value.format('YYYY-MM-DD');
    }
  });

  return qs;
}