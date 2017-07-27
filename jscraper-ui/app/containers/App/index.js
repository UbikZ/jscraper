import React, {Component} from 'react';
import {Redirect, Route} from 'react-router-dom';

import Home from '../../containers/Home';
import Authentication from '../../containers/Authentication';
import FeedItems from '../../containers/FeedItems';
import TagItems from '../../containers/TagItems';
import Toastr from '../../containers/Toastr';

import Footer from "../../components/Footer";
import Header from '../../components/Header';
import CheckAuthentication from '../../components/Auth/Check';

export default class App extends Component {
  render() {
    return (
      <div>
        <Header/>
        <Toastr/>
        <div>
          <Redirect from='/' to='/'/>
          <Route exact path='/' component={Home}/>
          <CheckAuthentication role="ROLE_USER" reverse={true}>
            <Route exact path='/authentication' component={Authentication}/>
          </CheckAuthentication>
          <CheckAuthentication role="ROLE_USER">
            <Route exact path='/feed-items' component={FeedItems}/>
          </CheckAuthentication>
          <CheckAuthentication role="ROLE_ADMIN">
            <Route exact path='/tags' component={TagItems}/>
          </CheckAuthentication>
        </div>
        <Footer/>
      </div>
    );
  }
}