import React, {Component} from 'react';
import styled from 'styled-components';
import {Route} from 'react-router-dom';

import Header from '../../components/Header';
import Home from '../../containers/Home';
import FeedItems from '../../containers/FeedItems';
import '../../styles';

const Container = styled.div`
  padding: 6rem 0;
  border-top-width: 0;
  border-top: 1px solid #eee;
  padding: 10rem 0;
  margin-bottom: 0;
`;

export default class App extends Component {
  render() {
    return (
      <section className="container">
        <Header/>
        <Container>
          <Route path="/" exact component={Home} />
          <Route path="/feed-items" exact component={FeedItems} />
        </Container>
      </section>
    );
  }
}