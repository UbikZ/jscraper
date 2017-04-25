import React from 'react';
import {StaticRouter} from 'react-router';
import {renderToString} from 'react-dom/server';
import App from './containers/App';
import './styles';

window.render = (template, model) => {
  const context = {};
  const req = JSON.parse(model.get('req'));

  const markup = renderToString(
    <StaticRouter location={req.url} context={context}>
      <App/>
    </StaticRouter>
  );

  return template.replace('SERVER_RENDERED_HTML', markup);
};