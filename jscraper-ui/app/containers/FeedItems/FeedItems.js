import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {fetchFeedItems} from '../../reducers';
import {FeedItemFilter, FeedItemList, PartialPanel} from './../../components';

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
      <PartialPanel title={'Feed Items'} isFetching={isFetching}>
        <FeedItemFilter loadList={this.loadList}/>
        <FeedItemList loadList={this.loadList}/>
      </PartialPanel>
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