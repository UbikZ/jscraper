import React, {Component} from 'react';
import {Match} from 'react-router';
import { Link, Route } from 'react-router-dom';

import Header from '../../components/Header';
import Home from '../../containers/Home';
import Test from '../../containers/Test';



export default class App extends Component {
  render() {
    return (
      <div className="container">
        <Header/>
        <Route path="/" exact component={Home} />
        <Route path="/test" exact component={Test} />
      </div>
    );
  }
}