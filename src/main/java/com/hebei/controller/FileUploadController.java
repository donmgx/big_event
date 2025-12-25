package com.hebei.controller;

import com.aliyun.oss.OSS;
import com.hebei.pojo.Result;
import com.hebei.util.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/upload")
public class FileUploadController {
    @Autowired
    private AliOssUtil aliOssUtil;
    /*
    * 文件上传
    * */
    @PostMapping
    public Result<String> upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file);
        //把文件的内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        //保证文件的名字是唯一的,从而防止文件覆盖
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        //file.transferTo(new File("C:\\Users\\Administrator\\Desktop\\files\\"+filename));
        String url = aliOssUtil.upload(file);
        return Result.success(url);
    }

}
