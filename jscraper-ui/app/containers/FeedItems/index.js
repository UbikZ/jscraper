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
    pending: PropTypes.bool.isRequired
  };

  loadList = (filter = {}) => {
    this.props.fetchFeedItems(filter);
  };

  render() {
    const {pending} = this.props;

    return (
      <Main title={'Feed Items'} pending={pending}>
        <Filter loadList={this.loadList} />
        <List loadList={this.loadList} />
      </Main>
    );
  }
}

function mapStateToProps(state) {
  const {pending} = state.feedItems;
  return {pending};
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    fetchFeedItems
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FeedItems);