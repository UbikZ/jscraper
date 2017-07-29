import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class Check extends Component {
  static propTypes = {
    role: PropTypes.string.isRequired,
    isAuthenticated: PropTypes.bool.isRequired,
    reverse: PropTypes.bool,
    token: PropTypes.string,
    roles: PropTypes.array
  };

  authenticated = () => {
    const result = this.props.isAuthenticated && !!this.props.token && !!~this.props.roles.indexOf(this.props.role);
    return !this.props.reverse ? result : !result;
  }

  render() {
    return this.authenticated() ? this.props.children : null;
  }
}

function mapStateToProps(state) {
  const {isAuthenticated, token, roles} = state.authentication;
  return {isAuthenticated, token, roles};
}

export default connect(mapStateToProps)(Check);