<template>
    <div class="article app-container">
      <div v-show="!showArticleFormPage">
      <el-card class="card">
        <el-row>
          <el-col :span="18">
            <el-form :inline="true" :model="searchModel" size="mini">
              <el-form-item label="文章标题（中文）">
                <el-input v-model="searchModel.titleCn" placeholder="请输入文章标题（中文）"></el-input>
              </el-form-item>
              <el-form-item label="文章分类">
                <!-- <el-select 多选 -->
<!--                <el-input v-model="searchModel.categoryIdList" placeholder="请输入文章标题（中文）"></el-input>-->
              </el-form-item>
              <el-form-item label="文章状态">
                <el-select v-model="searchModel.status" placeholder="请选择应用状态">
                  <el-option v-for="(item, i) in searchStatusOptions" :label="item.label" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="handleSearch" icon="el-icon-search">查询</el-button>
                <el-button type="info" @click="handleReset" icon="el-icon-refresh">重置</el-button>
              </el-form-item>

            </el-form>
          </el-col>
          <el-col :span="6">
            <el-button style="float:right; margin: 0 15px 0 15px;" type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="handleAdd">新增文章</el-button>
          </el-col>
        </el-row>
        <el-table
          v-loading.body="tableLoading"
          :data="dataList"
          style="width: 100%">
          <el-table-column
            type="index"
            :index="indexMethod"
            label="序号"
            align="center"
            width="60">
          </el-table-column>
          <el-table-column
            label="文章ID"
            align="center"
            prop="id"
            width="100"
          >
          </el-table-column>
          <el-table-column
            label="文章标题"
            align="center"

          >
            <template slot-scope="scope">
              <div><span style="font-weight: bold;">中文标题：</span>{{ scope.row.titleCn }}</div>
              <div><span style="font-weight: bold;">英文标题：</span>{{ scope.row.titleEn }}</div>
            </template>
          </el-table-column>
          <el-table-column
            label="分类名称"
            align="center"
            prop="categoryName"
            width="100"
          >
          </el-table-column>
          <el-table-column
            label="作者"
            align="center"
            prop="username"
            width="100"
          >
          </el-table-column>
<!--          <el-table-column-->
<!--            label="创建时间"-->
<!--            align="center"-->
<!--            prop="createTime"-->
<!--            width="150"-->
<!--          >-->
<!--          </el-table-column>-->
<!--          <el-table-column-->
<!--            label="编辑时间"-->
<!--            align="center"-->
<!--            prop="createTime"-->
<!--            width="150"-->
<!--          >-->
<!--          </el-table-column>-->

          <el-table-column
            label="文章状态"
            align="center"
            prop="status"
            width="80"
          >
            <template slot-scope="scope">
              <el-tag type="danger" size="small" v-if="scope.row.status === 1">隐藏</el-tag>
              <el-tag type="success" size="small" v-if="scope.row.status === 0">正常</el-tag>
              <el-tag type="warning" size="small" v-if="scope.row.status === -1">草稿</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            width="200"
            align="center">
            <template slot-scope="scope">
              <el-button type="warning" icon="el-icon-edit" size="mini" @click="handleEdit(scope.row)" >编辑</el-button>
              <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="page-selection" style="margin: 10px 0px; float: right;">
          <el-pagination
            :current-page.sync="currentPage"
            :page-sizes="[10, 20, 30, 40, 50, 500]"
            :page-size="limit"
            :total="totalNumber"
            background
            layout="sizes,total, prev, pager, next, jumper"
            @current-change="handleCurrentChange"/>
        </div>
      </el-card>
      </div>
      <div v-show="showArticleFormPage">
        <article-form-page ref="articleFormPage" @goBack="goBack"></article-form-page>
      </div>
    </div>
</template>

<script>
  import { findArticleByPage, deleteArticle } from '@/api/article'
  import ArticleFormPage from "@/views/dataControl/article/articleFormPage";
    export default {
        name: "article",
      components: {ArticleFormPage},
      data() {
          return {
            // 查询条件
            searchModel: {
              // 中文标题
              titleCn: '',
              // 分类ID列表
              categoryIdList: [],
              // 状态
              status: ''
            },
            // 状态下拉选项框
            searchStatusOptions: [
              {label: '全部', value: ''},
              {label: '正常', value: 0},
              {label: '隐藏', value: 1},
              {label: '草稿', value: -1},
            ],
            // 表格加载
            tableLoading: false,
            // 数据列表
            dataList: [],
            // 当前页
            currentPage: 1,
            // 每页行数
            limit: 10,
            // 本页总数
            totalNumber: 0,
            // 是否展示文章新增/编辑页
            showArticleFormPage: false,
          }
      },
      created() {
        this.fetchData()
      },
      methods: {
        // 序号自增
        indexMethod(index) {
          return (this.currentPage - 1) * 10 + (index + 1)
        },
        // 获取数据
        fetchData() {
            this.tableLoading = true
            findArticleByPage(this.currentPage, this.limit, this.searchModel).then(response => {
              this.dataList = response.data.list
              this.totalNumber = response.data.totalNum
              this.tableLoading = false
            })
        },
          // 执行查询操作
        handleSearch() {
          this.fetchData()
        },
        // 重置搜索条件
        handleReset() {
          this.searchModel.titleCn = ''
          this.searchModel.categoryIdList = []
          this.searchModel.status = ''

          this.fetchData()
        },
        // 新增文章
        handleAdd() {
          this.showArticleFormPage = true
          this.$refs['articleFormPage'].initForm('add', null)

        },
        // 翻页
        handleCurrentChange() {
          this.fetchData()
        },
        // 编辑
        handleEdit(row) {
          this.showArticleFormPage = true
          this.$refs['articleFormPage'].initForm('edit', row)

        },
        // 删除
        handleDelete(row) {
          this.$confirm('您是否要删除文章【'+row['titleCn']+'】?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            deleteArticle(row.id).then(response => {
              this.$message.success('删除成功')
              this.fetchData()
            })
          })
        },
        // 从编辑页返回
        goBack() {
          this.showArticleFormPage = false
          this.fetchData()
        }
      }
    }
</script>

<style lang="scss">

</style>
