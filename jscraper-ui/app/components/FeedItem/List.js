import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import styled from 'styled-components';

import Paginate from './Paginate';
import EmptyList from "../List/empty";

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
  padding: 1.5rem 1rem;
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
    items: PropTypes.array.isRequired,
    total: PropTypes.number.isRequired
  };

  searchTag = (tag) => this.props.loadList({tags: [tag]});

  render() {
    const {items, total} = this.props;

    return (
      <div>
        <ContainerStriped className="container">
          <div className="columns col-gapless">
            <ColumnHead className="col-1">#</ColumnHead>
            <ColumnHead className="col-3">Url</ColumnHead>
            <ColumnHead className="col-2">Size : {items.length} / {total}</ColumnHead>
            <ColumnHeadPaginate className="col-6">
              <Paginate loadList={this.props.loadList}/>
            </ColumnHeadPaginate>
          </div>
          {items.length === 0
            ? (<EmptyList title="Not Found" subtitle="0 feed item found here." icon="cross"/>)
            : items.map(item => (
              <div key={item.checksum} className="container-body columns col-gapless">
                <ColumnBody className="col-1">{item.id}</ColumnBody>
                <ColumnBody className="col-5"><a href={item.url} target="_blank">{item.url}</a></ColumnBody>
                <ColumnBody className="col-6 text-right">
                  {item.tags && item.tags.map((tag, i) => (
                    <button onClick={() => this.searchTag(tag.label)} key={i}
                            className="btn btn-primary btn-sm mr-5 mb-5">
                      {tag.label}
                    </button>
                  ))}
                </ColumnBody>
              </div>
            ))
          }
        </ContainerStriped>
      </div>
    );
  }
}

function mapStateToProps(state) {
  const {items, total} = state.feedItems;
  return {items, total};
}

export default connect(mapStateToProps)(List);