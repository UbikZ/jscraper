/*global require, process*/
import React from 'react';
import {render} from 'react-dom';
import {BrowserRouter as Router} from 'react-router-dom';
import App from './containers/App';

import './styles';

const markup = (
  <Router>
    <App/>
  </Router>
);

render(markup, document.getElementById('app'));