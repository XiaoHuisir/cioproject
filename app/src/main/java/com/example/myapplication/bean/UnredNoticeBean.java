package com.example.myapplication.bean;

public class UnredNoticeBean {

    /**
     * status : 1
     * data : {"notice_num":"1"}
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
         * notice_num : 1
         */

        private String notice_num;

        public String getNotice_num() {
            return notice_num;
        }

        public void setNotice_num(String notice_num) {
            this.notice_num = notice_num;
        }
    }
}
