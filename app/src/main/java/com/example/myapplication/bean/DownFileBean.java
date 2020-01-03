package com.example.myapplication.bean;

public class DownFileBean {

    /**
     * status : 1
     * data : {"insert_id":"1"}
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
         * insert_id : 1
         */

        private String insert_id;

        public String getInsert_id() {
            return insert_id;
        }

        public void setInsert_id(String insert_id) {
            this.insert_id = insert_id;
        }
    }
}
