import React, {Component} from 'react';
import PropTypes from 'prop-types';

import Loader from './../Loader';

export default class Main extends Component {
  static propTypes = {
    title: PropTypes.string,
    content: PropTypes.any,
    isFetching: PropTypes.bool
  };

  render() {
    const {title, children, isFetching} = this.props;

    return (
      <section className="container grid-960">
        <section className="columns">
          <div className="docs-content column col-12">
            <section className="container">
              <header className="text-center">
                <h3>{isFetching ? (<Loader/>) : title}</h3>
              </header>
              <section className="notes">{children}</section>
            </section>
          </div>
        </section>
      </section>
    );
  }
}