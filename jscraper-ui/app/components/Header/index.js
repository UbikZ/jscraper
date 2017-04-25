import React, {Component} from 'react';

import NavBar from './NavBar';
import NavLink from './NavLink';

export default class Header extends Component {
  shouldComponentUpdate() {
    return false;
  }

  render() {
    return (
      <section className='header'>
        <NavBar>
          <NavLink to='/' text='Home'/>
          <NavLink to='/feed-items' text='Feed Items'/>
        </NavBar>
      </section>
    );
  }
}