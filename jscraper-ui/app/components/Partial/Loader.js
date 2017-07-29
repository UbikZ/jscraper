import React, {Component} from 'react';
import styled from 'styled-components';

const Spinner = styled.div`
  width: 36px;
  height: 36px;
  margin: 20px auto;
`;

export default class Loader extends Component {
  shouldComponentUpdate() {
    return false;
  }

  render() {
    return (
      <Spinner className="sk-cube-grid">
        <div className="sk-cube sk-cube1"></div>
        <div className="sk-cube sk-cube2"></div>
        <div className="sk-cube sk-cube3"></div>
        <div className="sk-cube sk-cube4"></div>
        <div className="sk-cube sk-cube5"></div>
        <div className="sk-cube sk-cube6"></div>
        <div className="sk-cube sk-cube7"></div>
        <div className="sk-cube sk-cube8"></div>
        <div className="sk-cube sk-cube9"></div>
      </Spinner>
    );
  }
}