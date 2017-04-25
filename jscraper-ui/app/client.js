/*global require, process*/
import React from 'react';
import {render} from 'react-dom';
import {BrowserRouter} from 'react-router-dom';
import App from './containers/App';
import './styles';

const markup = (
  <BrowserRouter>
    <App/>
  </BrowserRouter>
);

render(markup, document.getElementById('app'));