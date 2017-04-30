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
    this.mutateState(type, date);
  };

  handleStartDateChange = (date) => this.handleDateChange(date, 'startDate');
  handleEndDateChange = (date) => this.handleDateChange(date, 'endDate');

  render() {
    return (
      <div>
        <div className="form-group">
          <label className="form-label">Start Date</label>
          <DatePicker
            selectsStart
            selected={this.state.startDate}
            startDate={this.state.startDate}
            endDate={this.state.endDate}
            onChange={this.handleStartDateChange}
            isClearable={true}
          />
        </div>
        <div className="form-group">
          <label className="form-label">End Date</label>
          <DatePicker
            selectsEnd
            selected={this.state.endDate}
            startDate={this.state.startDate}
            endDate={this.state.endDate}
            onChange={this.handleEndDateChange}
            isClearable={true}
          />
        </div>
        <div className="divider-vert"></div>
      </div>
    );
  }
}