/*global __dirname, process*/
import webpack from 'webpack';
import ExtractTextPlugin from 'extract-text-webpack-plugin';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import autoprefixer from 'autoprefixer';
import OfflinePlugin from 'offline-plugin';
import path from 'path';
import V8LazyParseWebpackPlugin from 'v8-lazy-parse-webpack-plugin';
const ENV = process.env.NODE_ENV || 'development';

const CSS_MAPS = ENV !== 'production';

export default {
  entry: {
    client: 'client.js',
    server: 'server.js'
  },
  output: {
    path: path.join(__dirname, 'static'),
    filename: '[name].js'
  },
  resolve: {
    extensions: ['', '.js', '.json', '.less'],
    modulesDirectories: [
      path.resolve(__dirname, 'app'),
      path.resolve(__dirname, 'node_modules'),
      'node_modules'
    ],
    alias: {
      components: path.resolve(__dirname, 'app/components'),
      style: path.resolve(__dirname, 'app/style')
    }
  },
  module: {
    preLoaders: [
      {
        test: /\.jsx?$/,
        exclude: path.resolve(__dirname, 'app'),
        loader: 'source-map-loader'
      }
    ],
    loaders: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        loader: 'babel-loader'
      },
      {
        test: /\.(less|css)$/,
        include: path.resolve(__dirname, 'app'),
        loader: ExtractTextPlugin.extract('style?singleton', [
          'isomorphic-style-loader',
          `css-loader?modules&importLoaders=1&sourceMap=${CSS_MAPS}`,
          'postcss-loader',
          `less-loader?sourceMap=${CSS_MAPS}`
        ].join('!'))
      },
      {
        test: /\.json$/,
        loader: 'json-loader'
      },
      {
        test: /\.(xml|html|txt|md)$/,
        loader: 'raw-loader'
      },
      {
        test: /\.(svg|woff2?|ttf|eot|jpe?g|png|gif)(\?.*)?$/i,
        loader: ENV === 'production' ? 'file-loader' : 'url-loader?limit=100000'
      }
    ]
  },
  postcss: [autoprefixer({browsers: ['last 5 versions']})],
  plugins: ([
    new webpack.NoErrorsPlugin(),
    new ExtractTextPlugin('[name].css', {
      allChunks: true,
      disable: ENV !== 'production'
    }),
    new webpack.DefinePlugin({
      'process.env.NODE_ENV': JSON.stringify(ENV)
    }),
    new HtmlWebpackPlugin({
      hash: true,
      template: path.resolve(__dirname, 'app/index.html'),
      filename: 'index.html',
      minify: {
        collapseWhitespace: true
      },
      excludeChunks: ['server']
    })
  ]).concat(ENV === 'production'
    ? [
      new V8LazyParseWebpackPlugin(),
      new webpack.optimize.UglifyJsPlugin({
        output: {
          comments: false
        },
        compress: {
          warnings: false,
          conditionals: true,
          unused: true,
          comparisons: true,
          sequences: true,
          dead_code: true,
          evaluate: true,
          if_return: true,
          join_vars: true,
          negate_iife: false
        }
      }),
      new OfflinePlugin({
        relativePaths: false,
        AppCache: false,
        excludes: ['_redirects'],
        ServiceWorker: {
          events: true
        },
        cacheMaps: [
          {
            match: /.*/,
            to: '/',
            requestTypes: ['navigate']
          }
        ],
        publicPath: '/'
      })
    ] : []),
  stats: {colors: true},
  node: {
    global: true,
    process: false,
    Buffer: false,
    __filename: false,
    __dirname: false,
    setImmediate: false
  },
  devtool: ENV === 'production' ? 'source-map' : 'cheap-module-eval-source-map'
};