module.exports = {
    devServer:{
        port:8081,
        proxy: {
            '/hotel':{
                target:'http://localhost:8080/',               //需要跨域的url
                ws:true,                           //代理webSocket
                changeOrigin:true,                        //允许跨域
                pathRewrite:{
                    '^/hotel':'hotel'                       //重写路径
                }
            }
        }
    }
}