import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {signIn} from './action';

import Main from '../../components/Main';
import Auth from '../../components/Auth';

class Authentication extends Component {
  static propTypes = {
    signIn: PropTypes.func.isRequired,
    isAuthenticated: PropTypes.bool.isRequired,
    isFetching: PropTypes.bool.isRequired,
    errorMessage: PropTypes.string
  };

  render() {
    const {signIn, isAuthenticated, isFetching} = this.props;

    return (
      <Main title={'Authentication'}>
        <Auth signIn={signIn} isAuthenticated={isAuthenticated} isFetching={isFetching}/>
      </Main>
    );
  }
}

function mapStateToProps(state) {
  const {isAuthenticated, isFetching, errorMessage} = state.authentication;
  return {isAuthenticated, isFetching, errorMessage};
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    signIn
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(Authentication);
