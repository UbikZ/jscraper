const darkGrey = '#333';

const totalColumns = 12;
const columnWidth = 100 / totalColumns;
const columnMargin = 4;

const gridColumnWidthValue = (n) => columnWidth * n - (columnWidth / 100 * (totalColumns - n) / totalColumns);
const gridOffsetLengthValue = (n) => gridColumnWidthValue(n) + columnMargin;

export const variables = {
  breakpoints: {
    largerThanMobile: '400px',
    largerThanPhablet: '550px',
    largerThanTablet: '750px',
    largerThanDesktop: '1000px',
    largerThanDesktophd: '1200px'
  },
  colors: {
    lightGrey: '#e1e1e1',
    darkGrey,
    primary: '#33c3f0',
    secondary: `lighten(${darkGrey}, 13.5%)`,
    border: '#bbb',
    link: '#1eaedb',
    font: '#222'
  },
  typo: {
    font: '"Raleway", "HelveticaNeue", "Helvetica Neue", Helvetica, Arial, sans-serif'
  },
  grid: {
    container: {
      width: '960px',
      widthLargerThanMobile: '85%',
      widthLargerThanPhablet: '80%'
    },
    columns: {
      total: totalColumns,
      width: `${columnWidth}px`,
      margin: `${columnMargin}%`
    }
  },
  misc: {
    global: {
      radius: '4px'
    }
  },
  func: {
    gridColumnWidth: (n) => `width: ${gridColumnWidthValue}px`,
    gridOffsetLength: (n) => `margin-left: ${gridOffsetLengthValue}px`
  }
};