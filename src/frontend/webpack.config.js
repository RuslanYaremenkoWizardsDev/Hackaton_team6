let path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const HTMLPlugin = require('html-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

const isDev = process.env.NODE_ENV === 'development'; // проверка режима разработки, будет возвращать true если совпало
const isProd = !isDev; // проверка режима разработки, будет возвращать true если предыдущее false

// функция для наименования файловCopyPlugin в зависимости от мода который выбран
const filename = (ext) => isDev ? `[name].${ext}` : `[name].[contenthash].${ext}`;

module.exports = {
    mode: 'development',
    entry:  {
        home: './js/script.js',
        createAccount: './js/createAccount.js',
        signIn: './js/signIn.js',
        mainPage: './js/mainPage.js',
        localization: './js/localization.js',
    },
    output: {
        filename: `./js/${filename('js')}`, // генерация названия файлов из функции
        library: 'dist', // путь, куда всё сложится
    },

    devServer: {
        contentBase: path.resolve(__dirname, 'dist'),
        open: true,
        compress: true,
        hot: true,
        port: 7070,
    },

    optimization: {
        runtimeChunk: 'single',
        splitChunks: {
            chunks: 'async',
        },
    },

    plugins: [
        new CleanWebpackPlugin(),  // очистка старых файлов, подгрузка новых
        new MiniCssExtractPlugin({
           filename: `./scss/${filename('css')}`,
        }),
        new HTMLPlugin({
            filename: 'mainPage.html',
            template: './mainPage.html',  // путь к файлу html
            minify: {
                collapseWhitespace: isProd,  // в режиме продакшена уберуться все пробелы для сжатия проекта
            },
        }),
        new HTMLPlugin({   
            filename: 'index.html',
            template: './index.html',  // путь к файлу html
            minify: {
                collapseWhitespace: isProd,  // в режиме продакшена уберуться все пробелы для сжатия проекта
            },
        }),
        new HTMLPlugin({   
            filename: 'createAccount.html',
            template: './createAccount.html',  // путь к файлу html
            minify: {
                collapseWhitespace: isProd,  // в режиме продакшена уберуться все пробелы для сжатия проекта
            },
        }),
        // new CopyPlugin({
        //     patterns: [{from:'./img', to: './img'}]  // копируем картинки
        // }),
    ],
    
    devtool: isProd ? false : 'source-map',   // в режиме разработки показывает где записана та или иная строка в исходном коде

    module: {
        rules: [
            {
                test: /\.js$/i,
                exclude: /node_modules/,
                use: ['babel-loader']     // пропускаем js через babel
            },

            {
                test: /\.html$/i,
                loader: 'html-loader',  // динамический загрузчик html
            },

            {
                test: /\.s[ac]ss$/i,       // загрузчики стилей Scss. Работа начинается с конца массива. Scss --> css --> folder
                use: [MiniCssExtractPlugin.loader, 'css-loader', 'sass-loader'] 
            },
            {
                test: /\.css$/i,       // загрузчики стилей Scss. Работа начинается с конца массива. Scss --> css --> folder
                use: ['css-loader', 'style-loader'] 
            },
            // {
            //     test: /\.(png|jpe?g|gif)$/i,
            //     use: [
            //       // {
            //       //   loader: 'file-loader',
            //       //     options: {
            //       //         name: '[path][name].[ext]',
            //       //     },
            //       // },
            //       //   {
            //       //       loader: 'url-loader',
            //       //       options: {
            //       //           limit: 8192,
            //       //       },
            //       //   },
            //     ]
            // }
        ]
    },
}