import {combineReducers} from 'redux';
import feedItems from 'containers/FeedItems/reducer';
import authentication from 'containers/Home/reducer';

export default combineReducers({
  feedItems,
  authentication
});