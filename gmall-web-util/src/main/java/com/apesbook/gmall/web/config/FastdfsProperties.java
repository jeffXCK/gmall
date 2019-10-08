package com.apesbook.gmall.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author:XCK
 * Date:2019/10/4
 */
@Component
@ConfigurationProperties(prefix = "fastdfs")
public class FastdfsProperties {

    /**
     * 连接超时时间，针对socket套接字函数connect，默认为5秒
     */
    private int connect_timeout_in_seconds;
    /**
     * 网络通讯超时时间，默认是 30 秒
     */
    private int network_timeout_in_seconds;

    private String  charset;

    private String http_anti_steal_token;

    private String http_secret_key;

    private String http_tracker_http_port;

    /**
     * 多个 tracker_server 用逗号","隔开 (必须)
     */
    private String tracker_servers;

    /**
     * fastdfs 服务器访问地址 (必须)
     */
    private String fastdfs_server;

    public int getConnect_timeout_in_seconds() {
        return connect_timeout_in_seconds;
    }

    public void setConnect_timeout_in_seconds(int connect_timeout_in_seconds) {
        this.connect_timeout_in_seconds = connect_timeout_in_seconds;
    }

    public int getNetwork_timeout_in_seconds() {
        return network_timeout_in_seconds;
    }

    public void setNetwork_timeout_in_seconds(int network_timeout_in_seconds) {
        this.network_timeout_in_seconds = network_timeout_in_seconds;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getHttp_anti_steal_token() {
        return http_anti_steal_token;
    }

    public void setHttp_anti_steal_token(String http_anti_steal_token) {
        this.http_anti_steal_token = http_anti_steal_token;
    }

    public String getHttp_secret_key() {
        return http_secret_key;
    }

    public void setHttp_secret_key(String http_secret_key) {
        this.http_secret_key = http_secret_key;
    }

    public String getHttp_tracker_http_port() {
        return http_tracker_http_port;
    }

    public void setHttp_tracker_http_port(String http_tracker_http_port) {
        this.http_tracker_http_port = http_tracker_http_port;
    }

    public String getTracker_servers() {
        return tracker_servers;
    }

    public void setTracker_servers(String tracker_servers) {
        this.tracker_servers = tracker_servers;
    }

    public String getFastdfs_server() {
        return fastdfs_server;
    }

    public void setFastdfs_server(String fastdfs_server) {
        this.fastdfs_server = fastdfs_server;
    }
}
