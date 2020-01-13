package com.example.myapplication.bean;

public class VerBean {


    /**
     * status : 1
     * data : {"version":{"status":1,"version_code":"1.0","apk_url":"http://images.ciotimes.com/com.IwaiwangApp.apk","upgrade_content":"测试的2322333444"}}
     * msg :
     * code : 10000
     */

    private int status;
    private DataBean data;
    private String msg;
    private int code;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * version : {"status":1,"version_code":"1.0","apk_url":"http://images.ciotimes.com/com.IwaiwangApp.apk","upgrade_content":"测试的2322333444"}
         */

        private VersionBean version;

        public VersionBean getVersion() {
            return version;
        }

        public void setVersion(VersionBean version) {
            this.version = version;
        }

        public static class VersionBean {
            /**
             * status : 1
             * version_code : 1.0
             * apk_url : http://images.ciotimes.com/com.IwaiwangApp.apk
             * upgrade_content : 测试的2322333444
             */

            private int status;
            private String version_code;
            private String apk_url;
            private String upgrade_content;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getVersion_code() {
                return version_code;
            }

            public void setVersion_code(String version_code) {
                this.version_code = version_code;
            }

            public String getApk_url() {
                return apk_url;
            }

            public void setApk_url(String apk_url) {
                this.apk_url = apk_url;
            }

            public String getUpgrade_content() {
                return upgrade_content;
            }

            public void setUpgrade_content(String upgrade_content) {
                this.upgrade_content = upgrade_content;
            }
        }
    }
}
