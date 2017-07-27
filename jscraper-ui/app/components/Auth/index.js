import React, {Component} from 'react';
import PropTypes from 'prop-types';
import SignIn from "./SignIn";
import SignUp from "./SignUp";

const MODE_SIGNIN = 'signin';
const MODE_SIGNUP = 'signup';

export default class Auth extends Component {
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
      <div className="columns">
        <div className="column col-12">
          <div className="modal-overlay"></div>
          <div className="modal-container">
            <div className="modal-header text-center">
              <h1><i className="icon icon-people"></i></h1>
            </div>
            <div className="modal-body">
              <div className="content">
                <ul className="tab tab-block">
                  <li className="tab-item">
                    <a onClick={() => this.setMode(MODE_SIGNIN)} className={this.isSignIn() && 'active'}>Sign In</a>
                  </li>
                  <li className="tab-item">
                    <a onClick={() => this.setMode(MODE_SIGNUP)} className={this.isSignUp() && 'active'}>Sign Up</a>
                  </li>
                </ul>
                {this.isSignIn() && (<SignIn signIn={signIn}/>)}
                {this.isSignUp() && (<SignUp signIn={signIn}/>)}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}