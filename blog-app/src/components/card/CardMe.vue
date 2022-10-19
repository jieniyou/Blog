<template>
  <el-card>
    <h1 class="me-author-name">解你忧</h1>
    <div class="me-author-description">
      <span><i class="el-icon-location-outline"></i> &nbsp;四川&你的心里</span>
      <span><i class="me-icon-job"></i> &nbsp;java技术专家</span>
    </div>
    <div class="me-author-tool">
      <i @click="showTool(qq)" :title="qq.title" class="iconfont icon-qq"></i>
      <!-- <i @click="showTool(bilibili)" :title="bilibili.title" class="iconfont icon-bilibili-fill"></i> -->
      <i @click="showTool(cnblogs)" :title="cnblogs.title" class="iconfont"><img width="23px" src="../../assets/icon/cnblogs.png"/></i>
      <i @click="showTool(github)" :title="github.title" class="iconfont"><img width="27px" src="../../assets/icon/github.svg"/></i>
    </div>
  </el-card>

</template>

<script>
  export default {
    name: 'CardMe',
    data() {
      return {
        qq: {
          title: 'QQ',
          message: '<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1799482162&site=qq&menu=yes">1799482162</a>'
          },
        // bilibili: {
        //   title: '哔哩哔哩',
        //   message: '<a target="_blank" href=""></a>'
        // },
        cnblogs:{
          title: '博客园',
          message: '<a target="_blank" href="https://www.cnblogs.com/jieniyou/">解你忧博客园</a>'
        },
        github: {
          title: 'Github',
          message: '<a target="_blank" href="https://jieniyou.gitHub.io">解你忧博客</a>'
        }
      }
    },
    mounted() {
    },
    methods: {
      showTool(tool) {
        this.$message({
          duration: 0,
          showClose: true,
          dangerouslyUseHTMLString: true,
          message: '<strong>' + tool.message + '</strong>'
        })
      }
    }
  }
</script>

<style scoped>
  .me-author-name {
    text-align: center;
    font-size: 30px;
    border-bottom: 1px solid #5FB878;
  }

  .me-author-description {
    padding: 8px 0;
  }

  .me-icon-job {
    padding-left: 16px;
  }

  .me-author-tool {
    text-align: center;
    padding-top: 10px;
  }

  .me-author-tool i {
    cursor: pointer;
    padding: 4px 10px;
    font-size: 30px;
  }
</style>
