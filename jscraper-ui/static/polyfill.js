/*eslint no-unused-vars: ["error", { "varsIgnorePattern": "console" }]*/
var window = this;
var global = this;
var process = {env:{}};
var console = {
  error: print,
  debug: print,
  warn: print,
  log: print
};
window.setTimeout = function() {};
window.Promise = {
  resolve: function () {},
  reject: function() {}
};