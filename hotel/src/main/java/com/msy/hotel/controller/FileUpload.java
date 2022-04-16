package com.msy.hotel.controller;

import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author whf
 */
@RestController
@RequestMapping("/FileUpload")
@CrossOrigin
public class FileUpload {
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseModel upload(MultipartFile files, HttpServletRequest request) throws IOException {
        System.out.println("1234556");
//    ①获取文件名
        String fileName=files.getOriginalFilename();
        System.out.println(fileName);
//     ②获取文件后缀及重写文件名
        String prifx=fileName.substring(fileName.lastIndexOf("."));

//        fileName=new Date().getTime()+prifx;
//      使用UUID生成唯一的文件名  7e38bbbc-2aab-4287-aedb-2491f953f68e.png
        fileName=UUID.randomUUID()+prifx;

//      ③设置文件上路路径
        String path=request.getServletContext().getRealPath("/pic");
        System.out.println(path);

//        File file=new File("D:/pic");

        File file=new File(path+"/"+fileName);
        if(!file.exists()){
            file.mkdirs();
        }

//        File file1=new File(file,fileName);
//       ④上传文件
        files.transferTo(file);

        return ResponseModel.success(ResCode.SUCCESS,"/pic/"+fileName);

    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public ResponseModel delFile(String roomImg,HttpServletRequest request) {
//        roomImg="http://localhost:8888/hotel/pic/43f02b4d-2a48-4378-a7f2-bbb3558cc397.jpg";
        String path=request.getServletContext().getRealPath("/pic");
        path=path+"\\"+roomImg.substring(roomImg.lastIndexOf("/")+1);
        System.out.println(path);
        File delFile = new File(path);
        System.out.println("删除的文件路径："+delFile);
        if(delFile.isFile() && delFile.exists()) {
            delFile.delete();
            return ResponseModel.success(ResCode.SUCCESS);
        }else {
            return ResponseModel.success(ResCode.FAIL);
        }
    }
}
