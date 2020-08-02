package com.windypath.blog.common.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.windypath.blog.common.exception.FileOpException;
import com.windypath.blog.common.exception.ParamException;
import com.windypath.blog.common.service.FileService;
import com.windypath.blog.common.utils.FileUtil;
import com.windypath.blog.common.utils.ImageUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 20:30
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private GridFsTemplate gridFsTemplate;
    @Resource
    private GridFSBucket gridFSBucket;

    @Override
    public String save(InputStream inputStream, String fileName) {
        return save(inputStream, fileName, null);
    }

    @Override
    public String save(InputStream inputStream, String fileName, Object metadata) {
        String fileId;
        try {
            fileId = gridFsTemplate.store(inputStream, fileName, metadata).toString();
        } catch (Exception e) {
            throw new FileOpException("【文件上传错误】" + e.getMessage());
        }
        return fileId;
    }

    @Override
    public void findById(String fileId, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(fileId)) {
            throw new ParamException("文件ID不能为空");
        }
        Query query = Query.query(Criteria.where("_id").is(fileId));
        GridFSFile file = gridFsTemplate.findOne(query);
        if (file == null) {
            throw new FileOpException("【文件下载错误】查无文件信息");
        }
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(file.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(file, gridFSDownloadStream);
        try {
            InputStream inputStream = gridFsResource.getInputStream();
            if (!ImageUtil.isImage(file.getFilename())) {
                response.addHeader("Content-Disposition", "attachment; filename=" + FileUtil.handleFileName(request, file.getFilename()));
                response.setContentType("application/octet-stream; charset=UTF-8");
            }
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            throw new FileOpException("【文件下载错误】" + e.getMessage());
        }
    }

    @Override
    public void delFile(List<String> fileIdList) {
        if (CollectionUtils.isEmpty(fileIdList)) {
            throw new ParamException("待删除文件ID不能为空");
        }
        try {
            for (String fileId : fileIdList) {
                Query query = Query.query(Criteria.where("_id").is(fileId));
                gridFsTemplate.delete(query);
            }
        } catch (Exception e) {
            throw new FileOpException("【文件删除错误】" + e.getMessage());
        }
    }
}
