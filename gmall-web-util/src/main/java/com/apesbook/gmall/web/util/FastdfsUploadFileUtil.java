package com.apesbook.gmall.web.util;

import com.apesbook.gmall.web.config.FastdfsProperties;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.Properties;

import static org.csource.fastdfs.ClientGlobal.PROP_KEY_TRACKER_SERVERS;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/4
 */
public class FastdfsUploadFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FastdfsUploadFileUtil.class);

    /**
     * 上传文件到FastDFS
     *
     * @param multipartFile
     * @return
     */
    public static String uploadImg(MultipartFile multipartFile, FastdfsProperties fastdfsProperties) {
        try {
            if (multipartFile == null) {
                throw new IllegalAccessException("上传文件不能为空");
            }
            // 加载 Properties 对象配置：
            Properties properties = getProperties(fastdfsProperties);

            ClientGlobal.initByProperties(properties);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            String filename = multipartFile.getOriginalFilename();
            String extName = StringUtils.substringAfterLast(filename, ".");
            String[] upload_file = storageClient.upload_file(multipartFile.getBytes(), extName, null);
            String imgUrl = fastdfsProperties.getFastdfs_server();
            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                imgUrl += "/" + path;
            }
            trackerServer.close();
            return imgUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Properties getProperties(FastdfsProperties fastdfsProperties) {
        Properties properties = new Properties();
        String tracker_servers = fastdfsProperties.getTracker_servers();
        if (StringUtils.isBlank(tracker_servers)) {
            try {
                throw new IllegalAccessException(String.format("configure item %s is required", PROP_KEY_TRACKER_SERVERS));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        properties.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, tracker_servers);
        int connect_timeout_in_seconds = fastdfsProperties.getConnect_timeout_in_seconds();
        int network_timeout_in_seconds = fastdfsProperties.getNetwork_timeout_in_seconds();
        String charset = fastdfsProperties.getCharset();
        String http_anti_steal_token = fastdfsProperties.getHttp_anti_steal_token();
        String http_secret_key = fastdfsProperties.getHttp_secret_key();
        String http_tracker_http_port = fastdfsProperties.getHttp_tracker_http_port();

        if (connect_timeout_in_seconds > 0) {
            properties.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, connect_timeout_in_seconds);
        }

        if (network_timeout_in_seconds > 0) {
            properties.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, connect_timeout_in_seconds);
        }

        if (StringUtils.isNotBlank(charset)) {
            properties.put(ClientGlobal.PROP_KEY_CHARSET, charset);
        }
        if (StringUtils.isNotBlank(http_anti_steal_token)) {
            properties.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, http_anti_steal_token);
        }
        if (StringUtils.isNotBlank(http_secret_key)) {
            properties.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, http_secret_key);
        }
        if (StringUtils.isNotBlank(http_tracker_http_port)) {
            properties.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, http_tracker_http_port);
        }
        return properties;
    }

}
