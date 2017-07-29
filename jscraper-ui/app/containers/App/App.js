import React, {Component} from 'react';
import {Redirect, Route} from 'react-router-dom';

import {Authentication, FeedItems, Home, Tags, Toastr} from './../../containers';
import {AuthCheck, PartialFooter, PartialHeader} from './../../components';

export default class App extends Component {
  render() {
    return (
      <div>
        <PartialHeader/>
        <Toastr/>
        <div>
          <Redirect from='/' to='/'/>
          <Route exact path='/' component={Home}/>
          <AuthCheck role='ROLE_USER' reverse={true}>
            <Route exact path='/authentication' component={Authentication}/>
          </AuthCheck>
          <AuthCheck role='ROLE_USER'>
            <Route exact path='/feed-items' component={FeedItems}/>
          </AuthCheck>
          <AuthCheck role='ROLE_ADMIN'>
            <Route exact path='/tags' component={Tags}/>
          </AuthCheck>
        </div>
        <PartialFooter/>
      </div>
    );
  }
}