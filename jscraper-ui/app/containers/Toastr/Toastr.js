import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import styled from 'styled-components';

const ToastrNotifs = styled.div`
    z-index: 99999;
    position: absolute;
    top: 70px;
    right: 10px;
`;

const ToastrNotif = styled.div`
    width: 300px;
    margin-bottom: 10px;
`;

class Toastr extends Component {
  static propTypes = {
    notifs: PropTypes.array.isRequired
  };

  render() {
    const {notifs} = this.props;

    return (
      <ToastrNotifs>
        {notifs.map((notif) => (
          <ToastrNotif key={notif.id} className={`toast toast-${notif.tType}`}>
            <button className="btn btn-clear float-right"></button>
            {notif.title && (<p><strong>{notif.title}</strong></p>)}
            {notif.message}
          </ToastrNotif>
        ))}
      </ToastrNotifs>
    );
  }
}

function mapStateToProps(state) {
  const {notifs} = state.toastr;
  return {notifs};
}

export default connect(mapStateToProps)(Toastr);