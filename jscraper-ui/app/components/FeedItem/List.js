import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

import Paginate from './Paginate';

class List extends Component {
  static propTypes = {
    loadList: PropTypes.func.isRequired,
    items: PropTypes.array.isRequired
  };

  render() {
    const {items} = this.props;

    return (
      <div>
        <ul>
          <table className="table table-striped table-hover">
            <thead>
            <tr>
              <th>Id</th>
              <th>Url</th>
              <th className="text-center">Size : {items.length}</th>
              <th>
                <Paginate loadList={this.props.loadList} />
              </th>
            </tr>
            </thead>
            <tbody>
            {items.map((item) => {
              return (
                <tr key={item.checksum}>
                  <td>{item.id}</td>
                  <td colSpan="3"><a href={item.url} target="_blank">{item.url}</a></td>
                </tr>
              );
            })}
            </tbody>
          </table>
        </ul>
      </div>
    );
  }
}

function mapStateToProps(state) {
  const {items} = state.feedItems;
  return {items};
}

export default connect(mapStateToProps)(List);