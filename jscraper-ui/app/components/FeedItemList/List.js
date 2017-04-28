import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class List extends Component {
  static propTypes = {
    items: PropTypes.array
  };

  constructor(props) {
    super(props);
  }

  render() {
    const {items} = this.props;
    return (
      <div>
        <p>Size : {items.length}</p>
        <ul>
          {items.map((item) => {
            return <li key={item.checksum}><a href={item.url}>{item.url}</a></li>;
          })}
        </ul>
      </div>
    );
  }
}