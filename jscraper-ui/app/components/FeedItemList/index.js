import React, {Component} from 'react';
import PropTypes from 'prop-types';

import Filter from './Filter';
import List from './List';

export default class FeedItemList extends Component {
  static propTypes = {
    items: PropTypes.array,
    load: PropTypes.func
  };

  render() {
    const {items, load} = this.props;

    return (
      <div>
        <Filter load={load} />
        <List items={items} />
      </div>
    );
  }
}