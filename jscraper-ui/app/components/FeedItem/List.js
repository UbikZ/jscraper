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
              <th className="text-center">Size&nbsp;:&nbsp;{items.length}</th>
              <th>
                <Paginate loadList={this.props.loadList} />
              </th>
            </tr>
            </thead>
            <tbody>
            {items.map(item => {
              return (
                <tr key={item.checksum}>
                  <td>{item.id}</td>
                  <td><a href={item.url} target="_blank">{item.url}</a></td>
                  <td colSpan="2" className="text-right">
                    {item.tags.map((tag, i) => {
                      return (<span key={i} className="label label-primary mr-5 mb-5">{tag.label}</span>);
                    })}
                  </td>
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