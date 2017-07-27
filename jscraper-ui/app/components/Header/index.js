import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import CheckAuthentication from '../Auth/Check';

export default class Header extends Component {
  render() {
    return (
      <section className="section section-header bg-gray">
        <section className="grid-header container grid-960">
          <nav className="navbar">
            <section className="navbar-section">
              <strong className="navbar-brand mr-10"><Link to='/' className="btn btn-link">JScraper</Link></strong>
              <CheckAuthentication role="ROLE_USER" reverse={true}>
                <Link to='/authentication' className="btn btn-link">Auth</Link>
              </CheckAuthentication>
              <CheckAuthentication role="ROLE_USER">
                <Link to='/feed-items' className="btn btn-link">Feed Items</Link>
              </CheckAuthentication>
              <CheckAuthentication role="ROLE_ADMIN">
                <Link to='/tags' className="btn btn-link">Tags</Link>
              </CheckAuthentication>
            </section>
            <section className="navbar-section">
              <a href="https://github.com/ubikz/jscraper" rel="noopener" className="btn btn-primary">Github</a>
            </section>
          </nav>
        </section>
      </section>
    );
  }
}