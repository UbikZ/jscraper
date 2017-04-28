import {variables} from './variables';

export const base = `
    .container {
      position: relative;
      width: 100%;
      max-width: ${variables.grid.container.width};
      margin: 0 auto;
      padding: 0 20px;
      box-sizing: border-box;
    }
    
    .column,
    .columns {
      width: 100%;
      float: left;
      box-sizing: border-box;
    }
       
    html {
      font-size: 62.5%;
    }
    
    body {
      font-size: 1.5em;
      line-height: 1.6;
      font-weight: 400;
      font-family: ${variables.typo.font};
      color: ${variables.colors.font};
    }
    
    h1, h2, h3, h4, h5, h6 {
      margin-top: 0;
      margin-bottom: 2rem;
      font-weight: 300;
    }
    
    h1 { font-size: 4.0rem; line-height: 1.2;  letter-spacing: -.1rem;  }
    h2 { font-size: 3.6rem; line-height: 1.25; letter-spacing: -.1rem;  }
    h3 { font-size: 3.0rem; line-height: 1.3;  letter-spacing: -.1rem;  }
    h4 { font-size: 2.4rem; line-height: 1.35; letter-spacing: -.08rem; }
    h5 { font-size: 1.8rem; line-height: 1.5;  letter-spacing: -.05rem; }
    h6 { font-size: 1.5rem; line-height: 1.6;  letter-spacing: 0;       }
    
    p {
      margin-top: 0;
    }
    
    a {
      color: ${variables.colors.link};
      &:hover {
        color: darken(${variables.colors.link}, 5%);
      }
    }
    
    .button,
    button {
      display: inline-block;
      height: 38px;
      padding: 0 30px;
      color: ${variables.colors.secondary};
      text-align: center;
      font-size: 11px;
      font-weight: 600;
      line-height: 38px;
      letter-spacing: .1rem;
      text-transform: uppercase;
      text-decoration: none;
      white-space: nowrap;
      background-color: transparent;
      border-radius: ${variables.misc.global.radius};
      border: 1px solid ${variables.colors.border};
      cursor: pointer;
      box-sizing: border-box;
    }
    
    input {
      &[type="submit"],
      &[type="reset"],
      &[type="button"] {
        display: inline-block;
        height: 38px;
        padding: 0 30px;
        color: ${variables.colors.secondary};
        text-align: center;
        font-size: 11px;
        font-weight: 600;
        line-height: 38px;
        letter-spacing: .1rem;
        text-transform: uppercase;
        text-decoration: none;
        white-space: nowrap;
        background-color: transparent;
        border-radius: ${variables.misc.global.radius};
        border: 1px solid ${variables.colors.border};
        cursor: pointer;
        box-sizing: border-box;
      }
    }
    
    .button:hover,
    button:hover {
      color: ${variables.colors.darkGrey};
      border-color: lighten(${variables.colors.darkGrey}, 33.3%);
      outline: 0;
    }
    
    input {
      &[type="submit"]:hover,
      &[type="reset"]:hover,
      &[type="button"]:hover {
        color: ${variables.colors.darkGrey};
        border-color: lighten(${variables.colors.darkGrey}, 33.3%);
        outline: 0;
      }
    }
    
    .button:focus,
    button:focus {
      color: ${variables.colors.darkGrey};
      border-color: lighten(${variables.colors.darkGrey}, 33.3%);
      outline: 0;
    }
    
    input {
      &[type="submit"]:focus,
      &[type="reset"]:focus,
      &[type="button"]:focus {
        color: ${variables.colors.darkGrey};
        border-color: lighten(${variables.colors.darkGrey}, 33.3%);
        outline: 0;
      }
    }
    
    .button.button-primary,
    button.button-primary {
      color: #fff;
      background-color: ${variables.colors.primary};
      border-color: ${variables.colors.primary};
    }
    
    input {
      &[type="submit"].button-primary,
      &[type="reset"].button-primary,
      &[type="button"].button-primary {
        color: #fff;
        background-color: ${variables.colors.primary};
        border-color: ${variables.colors.primary};
      }
    }
    
    .button.button-primary:hover,
    button.button-primary:hover {
      color: #fff;
      background-color: ${variables.colors.link};
      border-color: ${variables.colors.link};
    }
    
    input {
      &[type="submit"].button-primary:hover,
      &[type="reset"].button-primary:hover,
      &[type="button"].button-primary:hover {
        color: #fff;
        background-color: ${variables.colors.link};
        border-color: ${variables.colors.link};
      }
    }
    
    .button.button-primary:focus,
    button.button-primary:focus {
      color: #fff;
      background-color: ${variables.colors.link};
      border-color: ${variables.colors.link};
    }
    
    input {
      &[type="submit"].button-primary:focus,
      &[type="reset"].button-primary:focus,
      &[type="button"].button-primary:focus {
        color: #fff;
        background-color: ${variables.colors.link};
        border-color: ${variables.colors.link};
      }
      &[type="email"],
      &[type="number"],
      &[type="search"],
      &[type="text"],
      &[type="tel"],
      &[type="url"],
      &[type="password"] {
        height: 38px;
        padding: 6px 10px; // The 6px vertically centers text on FF, ignored by Webkit
        background-color: #fff;
        border: 1px solid lighten(${variables.colors.border}, 8.8%);
        border-radius: ${variables.misc.global.radius};
        box-shadow: none;
        box-sizing: border-box;
      }
    }
    
    textarea,
    select {
      height: 38px;
      padding: 6px 10px; /* The 6px vertically centers text on FF, ignored by Webkit */
      background-color: #fff;
      border: 1px solid lighten(${variables.colors.border}, 8.8%);
      border-radius: ${variables.misc.global.radius};
      box-shadow: none;
      box-sizing: border-box;
    }
    
    /* Removes awkward default styles on some inputs for iOS */
    input {
      &[type="email"],
      &[type="number"],
      &[type="search"],
      &[type="text"],
      &[type="tel"],
      &[type="url"],
      &[type="password"] {
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
      }
    }
    
    textarea {
      -webkit-appearance: none;
      -moz-appearance: none;
      appearance: none;
      min-height: 65px;
      padding-top: 6px;
      padding-bottom: 6px;
    }
    
    input {
      &[type="email"]:focus,
      &[type="number"]:focus,
      &[type="search"]:focus,
      &[type="text"]:focus,
      &[type="tel"]:focus,
      &[type="url"]:focus,
      &[type="password"]:focus {
        border: 1px solid ${variables.colors.primary};
        outline: 0;
      }
    }
    
    textarea:focus,
    select:focus {
      border: 1px solid ${variables.colors.primary};
      outline: 0;
    }
    
    label,
    legend {
      display: block;
      margin-bottom: .5rem;
      font-weight: 600;
    }
    
    fieldset {
      padding: 0;
      border-width: 0;
    }
    
    input {
      &[type="checkbox"],
      &[type="radio"] {
        display: inline;
      }
    }
    
    label > .label-body {
      display: inline-block;
      margin-left: .5rem;
      font-weight: normal;
    }
    
    ul {
      list-style: circle inside;
    }
    
    ol {
      list-style: decimal inside;
      padding-left: 0;
      margin-top: 0;
    }
    
    ul {
      padding-left: 0;
      margin-top: 0;
      ul, ol {
        margin: 1.5rem 0 1.5rem 3rem;
        font-size: 90%;
      }
    }
    
    ol {
      ol, ul {
        margin: 1.5rem 0 1.5rem 3rem;
        font-size: 90%;
      }
    }
    
    li {
      margin-bottom: 1rem;
    }
    
    code {
      padding: .2rem .5rem;
      margin: 0 .2rem;
      font-size: 90%;
      white-space: nowrap;
      background: lighten(${variables.colors.lightGrey}, 6.4%);
      border: 1px solid ${variables.colors.lightGrey};
      border-radius: ${variables.misc.global.radius};
    }
    
    pre > code {
      display: block;
      padding: 1rem 1.5rem;
      white-space: pre;
    }
    
    th,
    td {
      padding: 12px 15px;
      text-align: left;
      border-bottom: 1px solid ${variables.colors.lightGrey};
    }
    
    th:first-child,
    td:first-child {
      padding-left: 0;
    }
    
    th:last-child,
    td:last-child {
      padding-right: 0;
    }
    
    button,
    .button {
      margin-bottom: 1rem;
    }
    
    input,
    textarea,
    select,
    fieldset {
      margin-bottom: 1.5rem;
    }
    
    pre,
    blockquote,
    dl,
    figure,
    table,
    p,
    ul,
    ol,
    form {
      margin-bottom: 2.5rem;
    }
    
    .u-full-width {
      width: 100%;
      box-sizing: border-box;
    }
    
    .u-max-full-width {
      max-width: 100%;
      box-sizing: border-box;
    }
    
    .u-pull-right {
      float: right;
    }
    
    .u-pull-left {
      float: left;
    }
    
    hr {
      margin-top: 3rem;
      margin-bottom: 3.5rem;
      border-width: 0;
      border-top: 1px solid ${variables.colors.lightGrey};
    }
    
    .container:after,
    .row:after,
    .u-cf {
      content: "";
      display: table;
      clear: both;
    }   
`;