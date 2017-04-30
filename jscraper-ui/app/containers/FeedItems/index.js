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

  constructor(props) {
    super(props);
    this.filter = {};
  }

  componentDidMount() {
    this.loadList();
  }

  loadList = (filter = {}) => {
    this.filter = filter;
    this.props.fetchFeedItems(this.filter);
  };

  render() {
    const {feedItems} = this.props;

    return (
      <Main title={'Feed Items Stuff'}>
        <FeedItemList items={feedItems} load={this.loadList}/>
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