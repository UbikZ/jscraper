import moment from 'moment';
import {FETCH_FEEDITEMS_REQUEST, FETCH_FEEDITEMS_SUCCESS, FETCH_FEEDITEMS_FAILURE} from './action';

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
    case FETCH_FEEDITEMS_REQUEST: {
      return {
        ...state,
        isFetching
      };
    }
    case FETCH_FEEDITEMS_SUCCESS: {
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
    case FETCH_FEEDITEMS_FAILURE: {
      return {
        ...state,
        error
      };
    }
    default:
      return state;
  }
}