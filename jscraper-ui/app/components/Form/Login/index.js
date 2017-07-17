import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class Login extends Component {
  static propTypes = {
    login: PropTypes.func.isRequired
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.login({username: this.refs.username.value, password: this.refs.password.value});
  };

  render() {
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
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}