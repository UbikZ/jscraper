import {combineReducers} from 'redux';
import feedItems from './modules/feedItems';
import tags from './modules/tags';
import authentication from './modules/authentication';
import toastr from './modules/toastr';

export default combineReducers({
  feedItems,
  authentication,
  tags,
  toastr
});