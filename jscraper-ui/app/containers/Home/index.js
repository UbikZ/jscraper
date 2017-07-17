import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {login} from './action';

import Main from '../../components/Main';
import Login from '../../components/Form/Login';

class Home extends Component {
  static propTypes = {
    login: PropTypes.func.isRequired,
    isAuthenticated: PropTypes.bool.isRequired,
    isFetching: PropTypes.bool.isRequired,
    errorMessage: PropTypes.string
  };

  render() {
    const {login, isAuthenticated, isFetching} = this.props;

    return (
      <Main title={'Home Stuff'}>
        {!isAuthenticated
          ? (<Login login={login} isFetching={isFetching}/>)
          : (<span></span>)}
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
    login
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(Home);
