import moment from 'moment';
import {PENDING_ITEMS, RECEIVE_ITEMS, RECEIVE_ITEMS_ERROR} from './action';

const initialState = {
  items: [],
  total: 0,
  offset: 0,
  limit: 20,
  approved: true,
  pending: false,
  tags: [],
  startDate: moment(),
  endDate: moment()
};

export default function itemReducer(state = initialState, action) {
  switch (action.type) {
    case PENDING_ITEMS: {
      return {
        ...state,
        pending: action.pending
      };
    }
    case RECEIVE_ITEMS: {
      return {
        ...state,
        items: action.items,
        total: action.total,
        startDate: action.startDate,
        endDate: action.endDate,
        offset: action.offset,
        tags: action.tags,
        pending: action.pending,
        approved: action.approved
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