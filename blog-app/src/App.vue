<template>
  <div id="app">
    <router-view />
    <go-top></go-top>

    <!--<aplayer :audio="audio" :lrcType="1" fixed />-->
  </div>
</template>

<script>
  import '@/assets/scrollbar/scrollbar.css'

  /**
   * import kanbanA from '@/components/kanban/kanbanA'
   * import '@/kanban/font-awesome-4.7.0/css/font-awesome.min.css'
   */

  import GoTop from '@/components/gotop/GoTop'

  import MouseMove from '@/components/mouse/mousemove'
  import MouseClick from '@/components/mouse/mouseclick'



  export default {
    name: 'App',
    components: { GoTop },

  }
</script>

<style>
  * {
    margin: 0;
    padding: 0;
  }

  body {
    background-color: #f5f5f5;
    font-weight: 400;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    line-height: 1.5;
  }

  a {
    cursor: pointer;
    text-decoration: none;
    transition: none 86ms ease-out;
  }

  a:hover {
    color: #5FB878;
  }

  .me-area {
    background-color: rgb(255 255 255 / 80%);
  }

  html {
    height: 100%;
  }

  body {
    min-height: 100%;
  }

  body {
    position: relative;
  }

  .me-pull-right {
    float: right;
  }
</style>
