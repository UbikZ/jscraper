import React, {Component} from 'react';
import Main from '../../components/Main';

export default class Home extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Main title={'Home Stuff'}>
        Test Home text
      </Main>
    );
  }
}