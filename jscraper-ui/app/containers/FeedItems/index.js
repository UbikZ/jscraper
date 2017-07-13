import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {fetchFeedItems} from './action';
import Main from '../../components/Main';
import Filter from '../../components/FeedItem/Filter';
import List from '../../components/FeedItem/List';

class FeedItems extends Component {
  static propTypes = {
    fetchFeedItems: PropTypes.func.isRequired,
    isFetching: PropTypes.bool.isRequired
  };

  loadList = (filter = {}) => {
    this.props.fetchFeedItems(filter);
  };

  render() {
    const {isFetching} = this.props;

    return (
      <Main title={'Feed Items'} isFetching={isFetching}>
        <Filter loadList={this.loadList} />
        <List loadList={this.loadList} />
      </Main>
    );
  }
}

function mapStateToProps(state) {
  const {isFetching} = state.feedItems;
  return {isFetching};
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    fetchFeedItems
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FeedItems);