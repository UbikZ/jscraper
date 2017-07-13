import React, {Component} from 'react';
import {Redirect, Route} from 'react-router-dom';

import Home from '../../containers/Home';
import FeedItems from '../../containers/FeedItems';

import Footer from "../../components/Footer";
import Header from '../../components/Header';
import CheckAuthentication from '../../components/Main/CheckAuthentication';

export default class App extends Component {
  render() {
    return (
      <div>
        <Header/>
        <div>
          <Redirect from='/' to='/'/>
          <Route exact path='/' component={Home}/>

          <CheckAuthentication role="ROLE_USER">
            <Route exact path='/feed-items' component={FeedItems}/>
          </CheckAuthentication>
        </div>
        <Footer/>
      </div>
    );
  }
}