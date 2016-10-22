/**
 * Created by Zhukov on 22.10.2016.
 */
var webpack = require('webpack');
var path = require('path');

var BUILD_DIR = path.resolve(__dirname, 'public/javascripts');
var APP_DIR = path.resolve(__dirname, 'frontend');

var config = {
    entry: APP_DIR + '/index.js',
    output: {
        path: BUILD_DIR,
        filename: 'bundle.js'
    },
    module : {
        loaders : [
            {
                test : /\.js?/,
                include : APP_DIR,
                exclude: /node_modules/,
                loader : 'babel'
            }
        ]
    }
};


module.exports = config;