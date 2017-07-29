import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';

import {deleteTag, fetchTags} from '../../reducers';
import {PartialPanel, TagFilter, TagList} from './../../components';

class tags extends Component {
  static propTypes = {
    fetchTags: PropTypes.func.isRequired,
    deleteTag: PropTypes.func.isRequired,
    isFetching: PropTypes.bool.isRequired
  };

  loadList = (filter = {}) => this.props.fetchTags(filter);
  deleteElement = (id) => this.props.deleteTag(id);

  render() {
    const {isFetching} = this.props;

    return (
      <PartialPanel title={'Tags'} isFetching={isFetching}>
        <TagFilter loadList={this.loadList}/>
        <TagList loadList={this.loadList} editElement={this.editElement} deleteElement={this.deleteElement}/>
      </PartialPanel>
    );
  }
}

function mapStateToProps(state) {
  const {isFetching} = state.tags;
  return {isFetching};
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({
    fetchTags,
    deleteTag
  }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(tags);