import React, {Component} from 'react';
import {Link} from 'react-router-dom';

export default class Header extends Component {
  shouldComponentUpdate() {
    return false;
  }

  render() {
    return (
      <section className="section section-header bg-gray">
        <section className="grid-header container grid-960">
          <nav className="navbar">
            <section className="navbar-section">
              <strong className="navbar-brand mr-10">JScraper</strong>
              <Link to='/' className="btn btn-link">Home</Link>
              <Link to='/feed-items' className="btn btn-link">Feed Items</Link>
            </section>
            <section className="navbar-section">
              <a href="https://github.com/ubikz/jscraper" target="_blank" className="btn btn-primary">Github</a>
            </section>
          </nav>
        </section>
      </section>
    );
  }
}