package com.example.iwapp.bean;

public class EvaluationSubmitBean {

    /**
     * status : 1
     * data : {"evaluat_id":"3"}
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
         * evaluat_id : 3
         */

        private String evaluat_id;

        public String getEvaluat_id() {
            return evaluat_id;
        }

        public void setEvaluat_id(String evaluat_id) {
            this.evaluat_id = evaluat_id;
        }
    }
}
