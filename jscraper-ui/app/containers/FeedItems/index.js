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
    tags: PropTypes.array,
    offset: PropTypes.number,
    limit: PropTypes.number,
    startDate: PropTypes.object,
    endDate: PropTypes.object
  };

  loadList = (filter = {}) => {
    this.props.fetchFeedItems(filter);
  };

  render() {
    return (
      <Main title={'Feed Items Stuff'}>
        <Filter loadList={this.loadList} />
        <List loadList={this.loadList} />
      </Main>
    );
  }
}

function mapStateToProps(state) {
  const {offset, limit, tags, startDate, endDate} = state.feedItems;
  return {offset, limit, tags, startDate, endDate};
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    fetchFeedItems
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FeedItems);