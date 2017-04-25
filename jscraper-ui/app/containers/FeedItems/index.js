import React, {Component} from 'react';
import PropTypes from 'prop-types';
import Main from '../../components/Main';
import {getFeedItems} from './api';


export default class FeedItems extends Component {
  state = {
    error: false,
    loading: false
  }

  static propTypes = {
    feedItems: PropTypes.array,
    error: PropTypes.string
  }

  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.handleFetchFeedItems();
    console.log("mounted");
  }

  handleFetchFeedItems = () => {
    this.setState(prevState => ({loading: true, error: false}));
    getFeedItems()
      .then(items => {
        this.props.feedItems = items;
        this.setState(prevState => ({loading: false}));
      })
      .catch(error => {
        this.props.error = error;
        this.setState(prevState => ({error: true}));
      });
  }

  render() {
    const {
      feedItems,
      error
    } = this.props;

    return (
      <Main title={'Feed Items Stuff'}>
        Test Test text
      </Main>
    );
  }
}