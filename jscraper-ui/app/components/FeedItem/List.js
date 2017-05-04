import React, {Component} from 'react';
import ReactPaginate from 'react-paginate';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';

class List extends Component {
  static propTypes = {
    loadList: PropTypes.func.isRequired,
    items: PropTypes.array.isRequired,
    total: PropTypes.number.isRequired,
    offset: PropTypes.number.isRequired,
    limit: PropTypes.number.isRequired
  };

  handlePageClick = (data) => {
    this.props.loadList({offset: Math.ceil(data.selected * this.props.limit)});
  }

  render() {
    const {items, total, limit, offset} = this.props;

    return (
      <div>
        <ul>
          <table className="table table-striped table-hover">
            <thead>
            <tr>
              <th>Id</th>
              <th>Url</th>
              <th>Size : {items.length}</th>
              <th>
                <ReactPaginate previousLabel={"previous"}
                               nextLabel={"next"}
                               breakLabel={<a href="">...</a>}
                               breakClassName={"break-me"}
                               initialPage={offset}
                               pageCount={Math.ceil(total / limit)}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination"}
                               subContainerClassName={"pages pagination"}
                               activeClassName={"active"}/>
              </th>
            </tr>
            </thead>
            <tbody>
            {items.map((item) => {
              return (
                <tr key={item.checksum}>
                  <td>{item.id}</td>
                  <td colSpan="2"><a href={item.url} target="_blank">{item.url}</a></td>
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
  const {items, total, limit, offset} = state.feedItems;
  return {items, total, limit, offset};
}

export default connect(mapStateToProps)(List);