package com.example.myapplication.bean;

public class UserInfoUpdateBean {

    /**
     * status : 1
     * data : {"update_id":1}
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
         * update_id : 1
         */

        private int update_id;

        public int getUpdate_id() {
            return update_id;
        }

        public void setUpdate_id(int update_id) {
            this.update_id = update_id;
        }
    }
}
