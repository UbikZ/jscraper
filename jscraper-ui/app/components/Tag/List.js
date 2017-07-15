import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import styled from 'styled-components';
import moment from 'moment';

import Paginate from './Paginate';

const ColumnHead = styled.div`
  font-weight: bold;
  border-bottom: .1rem solid #727e96;
  padding: 2rem 1rem;
`;

const ColumnHeadPaginate = styled.div`
  font-weight: bold;
  border-bottom: .1rem solid #727e96;
`;

const ColumnBody = styled.div`
  border-bottom: .1rem solid #f0f1f4;
  padding: 1rem 1rem;
  overflow: hidden;
`;

const ContainerStriped = styled.div`
  .container-body.columns:nth-of-type(odd) {
        background: #f8f9fa;
  }
`;

class List extends Component {
  static propTypes = {
    loadList: PropTypes.func.isRequired,
    editElement: PropTypes.func.isRequired,
    deleteElement: PropTypes.func.isRequired,
    items: PropTypes.array.isRequired,
    total: PropTypes.number.isRequired
  };

  deleteTag = (id) => this.props.deleteElement(id);
  editTag = (id) => this.props.editElement(id);

  render() {
    const {items, total} = this.props;

    return (
      <div>
        <ContainerStriped className="container">
          <div className="columns col-gapless">
            <ColumnHead className="col-1">#</ColumnHead>
            <ColumnHead className="col-3">Label</ColumnHead>
            <ColumnHead className="col-2">Date</ColumnHead>
            <ColumnHead className="col-2">Size : {items.length} / {total}</ColumnHead>
            <ColumnHeadPaginate className="col-4">
              <Paginate loadList={this.props.loadList}/>
            </ColumnHeadPaginate>
          </div>
          {items.map(item => (
            <div key={item.id} className="container-body columns col-gapless">
              <ColumnBody className="col-1">{item.id}</ColumnBody>
              <ColumnBody className="col-3">{item.label}</ColumnBody>
              <ColumnBody className="col-2">{moment(item.date).format('DD MMMM YYYY')}</ColumnBody>
              <ColumnBody className="col-6 text-right">
                <button onClick={() => this.editTag(item.id)} className="btn btn-sm mr-5 mb-5">
                  <i className="icon icon-edit"></i>
                </button>
                <button onClick={() => this.deleteTag(item.id)} className="btn btn-sm mr-5 mb-5">
                  <i className="icon icon-delete"></i>
                </button>
              </ColumnBody>
            </div>
          ))}
        </ContainerStriped>
      </div>
    );
  }
}

function mapStateToProps(state) {
  const {items, total} = state.tagItems;
  return {items, total};
}

export default connect(mapStateToProps)(List);