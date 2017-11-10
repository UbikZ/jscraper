import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class SignIn extends Component {
  static propTypes = {
    signIn: PropTypes.func.isRequired
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.signIn({username: this.refs.username.value, password: this.refs.password.value});
  };

  render() {
    return (
      <form className="mt-10 mb-10 mt-10 ml-10" onSubmit={this.handleSubmit}>
        <div className="form-group">
          <label className="form-label" htmlFor="username"><strong>Username</strong></label>
          <input ref="username" className="form-input" type="text" id="username"
                 placeholder="Username"></input>
        </div>
        <div className="form-group mb-5">
          <label className="form-label" htmlFor="password"><strong>Password</strong></label>
          <input ref="password" className="form-input" type="password" id="password"
                 placeholder="Password"></input>
        </div>
        <div className="form-group pt-10">
          <button className="btn btn-primary float-right">Sign in</button>
        </div>
      </form>
    );
  }
}