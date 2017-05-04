import 'isomorphic-fetch';
import queryString from 'query-string';
import moment from 'moment';

export const RECEIVE_ITEMS = 'RECEIVE_ITEMS';
export const RECEIVE_ITEMS_ERROR = 'RECEIVE_ITEMS_ERROR';

export function receiveFeedItems(items, total, startDate, endDate, offset) {
  return {type: RECEIVE_ITEMS, items, total, startDate, endDate, offset};
}

export function receiveFeedItemsError(error) {
  return {type: RECEIVE_ITEMS_ERROR, error};
}

export function fetchFeedItems(qs = {}) {
  return (dispatch, getState) => {
    let finalQs = Object.assign({}, getState().feedItems, qs);
    const {startDate, endDate, offset} = finalQs;

    return fetch(`/api/feed-item?${queryString.stringify(parseQueryString(finalQs))}`)
      .then(response => response.json())
      .then(json => {
        if (!json.success) {
          throw new Error(json.data);
        }
        dispatch(receiveFeedItems(json.data, json.total, startDate, endDate, offset));
      })
      .catch(error => {
        dispatch(receiveFeedItemsError(error));
      });
  };
}

function parseQueryString(qs) {
  let result = {};
  const forbiden = ['items', 'total'];

  console.log("QS => ", qs);

  Object
    .keys(qs)
    .filter(key => !!qs[key] && !~forbiden.indexOf(key))
    .map(key => {
      const value = qs[key];

      if (value instanceof moment) {
        result[key] = value.format('YYYY-MM-DD');
      } else {
        result[key] = value;
      }
    });

  console.log("Result -> ", qs);

  return result;
}