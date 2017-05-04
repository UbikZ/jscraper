import React, {Component} from 'react';
import PropTypes from 'prop-types';
import DatePicker from 'react-datepicker';
import {connect} from 'react-redux';

import 'react-datepicker/dist/react-datepicker.css';

class Filter extends Component {
  static propTypes = {
    loadList: PropTypes.func.isRequired,
    startDate: PropTypes.object,
    endDate: PropTypes.object
  };

  handleDateChange = (date, type) => {
    this.props.loadList({[type]: date});
  };

  handleStartDateChange = (date) => this.handleDateChange(date, 'startDate');
  handleEndDateChange = (date) => this.handleDateChange(date, 'endDate');

  render() {
    const {startDate, endDate} = this.props;
    
    return (
      <div>
        <div className="form-group">
          <label className="form-label">Start Date</label>
          <DatePicker
            selectsStart
            selected={startDate}
            startDate={startDate}
            endDate={endDate}
            onChange={this.handleStartDateChange}
            isClearable={true}
          />
        </div>
        <div className="form-group">
          <label className="form-label">End Date</label>
          <DatePicker
            selectsEnd
            selected={endDate}
            startDate={startDate}
            endDate={endDate}
            onChange={this.handleEndDateChange}
            isClearable={true}
          />
        </div>
        <div className="divider-vert"></div>
      </div>
    );
  }
}

function mapStateToProps(state) {
  const {startDate, endDate} = state.feedItems;
  return {startDate, endDate};
}

export default connect(mapStateToProps)(Filter);