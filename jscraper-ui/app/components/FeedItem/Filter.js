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
    approved: PropTypes.bool,
    tags: PropTypes.array
  };

  removeTag = (tag) => this.handleTags(this.props.tags.filter(t => t !== tag));
  addTag = (e) => {
    const value = e.target.value;
    const values = new Set(this.props.tags);
    if (e.key === 'Enter' && value && !values.has(value)) {
      values.add(value);
      this.handleTags([...values]);
      e.target.value = '';
    }
  };
  handleTags = (tags) => this.props.loadList({tags});

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
    const {startDate, endDate, approved, tags} = this.props;

    return (
      <div>

        <div className="form-horizontal">
          <div className="form-group">
            <div className="col-1">
              <label className="form-label">Start : </label>
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
              <label className="form-label">End : </label>
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
            <div className="col-3">
              <label className="form-switch">
                <input type="checkbox" name="approved" defaultChecked={approved} onChange={this.handleInputChange}/>
                <i className="form-icon"></i> Approved
              </label>
            </div>
          </div>
        </div>
        <div className="form-group">
          <div className="col-12">
            <div className="input-group">
              <span className="input-group-addon addon-sm">Search</span>
              <input type="text" className="form-input input-sm" placeholder="Search by tags" onKeyPress={this.addTag}/>
            </div>
          </div>
        </div>
        <div className="form-horizontal">
          <div className="form-group">
            <div className="col-2">
              <label className="form-label"><strong>Searched Tags : </strong></label>
            </div>
            <div className="col-10">
              {tags.map((tag, i) => (
                <span key={i} className="label label-primary mt-5 mr-5 mb-5">
                    {tag}&nbsp;<a className="a-icon pl-10" onClick={() => this.removeTag(tag)}>
                    <i className="icon icon-delete"></i></a>
                  </span>
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

function mapStateToProps(state) {
  const {startDate, endDate, approved, tags} = state.feedItems;
  return {startDate, endDate, approved, tags};
}

export default connect(mapStateToProps)(Filter);