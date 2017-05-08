import React, {Component} from 'react';

export default class Footer extends Component {
  shouldComponentUpdate() {
    return false;
  }

  render() {
    return (
      <footer className='bg-gray'>
        <section id='copyright' className='grid-footer container grid-960'>
          <p>
            <a href='https://github.com/ubikz/jscraper' rel="noopener">GitHub Repo</a> | Version <span className='version'>0.0.1</span>
          </p>
          <p>Developed and built with ♥ by <a href='https://github.com/ubikz' rel="noopener">UbikZ</a>.</p>
        </section>
      </footer>
    );
  }
}