import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class Filter extends Component {
  static propTypes = {
    load: PropTypes.func
  };

  constructor(props) {
    super(props);
    this.inputs = {};
  }

  static shouldComponentUpdate() {
    return false;
  }

  handleSubmit = (event) => {
    event.preventDefault();
    this.props.load(this.inputs);
  };

  handleInputChange = (event) => {
    const target = event.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;

    if (value) {
      this.inputs = {[name]: value};
    } else {
      delete this.inputs[name];
    }
  };

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <input name="startDate" type="text" onChange={this.handleInputChange} />
        <input type="submit" value="Submit" />
      </form>
    );
  }
}