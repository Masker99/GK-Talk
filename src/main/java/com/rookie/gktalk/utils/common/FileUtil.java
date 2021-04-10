package com.rookie.gktalk.utils.common;

import com.rookie.gktalk.utils.exception.WebException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

public class FileUtil {
    public static final String UserPersonalFilePath = "/upload/";

    public static String fileUploadAvatar(MultipartFile file, HttpServletRequest request,String userName){
        if (file.isEmpty() && file.getSize() == 0){
            throw new WebException("没有选择文件！");
        }

        int suffixBegin = file.getOriginalFilename().lastIndexOf(".")+1;
        String fileSuffixName = file.getOriginalFilename().substring(suffixBegin);
        if(!fileSuffixName.equals("jpg") && !fileSuffixName.equals("png") && !fileSuffixName.equals("jpeg")){
            throw new WebException("上传文件格式错误！");
        }

        String filePath = saveFile(file,request,userName);

        return filePath;
    }

    public static String saveFile(MultipartFile multipartFile,HttpServletRequest request,String userName){
        String dirPath = request.getServletContext().getRealPath(UserPersonalFilePath);

        File file = new File(dirPath);
        if(!file.exists()){
            file.mkdirs();
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = userName + "_" + UUID.randomUUID() + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);

        try {
            multipartFile.transferTo(new File(dirPath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dirPath+fileName;
    }

    public static void outFile(String filePath, HttpServletResponse response){
        File file = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(file);
            int bytesAvailable = inputStream.available();
            OutputStream outputStream = response.getOutputStream();

            byte[] buffer = new byte[bytesAvailable];
            while(inputStream.read(buffer) != -1){
                outputStream.write(buffer);
            }

            outputStream.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            throw new WebException(e.toString());
        } catch (IOException e) {
            throw new WebException(e.toString());
        }
    }
}
