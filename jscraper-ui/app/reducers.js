import {combineReducers} from 'redux';
import feedItems from 'containers/FeedItems/reducer';
import tagItems from 'containers/TagItems/reducer';
import authentication from 'containers/Home/reducer';
import toastr from 'containers/Toastr/reducer';

export default combineReducers({
  feedItems,
  authentication,
  tagItems,
  toastr
});