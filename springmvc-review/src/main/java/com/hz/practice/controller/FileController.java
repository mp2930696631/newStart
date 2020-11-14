package com.hz.practice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: zehua
 * @date: 2020/11/14 12:53
 */
@Controller
public class FileController {

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(originalFilename);
        multipartFile.transferTo(new File("C:\\zehua\\test\\"+originalFilename));

        return "success";
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws Exception {
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/files/Maven.txt");
        System.out.println(realPath);
        FileInputStream fileInputStream = new FileInputStream(realPath);
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        fileInputStream.close();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment:filename=Maven.txt");
        headers.add("Content-Type", "multipart/form-data");

        return new ResponseEntity<>(buffer, headers, HttpStatus.OK);
    }

}
