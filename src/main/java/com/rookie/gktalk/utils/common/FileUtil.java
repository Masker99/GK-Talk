package com.rookie.gktalk.utils.common;

import com.rookie.gktalk.utils.exception.WebException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * 自定义文件工具类
 * @author Masker
 */
public class FileUtil {
    public static final String UserPersonalFilePath = "/upload/";

    /**
     * 上传图片文件
     * @param file
     * @param request
     * @param userName 区分用户文件
     * @return
     */
    public static String fileUploadAvatar(MultipartFile file, HttpServletRequest request,String userName){
        //检验文件是否存在
        if (file.isEmpty() && file.getSize() == 0){
            throw new WebException("没有选择文件！");
        }

        //获取源文件的后缀名
        int suffixBegin = file.getOriginalFilename().lastIndexOf(".")+1;
        String fileSuffixName = file.getOriginalFilename().substring(suffixBegin);

        //利用文件后缀名检验文件格式
        if(!fileSuffixName.equals("jpg") && !fileSuffixName.equals("png") && !fileSuffixName.equals("jpeg")){
            throw new WebException("上传文件格式错误！");
        }

        String filePath = saveFile(file,request,userName);

        return filePath;
    }

    /**
     * 保存文件至服务器
     * @param multipartFile
     * @param request
     * @param userName
     * @return 返回文件存储路径
     */
    public static String saveFile(MultipartFile multipartFile,HttpServletRequest request,String userName){
        //定义文件存储的路径
        String dirPath = request.getServletContext().getRealPath(UserPersonalFilePath);

        File file = new File(dirPath);
        if(!file.exists()){
            file.mkdirs();
        }

        String originalFileName = multipartFile.getOriginalFilename();
        //重新生成文件名
        String fileName = userName + "_" + UUID.randomUUID() + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);

        try {
            //将文件传输至指定路径
            multipartFile.transferTo(new File(dirPath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dirPath+fileName;
    }

    /**
     * 输出文件
     * @param filePath
     * @param response
     */
    public static void outFile(String filePath, HttpServletResponse response){
        File file = new File(filePath);
        //利用文件流输出文件
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
