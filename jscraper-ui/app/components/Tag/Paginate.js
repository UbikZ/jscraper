import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import ReactPaginate from 'react-paginate';

class Paginate extends Component {
  static propTypes = {
    loadList: PropTypes.func.isRequired,
    total: PropTypes.number.isRequired,
    offset: PropTypes.number.isRequired,
    limit: PropTypes.number.isRequired
  };

  handlePageClick = (data) => {
    this.props.loadList({offset: Math.ceil(data.selected * this.props.limit)});
  }

  render() {
    const clsPageItem = "page-item";
    const {total, limit, offset} = this.props;

    return (
      <ReactPaginate previousLabel={"Previous"}
                     nextLabel={"Next"}
                     breakLabel={<span>...</span>}
                     initialPage={offset}
                     pageCount={Math.ceil(total / limit)}
                     marginPagesDisplayed={2}
                     pageRangeDisplayed={3}
                     onPageChange={this.handlePageClick}
                     containerClassName={"pagination float-right"}
                     previousClassName={clsPageItem}
                     nextClassName={clsPageItem}
                     pageClassName={clsPageItem}
                     breakClassName={clsPageItem}
                     hrefBuilder={() => "#"}
                     activeClassName={"active"}/>
    );
  }
}

function mapStateToProps(state) {
  const {total, limit, offset} = state.tagItems;
  return {total, limit, offset};
}

export default connect(mapStateToProps)(Paginate);