package com.example.iwapp.bean;

public class TokenBean {

    /**
     * status : 1
     * data : {"token":"O9wQ-Cc6XBp4_UffnIs2Ovtdnz-srqIkdz3Ih2h-:bVZ5vRMkeMNIck35O2Q0JGw777g=:eyJzY29wZSI6ImNpb3RpbWVzIiwiZGVhZGxpbmUiOjE1NzczNTU2OTJ9"}
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
         * token : O9wQ-Cc6XBp4_UffnIs2Ovtdnz-srqIkdz3Ih2h-:bVZ5vRMkeMNIck35O2Q0JGw777g=:eyJzY29wZSI6ImNpb3RpbWVzIiwiZGVhZGxpbmUiOjE1NzczNTU2OTJ9
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
