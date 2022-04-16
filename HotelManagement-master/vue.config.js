module.exports = {
    baseUrl: './',
    assetsDir: 'static',
    outputDir: 'dist',
    publicPath: './',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/hotel':{
                target:'http://localhost:8080/',
                changeOrigin:true,
                pathRewrite:{
                    '^/hotel':'/hotel'
                }
            }
        }
    }
}