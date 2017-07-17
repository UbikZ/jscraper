import React, {Component} from 'react';
import PropTypes from 'prop-types';


export default class EmptyList extends Component {
  static propTypes = {
    title: PropTypes.string.isRequired,
    subtitle: PropTypes.string.isRequired,
    icon: PropTypes.string.isRequired
  };

  render() {
    const {title, subtitle, icon, children} = this.props;

    return (
      <div>
        <div className="empty">
          <div className="empty-icon">
            <i className={`icon icon-${icon}`}></i>
          </div>
          <h4 className="empty-title">{title}</h4>
          <p className="empty-subtitle">{subtitle}</p>
          {children}
        </div>
      </div>
    );
  }
}