/*global require, process*/
import React from 'react';
import {render} from 'react-dom';
import * as OfflinePluginRuntime from 'offline-plugin/runtime';
import {BrowserRouter} from 'react-router-dom';
import {applyMiddleware, createStore} from 'redux';
import thunkMiddleware from 'redux-thunk';
import {Provider} from 'react-redux';

import reducer from './reducers';
import App from './containers/App';

import 'normalize.css/normalize.css';
import 'spectre.css/dist/spectre.css';
import 'spectre.css/dist/spectre-icons.css';
import 'spectre.css/dist/spectre-exp.css';
import 'react-datepicker/dist/react-datepicker.css';
import './style/spectre-custom.css';

const store = process.env.NODE_ENV === 'production'
    ? createStore(reducer, applyMiddleware(thunkMiddleware))
    : createStore(reducer, applyMiddleware(thunkMiddleware, require('redux-logger').createLogger()));

const markup = (
  <Provider store={store}>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </Provider>
);

render(markup, document.getElementById('root'));

if (process.env.NODE_ENV === 'production') {
  OfflinePluginRuntime.install();
}