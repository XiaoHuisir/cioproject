package com.example.myapplication.bean;

import java.util.List;

public class MyfilelistBean {

    /**
     * status : 1
     * data : [{"id":3,"file_name":"打造数据驱动的企业.pdf","file_url":"http://images.ciotimes.com/o_1dq6dauml14qn1dg012s6rpubuvi.pdf","userid":1,"file_size":"78848","add_time":1577279495,"curriculum_id":1,"title":"通用人工智能路在何方（国家_资源_开发）","type":2}]
     * msg :
     * code : 10000
     */

    private int status;
    private String msg;
    private int code;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * file_name : 打造数据驱动的企业.pdf
         * file_url : http://images.ciotimes.com/o_1dq6dauml14qn1dg012s6rpubuvi.pdf
         * userid : 1
         * file_size : 78848
         * add_time : 1577279495
         * curriculum_id : 1
         * title : 通用人工智能路在何方（国家_资源_开发）
         * type : 2
         */

        private int id;
        private String file_name;
        private String file_url;
        private int userid;
        private String file_size;
        private int add_time;
        private int curriculum_id;
        private String title;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getFile_size() {
            return file_size;
        }

        public void setFile_size(String file_size) {
            this.file_size = file_size;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getCurriculum_id() {
            return curriculum_id;
        }

        public void setCurriculum_id(int curriculum_id) {
            this.curriculum_id = curriculum_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
