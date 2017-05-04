import moment from 'moment';
import {RECEIVE_ITEMS, RECEIVE_ITEMS_ERROR} from './action';

const initialState = {
  items: [],
  total: 0,
  offset: 0,
  limit: 10,
  tags: [],
  startDate: moment(),
  endDate: moment()
};

export default function itemReducer(state = initialState, action) {
  switch (action.type) {
    case RECEIVE_ITEMS: {
      return {
        ...state,
        items: action.items,
        total: action.total,
        startDate: action.startDate,
        endDate: action.endDate,
        offset: action.offset
      };
    }
    case RECEIVE_ITEMS_ERROR: {
      const {error} = action;
      return error;
    }
    default:
      return state;
  }
}