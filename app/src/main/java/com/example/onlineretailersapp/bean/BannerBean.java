package com.example.onlineretailersapp.bean;

import java.util.List;

/**
 * author:${张四佟}
 * date:2019/2/14 17:54
 * description
 */
public class BannerBean {


    /**
     * result : [{"imageUrl":"http://172.17.8.100/images/tech/banner/20181026151647.png","jumpUrl":"http://172.17.8.100/htm/lottery/index.html"},{"imageUrl":"http://172.17.8.100/images/tech/banner/20181026151647.png","jumpUrl":"http://172.17.8.100/htm/lottery/index.html"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * imageUrl : http://172.17.8.100/images/tech/banner/20181026151647.png
         * jumpUrl : http://172.17.8.100/htm/lottery/index.html
         */

        private String imageUrl;
        private String jumpUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }
    }
}
