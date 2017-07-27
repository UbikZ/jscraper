import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class SignUp extends Component {
  static propTypes = {
    signIn: PropTypes.func.isRequired
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.signIn({username: this.refs.username.value, password: this.refs.password.value});
  };

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <div className="form-group">
          <label className="form-label" htmlFor="username">Username</label>
          <input ref="username" className="form-input" type="text" id="username"
                 placeholder="Username"></input>
        </div>
        <div className="form-group">
          <label className="form-label" htmlFor="password">Password</label>
          <input ref="password" className="form-input" type="password" id="password"
                 placeholder="Password"></input>
        </div>
        <div className="form-group">
          <button className="btn btn-primary float-right">Sign in</button>
        </div>
      </form>
    );
  }
}