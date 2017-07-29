/*global require, process*/

import {applyMiddleware, compose, createStore as _createStore} from 'redux';
import thunk from 'redux-thunk';

import reducer from './reducer';

export default function createStore() {
  let composeEnhancers = compose;
  const middlewares = [thunk];

  if (process.env.NODE_ENV !== 'production') {
    composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || composeEnhancers;
  }

  return _createStore(reducer, composeEnhancers(applyMiddleware(...middlewares)));
}
