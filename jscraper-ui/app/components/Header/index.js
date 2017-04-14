import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import NavBar from './NavBar';

export default class App extends Component {
  render() {
    return (
      <section className="header">
          <NavBar>
            <div className="container">
              <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/test">Test</Link></li>
              </ul>
            </div>
          </NavBar>
      </section>
    );
  }
}