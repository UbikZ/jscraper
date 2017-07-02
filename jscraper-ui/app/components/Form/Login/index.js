import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class Login extends Component {
  static propTypes = {
    loginUser: PropTypes.func.isRequired
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.loginUser({username: this.refs.username.value, password: this.refs.password.value});
  };

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <div>
          <label htmlFor="username">Username</label>
          <input ref="username" id="username" name="username" type="text"/>
        </div>
        <div>
          <label htmlFor="password">Password</label>
          <input ref="password" id="password" name="password" type="password"/>
        </div>
        <button type="submit">Submit</button>
      </form>
    );
  }
}