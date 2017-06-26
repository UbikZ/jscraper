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