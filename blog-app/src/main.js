
import Vue from 'vue'
import App from './App'

import router from './router'
import store from './store'

import lodash from 'lodash'

import ElementUI from 'element-ui'
import '@/assets/theme/index.css'

import '@/assets/icon/iconfont.css'

import { formatTime } from './utils/time'

// 音乐播放器
import APlayer from '@moefe/vue-aplayer'
Vue.use(APlayer, {
    defaultCover: 'https://github.com/u3u.png',
    productionTip: true,
})
let vm3 = new Vue({
        el: '#aplayer',
        data: {
            id: '7510894589', //这里是我音乐盒的id  查找自己音乐盒就到网易云web端查看url的id值
            uri:'getPlayList', // 自己设置的请求地址
            audio: [] // 储存返回来的信息
        },
        mounted(){
            var playerOption = { // 设置播放器基本参数
                container: document.getElementById('aplayer'),
                fixed: true,
                volume:0.7,
                order:'list',
                theme:'#988bbc',
                listFolded:false,
                audio: []
            }
            this.initAplayer(playerOption,this.id) //参数放到初始化方法中
        },
        methods:{
            initAplayer(playerOption,id){
                var url = '/'+this.uri+'?listId='+id
                //这里就是axios 请求的方式
                axios.get(url).then(response => {
                    console.log(response.data)
                    playerOption.audio=response.data
                    var ap = new APlayer(playerOption)
                }).catch(function (error) {
                    console.log(error)
                    alert('获取歌单信息失败！')
                })
            }
        }
    })



Vue.config.productionTip = false

Vue.use(ElementUI)

Object.defineProperty(Vue.prototype, '$_', { value: lodash })


Vue.directive('title', function (el, binding) {
  document.title = el.dataset.title
})
// 格式化时间
Vue.filter('format', formatTime)

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
