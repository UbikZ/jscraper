import {injectGlobal} from 'styled-components';
import {reset} from './reset';
import {base} from './base';

injectGlobal`
  ${reset}
  ${base}
`;