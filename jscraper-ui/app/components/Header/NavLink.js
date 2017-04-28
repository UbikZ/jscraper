import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import styled from 'styled-components';
import PropTypes from 'prop-types';

const NavLi = styled.li`
  position: relative;
  float: left;
  margin-bottom: 0;
`;

const NavA = styled(Link)`
    text-transform: uppercase;
    font-size: 11px;
    font-weight: 600;
    letter-spacing: .2rem;
    margin-right: 35px;
    text-decoration: none;
    line-height: 6.5rem;
    color: #222;
`;

export default class NavLink extends Component {
  static propTypes = {
    to: PropTypes.string,
    text: PropTypes.string
  };

  constructor(props) {
    super(props);
  }

  shouldComponentUpdate() {
    return false;
  }

  render() {
    const {to, text} = this.props;

    return (
      <NavLi>
        <NavA to={to}>{text}</NavA>
      </NavLi>
    );
  }
}