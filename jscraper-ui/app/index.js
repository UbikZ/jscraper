/*global require, process*/

import React from 'react';
import {render} from 'react-dom';
import {BrowserRouter} from 'react-router-dom';
import {Provider} from 'react-redux';

import {App} from './containers';
import createStore from './reducers/create';

import 'normalize.css/normalize.css';
import 'spectre.css/dist/spectre.min.css';
import 'spectre.css/dist/spectre-icons.min.css';
import 'spectre.css/dist/spectre-exp.min.css';
import 'react-datepicker/dist/react-datepicker.min.css';
import 'spinkit/css/spinkit.css';
import './theme';

const markup = (
  <Provider store={createStore()}>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </Provider>
);

render(markup, document.getElementById('root'));