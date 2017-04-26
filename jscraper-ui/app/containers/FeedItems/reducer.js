import {RECEIVE_ITEMS, RECEIVE_ITEMS_ERROR} from './action';

const initialState = [];

export default function itemReducer(state = initialState, action) {
  switch (action.type) {
    case RECEIVE_ITEMS: {
      const {feedItems} = action;
      return feedItems;
    }
    case RECEIVE_ITEMS_ERROR: {
      const {error} = action;
      return error;
    }
    default:
      return state;
  }
}