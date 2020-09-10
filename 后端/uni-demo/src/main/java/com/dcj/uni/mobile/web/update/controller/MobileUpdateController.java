package com.dcj.uni.mobile.web.update.controller;

import com.dcj.uni.mobile.exception.MobileConst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("mobile/update")
public class MobileUpdateController {




    @RequestMapping("downapk")
    public void downApk(HttpServletRequest request, HttpServletResponse response) {
        String dstPath = request.getSession().getServletContext().getRealPath(
                BASE_PATH + File.separator + "apk" + File.separator + "bzwg.apk");
        File file = new File(dstPath);
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(
                    "bzwg.apk".getBytes(), "ISO-8859-1"));
            response.setContentType("application/octet-stream");
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取版本号
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "myInfo/getVersion", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResultData getVersionByType() {
        ResultData rd = new ResultData();
        try {
            Map map = getVerson();
            if (map != null) {
                rd.setCode(MobileConst.SUCCESS);
                rd.setResult(map);
                return rd;
            } else {
                throw new Exception("加载失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rd.setCode(MobileConst.SYS_ERROR);
            rd.setResult(MobileConst.SYS_ERROR_DESCRIPTION);
            return rd;
        }
    }

    public Map getVerson() throws Exception {
        String filepath = PropertyListener.getPropertyValue("${mobile.version}") + File.separator + "version.txt";
        File file = new File(filepath);//要读取以上路径的input。txt文件
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "GB2312");
        BufferedReader reader = new BufferedReader(inputStreamReader);// 建立一个对象，它把文件内容转成计算机能读懂的语言
        String version = "";
        if ((version = reader.readLine()) != null) {
            String[] strArray = version.split(",");
            Map map = new HashMap();
            for (int i = 0; i < strArray.length; i++) {
                String[] str = strArray[i].split("=");
                map.put(str[0], str[1]);
            }

            return map;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "myInfo/downloadApp", method = RequestMethod.GET)
    public void downloadApp(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream os = null;
        //设置响应头和客户端保存文件名
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            request.setCharacterEncoding("utf-8");
            String filepath = PropertyListener.getPropertyValue("${mobile.version}") + File.separator + getVerson().get("VERSION_NAME") + ".apk";
            response.setHeader("Content-Disposition", "attachment;fileName=" + getVerson().get("VERSION_NAME") + ".apk");
            response.addHeader("Content-Length", "" + new File(filepath).length());
            //打开本地文件流
            inputStream = new FileInputStream(filepath);
            //激活下载操作
            os = response.getOutputStream();
            //循环写入输出流
            byte[] b = new byte[2048];
            int length = 0;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                os.flush();
                // 这里主要关闭。
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

   /* @RequestMapping(value = "download/getImgByUserId")
    public Object getImgById(String userId, HttpServletResponse response, HttpServletRequest request) throws Exception {
        PageData filesUploadsData = filesUploadsService.getByUserId(userId);
        String fileName = "";
        File file = null;
        if (filesUploadsData != null) {
            fileName = filesUploadsData.getString("FILE_NAME") + "";
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            String path = filesUploadsData.getString("BASE_PATH") + File.separator + filesUploadsData.getString("PATH");
            file = new File(path);
        }
        if (file==null || !file.exists()) {
            String realPath = request.getServletContext().getRealPath("/upload");
            String path = realPath + "/head.png";
            file = new File(path);
        }

        BufferedImage srcImage = ImageIO.read(file);
        srcImage = ImgUtils.resize(srcImage, 100, 100);
        //bufferedimage 转换成 inputstream
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(srcImage, "png", imOut);
        InputStream inputStream = new ByteArrayInputStream(bs.toByteArray());

//        InputStream inputStream = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        //关闭。
        os.close();
        inputStream.close();
        return null;
    }*/
}
