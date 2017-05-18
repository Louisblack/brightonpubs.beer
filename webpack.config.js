const webpack = require("webpack");

module.exports = {
    entry: './app/assets/javascripts/entry.js',
    output: {
        path: __dirname + '/public/compiled',
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                include: /ui/,
                query: {
                    presets: ['es2015', 'stage-0', 'react']
                }
            }
        ]
    }
};