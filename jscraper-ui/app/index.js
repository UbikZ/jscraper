/*global require, process*/
import React from 'react';
import {render} from 'react-dom';
import {BrowserRouter} from 'react-router-dom';
import {applyMiddleware, createStore, compose} from 'redux';
import thunkMiddleware from 'redux-thunk';
import {Provider} from 'react-redux';

import reducer from './reducers';
import App from './containers/App';

import 'normalize.css/normalize.css';
import 'spectre.css/dist/spectre.css';
import 'spectre.css/dist/spectre-icons.css';
import 'spectre.css/dist/spectre-exp.css';
import 'react-datepicker/dist/react-datepicker.css';
import 'spinkit/css/spinkit.css';
import './styles';

const middlewares = [thunkMiddleware];
let composeEnhancers = compose;
if (process.env.NODE_ENV !== 'production') {
  composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || composeEnhancers;
}

const markup = (
  <Provider store={createStore(reducer, composeEnhancers(applyMiddleware(...middlewares)))}>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </Provider>
);

render(markup, document.getElementById('root'));