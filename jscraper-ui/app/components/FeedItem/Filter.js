import React, {Component} from 'react';
import PropTypes from 'prop-types';
import DatePicker from 'react-datepicker';
import {connect} from 'react-redux';

import 'react-datepicker/dist/react-datepicker.css';

class Filter extends Component {
  static propTypes = {
    loadList: PropTypes.func.isRequired,
    startDate: PropTypes.object,
    endDate: PropTypes.object,
    approved: PropTypes.bool
  };

  handleInputChange = (event) => {
    const target = event.target;
    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;

    this.props.loadList({[name]: value});
  };

  handleDateChange = (date, type) => {
    this.props.loadList({[type]: date});
  };

  handleStartDateChange = (date) => this.handleDateChange(date, 'startDate');
  handleEndDateChange = (date) => this.handleDateChange(date, 'endDate');

  render() {
    const {startDate, endDate, approved} = this.props;
    
    return (
      <form className="form-horizontal">
        <div className="form-group">
          <div className="col-1">
            <label className="form-label">Start</label>
          </div>
          <div className="col-3">
            <DatePicker
              selectsStart
              selected={startDate}
              startDate={startDate}
              endDate={endDate}
              onChange={this.handleStartDateChange}
              isClearable={true}
            />
          </div>
          <div className="col-1">
            <label className="form-label">End</label>
          </div>
          <div className="col-3">
            <DatePicker
              selectsEnd
              selected={endDate}
              startDate={startDate}
              endDate={endDate}
              onChange={this.handleEndDateChange}
              isClearable={true}
            />
          </div>
        </div>
        <div className="form-group">
          <div className="col-3">
            <label className="form-switch">
              <input type="checkbox" name="approved" defaultChecked={approved} onChange={this.handleInputChange}/>
              <i className="form-icon"></i> Approved
            </label>
          </div>
        </div>
      </form>
    );
  }
}

function mapStateToProps(state) {
  const {startDate, endDate, approved, tags} = state.feedItems;
  return {startDate, endDate, approved, tags};
}

export default connect(mapStateToProps)(Filter);