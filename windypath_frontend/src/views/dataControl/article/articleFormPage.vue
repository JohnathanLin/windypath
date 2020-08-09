<template>
    <div class="article-form-page">
      <el-card shadow="never" v-loading="loading">
        <el-page-header @back="goBack" :content="pageTitle"></el-page-header>
        <br/>

        <el-form :model="articleForm" :rules="rules" ref="ruleForm" label-position="left" label-width="150px" class="demo-ruleForm" size="small">
          <el-form-item label="文章标题（中文）"  prop="titleCn">
            <el-input v-model="articleForm.titleCn" placeholder="请输入文章标题（中文）"></el-input>
          </el-form-item>
          <el-form-item label="文章标题（英文）"  prop="titleCn">
            <el-input v-model="articleForm.titleEn" placeholder="请输入文章标题（英文）"></el-input>
          </el-form-item>
          <el-form-item label="文章状态" prop="status">
            <el-select v-model="articleForm.status" placeholder="请选择应用状态">
              <el-option v-for="(item, i) in searchStatusOptions" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
<!--          <el-form-item label="文章头图" prop="picture">-->
<!--            <el-select v-model="articleForm.picture" placeholder="请输入文章标题（中文）"></el-select>-->
<!--          </el-form-item>-->
          <el-form-item label="允许评论" prop="allowComment">
            <el-switch v-model="articleForm.allowCommentSwitch" ></el-switch>
          </el-form-item>

        </el-form>
        <div class="edit-title">文章正文（中文）</div>
        <mavon-editor
          style="width: 100%;"
          class="mavonEditor"
          :toolbars="toolbars"
          v-model="articleForm.contentCn"
          @imgAdd="handleEditorImgAddCn"
        ></mavon-editor>
        <div class="edit-title">文章正文（英文）</div>
        <mavon-editor
          style="width: 100%;"
          class="mavonEditor"
          :toolbars="toolbars"
          v-model="articleForm.contentEn"
          @imgAdd="handleEditorImgAddEn"
        ></mavon-editor>
      </el-card>
      <div class="bottom-line">
        <el-button type="primary" size="small" @click="handleSubmit">确定</el-button>
        <el-button type="danger" size="small" @click="handleCancel" style="float: right;">取消</el-button>
      </div>
    </div>
</template>
<script>
  import { mavonEditor } from "mavon-editor";
  import "mavon-editor/dist/css/index.css";
  import { globalConst } from "@/utils/config";
  import axios from 'axios'
  import { getArticleById, saveArticle, editArticle } from '@/api/article'
    export default {
        name: "articleFormPage",
      components: {
        mavonEditor
      },
      data() {
        return {
          // 文章新增/编辑页标题
          pageTitle: '',
          // 表单类型（新增add，编辑edit）
          formType: '',
          // 文章表单
          articleForm: {
            // id
            id: '',
            // 文章中文标题
            titleCn: '',
            // 文章英文标题
            titleEn: '',
            // 文章状态
            status: '',
            // 文章头图
            picture: '',
            // 是否允许评论
            allowComment: '',
            // 文章正文（中文）
            contentCn: '',
            // 文章正文（英文）
            contentEn: '',
            // TODO 分类和标签
            // 是否允许评论的开关
            allowCommentSwitch: true,
          },
          // 加载状态
          loading: false,
          // 状态下拉选项框
          searchStatusOptions: [
            {label: '正常', value: 0},
            {label: '隐藏', value: 1},
            {label: '草稿', value: -1},
          ],
          // mavon-editor编辑器的配置
          toolbars: {
            bold:        true, // 粗体
            italic:      true, // 斜体
            header:      true, // 标题
            underline:   true, // 下划线
            mark:        true, // 标记
            superscript: true, // 上角标
            quote:       true, // 引用
            ol:          true, // 有序列表
            link:        true, // 链接
            imagelink:   true, // 图片链接
            help:        true, // 帮助
            code:        true, // code
            subfield:    true, // 是否需要分栏
            fullscreen:  true, // 全屏编辑
            readmodel:   true, // 沉浸式阅读
            undo:        true, // 上一步
            trash:       true, // 清空
            save:        true, // 保存（触发events中的save事件）
            navigation:  true // 导航目录
          },
          // 图片列表（中文文章）
          imgFileCn: [],
          // 表单验证规则
          rules: {
            titleCn: [
              { required: true, message: '请输入文章中文标题', trigger: 'blur' },
            ],
            titleEn: [
              { required: true, message: '请输入文章英文标题', trigger: 'blur' },
            ]
          }
        }
      },
      methods: {
          // 返回键
        goBack() {
          this.$confirm('您还未保存，是否退出编辑文章页?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$emit('goBack')
          })
        },
        // 初始化表单页
        initForm(formType, row) {
          this.loading = true
          this.clearForm()
          this.formType = formType
          if (this.formType === 'add') {
            this.pageTitle = '新增文章'
            this.loading = false
          } else if (this.formType === 'edit') {
            this.pageTitle = '编辑文章'
            getArticleById(row.id).then(response => {
              let data = response.data
              this.editLoadData(data)
              this.loading = false
            })
          }
        },
        // 读取编辑的数据
        editLoadData(row) {
          this.articleForm.id = row.id
          this.articleForm.titleCn = row.titleCn
          this.articleForm.titleEn = row.titleEn
          this.articleForm.status = row.status
          this.articleForm.picture = row.picture
          this.articleForm.allowComment = row.allowComment
          this.articleForm.contentCn = row.contentCn
          this.articleForm.contentEn = row.contentEn
          if (this.articleForm.allowComment === 1) {
            this.articleForm.allowCommentSwitch = false
          }
        },
        // 清空表单
        clearForm() {
          this.articleForm.id = ''
          this.articleForm.titleCn = ''
          this.articleForm.titleEn = ''
          this.articleForm.status = ''
          this.articleForm.picture = ''
          this.articleForm.allowComment = ''
          this.articleForm.contentCn = ''
          this.articleForm.contentEn = ''
          this.articleForm.allowCommentSwitch = true
          // 打开时清空之前的表单验证
          this.$refs['ruleForm'].clearValidate()
        },
        // 上传图片（中文文章）
        handleEditorImgAddCn (pos, $file) {
          let formdata = new FormData()
          formdata.append('file', $file)
          this.imgFileCn[pos] = $file
          let instance = axios.create({
            withCredentials: true,
          })
          instance.post(globalConst().backendAddress + '/file/upload', formdata).then(res => {
            if (res.data.code === 200) {
              this.$message.success('上传成功')
              let url = globalConst().backendAddress + '/file/'+ res.data.data
              let name = $file.name
              if (name.includes('-')) {
                name = name.replace(/-/g, '')
              }
              let content = this.articleForm.contentCn
              // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)  这里是必须要有的
              if (content.includes(name)) {
                let oStr = `(${pos})`
                let nStr = `(${url})`
                let index = content.indexOf(oStr)
                let str = content.replace(oStr, '')
                let insertStr = (soure, start, newStr) => {
                  return soure.slice(0, start) + newStr + soure.slice(start)
                }
                this.articleForm.contentCn = insertStr(str, index, nStr)
              }
            } else {
              this.$message.error(res.data)
            }
          })
        },
        // 上传图片（中文文章）
        handleEditorImgAddEn (pos, $file) {
          let formdata = new FormData()
          formdata.append('file', $file)
          this.imgFileCn[pos] = $file
          let instance = axios.create({
            withCredentials: true,
          })
          instance.post(globalConst().backendAddress + '/file/upload', formdata).then(res => {
            if (res.data.code === 200) {
              this.$message.success('上传成功')
              let url = globalConst().backendAddress + '/file/'+ res.data.data
              let name = $file.name
              if (name.includes('-')) {
                name = name.replace(/-/g, '')
              }
              let content = this.articleForm.contentEn
              // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)  这里是必须要有的
              if (content.includes(name)) {
                let oStr = `(${pos})`
                let nStr = `(${url})`
                let index = content.indexOf(oStr)
                let str = content.replace(oStr, '')
                let insertStr = (soure, start, newStr) => {
                  return soure.slice(0, start) + newStr + soure.slice(start)
                }
                this.articleForm.contentEn = insertStr(str, index, nStr)
              }
            } else {
              this.$message.error(res.data)
            }
          })
        },
        // 确定保存文章
        handleSubmit() {
          // 处理是否可以评论
          if (this.articleForm.allowCommentSwitch) {
            this.articleForm.allowComment = 0
          } else {
            this.articleForm.allowComment = 1
          }
          // TODO 可能要处理标签和分类
          if (this.formType === 'add') {
            saveArticle(this.articleForm).then(response => {
              this.$message.success(response.message)
              this.$emit('goBack')
            })
          } else {
            editArticle(this.articleForm).then(response => {
              this.$message.success(response.message)
              this.$emit('goBack')
            })
          }
        },
        // 取消保存
        handleCancel() {
          this.goBack()
        }
      }
    }
</script>

<style scoped>
  .v-note-wrapper {
    z-index: auto;
  }
  .edit-title {
    padding: 15px 0;
    vertical-align: middle;
    float: left;
    font-size: 14px;
    color: #606266;
  }
  .bottom-line {
    width: 100%;
    padding: 10px 0;
  }
</style>
