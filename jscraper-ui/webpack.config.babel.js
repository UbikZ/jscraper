/*global __dirname, process*/
import webpack from "webpack";
import path from "path";
import ExtractTextPlugin from "extract-text-webpack-plugin";
import HtmlWebpackPlugin from "html-webpack-plugin";
import PrepackWebpackPlugin from "prepack-webpack-plugin";
import SWPrecacheWebpackPlugin from "sw-precache-webpack-plugin";
import autoprefixer from "autoprefixer";

const sourcePath = path.join(__dirname, './app');
const buildDirectory = path.join(__dirname, './static');
const modulesPath = path.resolve(__dirname, 'node_modules');

const stats = {
  assets: true,
  children: false,
  chunks: false,
  hash: false,
  modules: false,
  publicPath: false,
  timings: true,
  version: false,
  warnings: true,
  colors: {
    green: '\u001b[32m'
  }
};

export default (env) => {
  const nodeEnv = env && env.prod ? 'production' : 'development';
  const isProduction = nodeEnv === 'production';
  const serviceWorkerBuild = env && env.sw;

  const plugins = [
    new webpack.optimize.CommonsChunkPlugin({
      name: 'vendor',
      filename: 'vendor.js',
      minChunks(module) {
        const context = module.context;
        return context && context.indexOf('node_modules') >= 0;
      }
    }),
    new ExtractTextPlugin('style.css'),
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: JSON.stringify(nodeEnv)
      }
    }),
    new webpack.NamedModulesPlugin(),
    new HtmlWebpackPlugin({
      template: path.join(sourcePath, 'index.html'),
      path: buildDirectory,
      filename: 'index.html'
    }),
    new webpack.LoaderOptionsPlugin({
      options: {
        postcss: [
          autoprefixer({
            browsers: [
              'last 3 version',
              'ie >= 10'
            ]
          })
        ],
        context: sourcePath
      }
    }),
    new PrepackWebpackPlugin()
  ];

  const rules = [
    {
      test: /\.(jsx?)$/,
      exclude: /node_modules/,
      use: 'babel-loader'
    }
  ];

  if (isProduction) {
    plugins.push(
      new webpack.optimize.UglifyJsPlugin({
        compress: {
          warnings: false,
          screw_ie8: true,
          conditionals: true,
          unused: true,
          comparisons: true,
          sequences: true,
          dead_code: true,
          evaluate: true,
          if_return: true,
          join_vars: true,
        },
        output: {
          comments: false,
        }
      })
    );

    rules.push(
      {
        test: /\.css$/,
        include: [sourcePath, modulesPath],
        loader: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader!postcss-loader',
        })
      }
    );
  } else {
    rules.push(
      {
        test: /\.css$/,
        include: [sourcePath, modulesPath],
        loader: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          use: 'css-loader!postcss-loader?sourceMap',
        })
      }
    );
  }

  if (serviceWorkerBuild) {
    plugins.push(new SWPrecacheWebpackPlugin(
      {
        cacheId: 'jscraper-app',
        filename: 'sw.js',
        minify: true,
        runtimeCaching: [{
          urlPattern: /\/api\//,
          handler: 'networkFirst'
        }]
      }
    ));
  }

  return {
    devtool: isProduction ? false : 'source-map',
    context: sourcePath,
    entry: {
      main: 'index.js'
    },
    output: {
      path: buildDirectory,
      publicPath: '/',
      filename: 'app.js'
    },
    module: {
      rules
    },
    resolve: {
      extensions: ['.js', '.css'],
      modules: [
        sourcePath,
        'node_modules',
        modulesPath
      ]
    },
    plugins,
    performance: isProduction && {
      maxAssetSize: 300000,
      maxEntrypointSize: 300000,
      hints: 'warning'
    },
    stats
  };
};