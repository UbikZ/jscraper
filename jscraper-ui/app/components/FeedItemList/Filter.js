import React, {Component} from 'react';
import PropTypes from 'prop-types';
import DatePicker from 'react-datepicker';

import 'react-datepicker/dist/react-datepicker.css';

export default class Filter extends Component {
  static propTypes = {
    load: PropTypes.func
  };

  constructor(props) {
    super(props);
    this.state = {};
  }

  shouldComponentUpdate() {
    return false;
  }

  mutateState = (name, value) => {
    this.setState({[name]: value}, () => {
      if (!value) {
        delete this.state[name];
      }

      this.props.load(this.state);
    });
  };

  handleInputChange = (event) => {
    const target = event.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;

    this.mutateState(name, value);
  };

  handleDateChange = (date, type) => {
    const formatedValue = date.format('YYYY-MM-DD');
    this.mutateState(type, formatedValue);
  };

  handleStartDateChange = (date) => this.handleDateChange(date, 'startDate');
  handleEndDateChange = (date) => this.handleDateChange(date, 'endDate');

  render() {
    return (
      <div>
        <DatePicker
          selectsStart
          selected={this.state.startDate}
          startDate={this.state.startDate}
          endDate={this.state.endDate}
          onChange={this.handleStartDateChange}
          isClearable={true}
        />
        <DatePicker
          selectsEnd
          selected={this.state.startDate}
          startDate={this.state.startDate}
          endDate={this.state.endDate}
          onChange={this.handleEndDateChange}
          isClearable={true}
        />
      </div>
    );
  }
}