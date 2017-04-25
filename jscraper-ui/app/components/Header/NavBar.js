import React, {Component} from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';

const Nav = styled.nav`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 6.5rem;
  background: #fff;
  z-index: 99;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
`;

const NavUl = styled.ul`
  list-style: none;
  margin-bottom: 0;
`;

export default class NavBar extends Component {
  static propTypes = {
    children: PropTypes.node
  };

  constructor(props) {
    super(props);
  }

  shouldComponentUpdate() {
    return false;
  }

  render() {
    return (
      <Nav>
        <div className="container">
          <NavUl>
            {this.props.children}
          </NavUl>
        </div>
      </Nav>
    );
  }
}