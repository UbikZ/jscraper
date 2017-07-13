import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class CheckAuthentication extends Component {
  static propTypes = {
    role: PropTypes.string.isRequired,
    isAuthenticated: PropTypes.bool.isRequired,
    token: PropTypes.string,
    roles: PropTypes.array
  };

  authenticated = () => {
    return this.props.isAuthenticated && !!this.props.token && !!~this.props.roles.indexOf(this.props.role);
  }

  render() {
    return this.authenticated() ? this.props.children : null;
  }
}

function mapStateToProps(state) {
  const {isAuthenticated, token, roles} = state.authentication;
  return {isAuthenticated, token, roles};
}

export default connect(mapStateToProps)(CheckAuthentication);