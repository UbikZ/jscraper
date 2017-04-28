import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {fetchFeedItems} from './action';
import Main from '../../components/Main';
import FeedItemList from '../../components/FeedItemList';

class FeedItems extends Component {
  static propTypes = {
    feedItems: PropTypes.array,
    error: PropTypes.object
  };

  componentDidMount() {
    this.props.fetchFeedItems();
  }

  render() {
    const {feedItems, error, fetchFeedItems} = this.props;

    return (
      <Main title={'Feed Items Stuff'}>
        <FeedItemList items={feedItems} load={fetchFeedItems}/>
      </Main>
    );
  }
}

function mapStateToProps({feedItems, error}) {
  return {feedItems, error};
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    fetchFeedItems
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FeedItems);