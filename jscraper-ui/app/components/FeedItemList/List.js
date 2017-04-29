import React, {Component} from 'react';
import PropTypes from 'prop-types';

export default class List extends Component {
  static propTypes = {
    items: PropTypes.array
  };

  constructor(props) {
    super(props);
  }

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
              <th>Size : {items.length}</th>
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