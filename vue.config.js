const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')
module.exports = {
    devServer: {
        host:'localhost',
        port:80,
        proxy: {
            '/api': {
                // 需要转发的请求
                target: 'http://localhost:8080',          // 目标地址
                ws: true,                // 如果要代理 websockets
                changeOrigin: true,     // 默认false，是否需要改变原始主机头为目标URL
                pathRewrite: {'^/api': ''}// 重写请求path，比如访问的是api/books，那么请求会被解析为/api/3.0/books
            }
        }
    },
    configureWebpack:{
        plugins: [
            AutoImport({
                resolvers: [ElementPlusResolver()],
            }),
            Components({
                resolvers: [ElementPlusResolver()],
            }),
        ]
    },
    lintOnSave:false
}
