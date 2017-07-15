import React, {Component} from 'react';
import PropTypes from 'prop-types';

import 'react-datepicker/dist/react-datepicker.css';

export default class Filter extends Component {
  static propTypes = {
    loadList: PropTypes.func.isRequired,
    label: PropTypes.string
  };

  searchByLabel = (e) => {
    const value = e.target.value;
    if (e.key === 'Enter') {
      this.props.loadList({label: value});
    }
  };

  render() {
    return (
      <div>
        <div className="form-group">
          <div className="col-12">
            <div className="input-group">
              <span className="input-group-addon addon-sm">Search</span>
              <input type="text" className="form-input input-sm" placeholder="Search by label"
                     onKeyPress={this.searchByLabel}/>
            </div>
          </div>
        </div>
      </div>
    );
  }
}