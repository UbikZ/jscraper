import moment from 'moment';
import {FETCHING_ITEMS, RECEIVE_ITEMS, RECEIVE_ITEMS_ERROR} from './action';

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

export default function itemReducer(state = initialState, action) {
  const {items, total, startDate, endDate, offset, tags, isFetching, approved, error} = action;
  switch (action.type) {
    case FETCHING_ITEMS: {
      return {
        ...state,
        isFetching
      };
    }
    case RECEIVE_ITEMS: {
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
    case RECEIVE_ITEMS_ERROR: {
      return {
        ...state,
        error
      };
    }
    default:
      return state;
  }
}