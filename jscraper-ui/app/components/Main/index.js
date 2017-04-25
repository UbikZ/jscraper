import React, {Component} from 'react';
import PropTypes from 'prop-types';

import H6 from './H6';
import P from './P';

export default class Main extends Component {
  static propTypes = {
    title: PropTypes.string,
    content: PropTypes.any
  };

  constructor(props) {
    super(props);
  }

  render() {
    const {title, children} = this.props;

    return (
      <div>
        <H6>{title}</H6>
        <P>{children}</P>
      </div>
    );
  }
}