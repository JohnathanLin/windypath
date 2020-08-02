package com.windypath.blog;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.windypath.blog.modules.oauth.dao.WpUserMapper;
import com.windypath.blog.modules.oauth.pojo.WpUser;
import com.windypath.blog.utils.RedisUtil;
import net.bytebuddy.asm.Advice;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Autowired
    private WpUserMapper wpUserMapper;
    @Test
    void contextLoads() {

        // 配置查询条件
        BasicDBObject cond1 = new BasicDBObject();
        cond1.append("_id", new ObjectId("5f25273c5d0eba52b0c61df0"));
        FindIterable<Document> findIterable =
                // 执行查询
                mongoTemplate.getCollection("test").find(cond1);
        // 装配查询结果
        MongoCursor<Document> cursor = findIterable.iterator();
        Document document = null;

        while (cursor.hasNext()) {
            document = cursor.next();

        }
        System.out.println(document.toString());
        cursor.close();
    }

    @Test
    void mainFs() throws FileNotFoundException {
        //选择要存储的文件
        File file = new File("D:\\Project\\windypath\\README.md");
        InputStream inputStream = new FileInputStream(file);
        //存储文件并起名称
        ObjectId objectId = gridFsTemplate.store(inputStream, "面试宝典");
        String id = objectId.toString();
        //获取到文件的id，可以从数据库中查找
        System.out.println(id);

    }

    @Test
    void mainDown() {
        String id = "5f26aca532364f5611065fe5";
        //根据id查找文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        //打开下载流对象
        GridFSDownloadStream gridFS = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建gridFsSource，用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFS);
        //获取流中的数据
//        String string = IOUtil.toString(gridFsResource.getInputStream(), "UTF-8");
//        System.out.println(string);

    }

    @Test
    void mybatis() {
        WpUser wpUser = WpUser.builder().nickname("超级管理员")
                .username("admin").password("123456").build();

        int wpUserId = wpUserMapper.insertSelective(wpUser);
        System.out.println(wpUserId);
    }

}
