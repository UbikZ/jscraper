import React from 'react';
import {StaticRouter} from 'react-router';
import {renderToString} from 'react-dom/server';
import {createStore, applyMiddleware} from 'redux';
import {Provider} from 'react-redux';
import serialize from 'serialize-javascript';
import thunkMiddleware from 'redux-thunk';
import { createLogger } from 'redux-logger';

import App from './containers/App';
import reducer from './reducers';

window.render = (template, model) => {
  const context = {};
  const req = JSON.parse(model.get('req'));
  const initialState = JSON.parse(model.get('initialState'));

  const loggerMiddleware = createLogger();
  const store = createStore(reducer, initialState, applyMiddleware(thunkMiddleware, loggerMiddleware));

  const markup = renderToString(
    <Provider store={store}>
      <StaticRouter location={req.url} context={context}>
        <App/>
      </StaticRouter>
    </Provider>
  );

  return template
    .replace('SERVER_RENDERED_HTML', markup)
    .replace('SERVER_RENDERED_STATE', serialize(initialState, {isJSON: true}));
};