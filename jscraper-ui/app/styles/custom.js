export const custom = `
  .bg-gray {
      background: #f8f9fa;
      color: #667189;
      padding: 1rem;
  }
  
  .bg-dark {
      background: #50596c;
      color: #fff;
      padding: 1rem;
  }
  
  .bg-primary {
      background: #5764c6;
      color: #fff;
      padding: 1rem;
  }
  
  .section-header {
      padding: 1rem .5rem;
      position: fixed;
      top: 0;
      width: 100%;
      z-index: 200;
  }
  
  .section-hero {
      position: relative;
      z-index: 300;
  }
  
  .grid-header .navbar {
      height: 4rem;
  }
  
  .grid-header .navbar-section {
      padding: 0;
  }
  
  .grid-header .navbar-brand {
      color: #50596c;
  }
  
  .grid-header .btn-link {
      color: #667189;
      padding-left: .6rem;
      padding-right: .6rem;
  }
  
  .grid-header .btn-link:focus,
  .grid-header .btn-link:hover,
  .grid-header .btn-link:active {
      color: #50596c;
      opacity: .75;
  }
  
  .grid-hero {
      margin-bottom: 4rem;
      margin-top: 10rem;
  }
  
  .grid-hero h1 {
      color: #50596c;
      font-size: 3.2rem;
      font-weight: 400;
  }
  
  .grid-hero h2 {
      color: #5b657a;
      font-size: 1.8rem;
      font-weight: 400;
      line-height: 3rem;
      margin-bottom: 3rem;
  }
  
  .grid-hero h2 u {
      border-bottom: .2rem solid currentColor;
      padding-bottom: .1rem;
      text-decoration: none;
  }
  
  .grid-hero .card {
      background: none;
      border: 0;
      color: #667189;
      padding: 1rem;
  }
  
  .grid-hero .card .card-title {
      color: #5764c6;
      font-size: 1.8rem;
      margin-bottom: 0;
  }
  
  .grid-footer {
      color: #acb3c2;
      margin-bottom: 1rem;
      margin-top: 2rem;
  }
  
  .grid-footer a {
      color: #727e96;
  }
  
  .docs-content {
      padding-top: 0;
  }
  
  .docs-content .container {
      padding: 1rem;
  }
  
  .docs-content header {
      padding-top: 6rem;
  }
  
  .docs-content .anchor {
      height: 0;
      margin-left: -1em;
      overflow: hidden;
      position: absolute;
      width: 0;
  }
  
  .docs-content .anchor:focus,
  .docs-content .anchor:hover {
      height: auto;
      text-decoration: none;
      width: auto;
  }
  
  .docs-content .notes {
      margin: 4rem 0;
  }
  
  .docs-content .docs-block {
      border-radius: .2rem;
      padding: 1rem .5rem;
  }
  
  .docs-content .docs-dot {
      border-radius: 50%;
      display: inline-block;
      height: 1rem;
      padding: 0;
      width: 1rem;
  }
  
  .docs-content .docs-table th,
  .docs-content .docs-table td {
      padding: 1.5rem .5rem;
  }
  
  .docs-content h1,
  .docs-content h2,
  .docs-content h3,
  .docs-content h4,
  .docs-content h5,
  .docs-content h6 {
      margin-bottom: 2rem;
      margin-top: 2rem;
  }
  
  .docs-content h1:hover .anchor,
  .docs-content h2:hover .anchor,
  .docs-content h3:hover .anchor,
  .docs-content h4:hover .anchor,
  .docs-content h5:hover .anchor,
  .docs-content h6:hover .anchor {
      height: auto;
      width: auto;
  }
  
  .docs-content .docs-color {
      border-radius: .2rem;
      margin: .5rem 0;
      padding: 1rem;
  }
  
  .docs-content .docs-color .color-subtitle {
      font-size: 1.2rem;
      opacity: .75;
  }
  
  .docs-content .panel {
      height: 75vh;
  }
  
  .docs-content .panel .tile {
      margin: 1.5rem 0;
  }
  
  .docs-content .code {
      color: #667189;
  }
  
  .docs-content .code .com {
      color: #acb3c2;
  }
  
  .docs-content .code .tag {
      color: #5764c6;
  }
  
  .docs-content .code .atn {
      color: #667189;
  }
  
  .docs-content .code .atv {
      color: #e06870;
  }
  
  .docs-content .empty .icon {
      font-size: 4rem;
  }
  
  .docs-content .form-autocomplete .menu {
      position: static;
  }
  
  .docs-content .example-tile-icon {
      -webkit-align-content: space-around;
      align-content: space-around;
      -webkit-align-items: center;
      align-items: center;
      background: #5764c6;
      border-radius: .2rem;
      color: #fff;
      display: flex;
      display: -ms-flexbox;
      display: -webkit-flex;
      -ms-flex-align: center;
      -ms-flex-line-pack: distribute;
      font-size: 2.4rem;
      height: 4rem;
      width: 4rem;
  }
  
  .docs-content .example-tile-icon .icon {
      margin: auto;
  }
  
  .docs-content .comparison-slider .filter-grayscale {
      -webkit-filter: grayscale(75%);
      filter: grayscale(75%);
  }
  
  @media screen and (min-width: 601px) {
      .section-header .navbar-section {
          padding: 0 .5rem;
      }
  
      .docs-sidebar {
          padding: 7rem 1rem 5rem 1rem;
      }
  
      .docs-sidebar .docs-nav {
          position: relative;
      }
  
      @supports ((position: -webkit-sticky) or (position: sticky)) {
          .docs-sidebar .docs-nav {
              position: sticky;
              position: -webkit-sticky;
              top: 8rem;
          }
  
          .docs-sidebar .docs-nav .nav-item a:focus {
              box-shadow: none;
          }
      }
  }
  
  .docs-nav-clear {
      display: none;
  }
  
  @media screen and (max-width: 600px) {
      .grid-hero h2 {
          font-size: 1.8rem;
      }
  
      .grid-hero .card {
          padding: 0;
      }
  
      .docs-sidebar {
          background: #fff;
          height: 100%;
          left: 0;
          overflow-y: auto;
          padding: 6rem 3rem;
          position: fixed;
          top: 0;
          -webkit-transform: translateX(-100%);
          -ms-transform: translateX(-100%);
          transform: translateX(-100%);
          transition: transform .2s ease, -webkit-transform .2s ease;
          transition: transform .2s ease;
          transition: -webkit-transform .2s ease;
          width: 24rem;
          z-index: 400;
      }
  
      .docs-sidebar:target {
          -webkit-transform: translateX(0);
          -ms-transform: translateX(0);
          transform: translateX(0);
          transition: transform .2s ease, -webkit-transform .2s ease;
          transition: transform .2s ease;
          transition: -webkit-transform .2s ease;
      }
  
      .docs-sidebar:target + .docs-nav-clear {
          display: block;
      }
  
      .docs-nav-clear {
          background: rgba(0, 0, 0, .15);
          display: none;
          height: 100%;
          left: 0;
          position: fixed;
          right: 0;
          top: 0;
          width: 100%;
          z-index: 300;
      }
  }
`;