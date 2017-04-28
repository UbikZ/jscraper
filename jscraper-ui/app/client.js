/*global require, process*/
import React from 'react';
import {render} from 'react-dom';
import {BrowserRouter} from 'react-router-dom';
import {applyMiddleware, createStore} from 'redux';
import thunkMiddleware from 'redux-thunk';
import {Provider} from 'react-redux';
import {createLogger} from 'redux-logger';

import reducer from './reducers';
import App from './containers/App';

const loggerMiddleware = createLogger();
const store = createStore(reducer, window.__PRELOADED_STATE__, applyMiddleware(thunkMiddleware, loggerMiddleware));

const markup = (
  <Provider store={store}>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </Provider>
);

render(markup, document.getElementById('app'));