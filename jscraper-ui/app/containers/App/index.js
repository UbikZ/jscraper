import React, {Component} from 'react';
import {Route} from 'react-router-dom';

import Header from '../../components/Header';
import Home from '../../containers/Home';
import FeedItems from '../../containers/FeedItems';
import Footer from "../../components/Footer/index";

export default class App extends Component {
  render() {
    return (
      <div>
        <Header/>
        <div>
          <Route path='/' exact component={Home}/>
          <Route path='/feed-items' exact component={FeedItems}/>
        </div>
        <Footer/>
      </div>
    );
  }
}