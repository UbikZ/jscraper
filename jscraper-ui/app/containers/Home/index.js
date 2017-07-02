import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {loginUser} from './action';

import Main from '../../components/Main';
import Login from '../../components/Form/Login';

class Home extends Component {
  static propTypes = {
    loginUser: PropTypes.func.isRequired,
    isAuthenticated: PropTypes.bool.isRequired,
    isFetching: PropTypes.bool.isRequired,
    errorMessage: PropTypes.string
  };

  render() {
    const {loginUser, isAuthenticated, isFetching, errorMessage} = this.props;

    return (
      <Main title={'Home Stuff'} pending={isFetching}>
        <Login loginUser={loginUser}/>
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
    loginUser
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(Home);
