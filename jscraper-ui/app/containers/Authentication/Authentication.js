import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import styled from 'styled-components';

import {signIn} from '../../reducers';
import {AuthSignIn, AuthSignUp, PartialPanel} from './../../components';

const MODE_SIGNIN = 'signin';
const MODE_SIGNUP = 'signup';

const A = styled.a`
  cursor: pointor;
`;

class Authentication extends Component {
  static propTypes = {
    signIn: PropTypes.func.isRequired,
    signUp: PropTypes.func.isRequired,
    isFetching: PropTypes.bool.isRequired
  };

  constructor(props) {
    super(props);
    this.state = {mode: MODE_SIGNIN};
  }

  setMode = (mode) => this.setState({mode});
  isSignIn = () => this.state.mode === MODE_SIGNIN;
  isSignUp = () => this.state.mode === MODE_SIGNUP;

  render() {
    const {signIn} = this.props;
    return (
      <PartialPanel title={'Authentication'}>
        <div className="panel">
          <div className="panel-header">
            <div className="panel-title">
              <h1><i className="icon icon-people"></i></h1>
            </div>
          </div>
          <div className="panel-nav">
            <ul className="tab tab-block">
              <li className="tab-item">
                <A onClick={() => this.setMode(MODE_SIGNIN)} className={this.isSignIn() && 'active'}>Sign In</A>
              </li>
              <li className="tab-item">
                <A onClick={() => this.setMode(MODE_SIGNUP)} className={this.isSignUp() && 'active'}>Sign Up</A>
              </li>
            </ul>
          </div>
          <div className="panel-body">
            {this.isSignIn() && (<AuthSignIn signIn={signIn}/>)}
            {this.isSignUp() && (<AuthSignUp signIn={signIn}/>)}
          </div>
          <div className="panel-footer">
            <div className="tile-subtitle">Enjoy the application.</div>
          </div>
        </div>
      </PartialPanel>
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
