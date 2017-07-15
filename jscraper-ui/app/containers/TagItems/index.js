import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {fetchTags, editTag, deleteTag} from './action';
import Main from '../../components/Main';
import Filter from '../../components/Tag/Filter';
import List from '../../components/Tag/List';

class TagItems extends Component {
  static propTypes = {
    fetchTags: PropTypes.func.isRequired,
    editTag: PropTypes.func.isRequired,
    deleteTag: PropTypes.func.isRequired,
    isFetching: PropTypes.bool.isRequired
  };

  loadList = (filter = {}) => this.props.fetchTags(filter);
  editElement = (id) => this.props.editTag(id);
  deleteElement = (id) => this.props.deleteTag(id);

  render() {
    const {isFetching} = this.props;

    return (
      <Main title={'Tags'} isFetching={isFetching}>
        <Filter loadList={this.loadList}/>
        <List loadList={this.loadList} editElement={this.editElement} deleteElement={this.deleteElement}/>
      </Main>
    );
  }
}

function mapStateToProps(state) {
  const {isFetching} = state.tagItems;
  return {isFetching};
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    fetchTags,
    editTag,
    deleteTag
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(TagItems);