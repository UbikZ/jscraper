/*global require, process*/
import React from 'react';
import {render} from 'react-dom';
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
import './styles';

const middlewares = [thunkMiddleware];
if (process.env.NODE_ENV !== 'production') {
  middlewares.push(require('redux-logger').createLogger());
}

const markup = (
  <Provider store={createStore(reducer, applyMiddleware(...middlewares))}>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </Provider>
);

render(markup, document.getElementById('root'));

if (process.env.NODE_ENV === 'production' && 'serviceWorker' in navigator) {
  navigator.serviceWorker.register('/sw.js')
    .then(reg => reg.onupdatefound = () => reg.installing.onstatechange = () => {
      switch (reg.installing.state) {
        case 'installed':
          if (navigator.serviceWorker.controller) {
            console.log('New or updated content is available.');
          } else {
            console.log('Content is now available offline!');
          }
          break;

        case 'redundant':
          console.error('The installing service worker became redundant.');
          break;
      }
    })
    .catch(error => {
      console.error('Error during service worker registration:', error);
    });
}