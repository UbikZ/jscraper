import React from 'react';
import {StaticRouter as Router} from 'react-router';
import serialize from 'serialize-javascript';
import {renderToString} from 'react-dom/server';

import App from './containers/App';
import './styles';

window.render = (template, model) => {
  const context = {};
  const req = JSON.parse(model.get('req'));
  const initialState = JSON.parse(model.get('initialState'));

  const markup = renderToString(
    <Router location={req.location} context={context}>
      <App/>
    </Router>
  );

  return template
    .replace('SERVER_RENDERED_HTML', markup)
    .replace('SERVER_RENDERED_STATE', serialize(initialState, {isJSON: true}));
};