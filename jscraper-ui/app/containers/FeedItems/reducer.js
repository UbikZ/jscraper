import {RECEIVE_ITEMS, RECEIVE_ITEMS_ERROR} from './action';

export default function itemReducer(state = {}, action) {
  switch (action.type) {
    case RECEIVE_ITEMS: {
      return {
        ...state,
        items: action.items,
        total: action.total,
        startDate: action.startDate,
        endDate: action.endDate,
        offset: action.offset,
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