package com.hz.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @author zehua
 * @date 2020/11/24 10:54
 */
@RestController
public class UploadController {

    private static final String FILE_PATH = "F:\\zehua\\test\\";

    @RequestMapping("/upload")
    public String upload(@RequestParam("up_file") MultipartFile file) {
        final long size = file.getSize();
        if (size <= 0) {
            return "error";
        }

        final String originalFilename = file.getOriginalFilename();
        // final String fileName = file.getName(); // 打印up_file,就是请求参数名
        System.out.println(originalFilename);
        final int suffixIndex = originalFilename.lastIndexOf(".");
        String newName = UUID.randomUUID().toString() + originalFilename.substring(suffixIndex);
        String fileFullName = FILE_PATH + newName;

        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
            return "error " + e.getMessage();
        }

        try {
            file.transferTo(new File(fileFullName));
        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
            return "error " + e.getMessage();
        }

        System.out.println(new String(bytes, 0, bytes.length));
        return "upload success";
    }

}
