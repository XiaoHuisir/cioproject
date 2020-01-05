package com.example.myapplication.bean;

import android.os.Parcelable;

import java.util.List;

public class SearchBean {

    /**
     * status : 1
     * data : {"curriculum_data":[{"id":5,"title":"通用人工智能路在何方(课程管理）","teacher":"杨学山","gs":"原工业和信息化部副部长","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","up_new":0,"len":2256,"type":1,"jd":"0"},{"id":1,"title":"通用人工智能路在何方（未选可见范围）","teacher":"杨学山","gs":"原工业和信息化部副部长","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","up_new":0,"len":3005,"type":2,"jd":"0.45"}]}
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
        private List<CurriculumDataBean> curriculum_data;

        public List<CurriculumDataBean> getCurriculum_data() {
            return curriculum_data;
        }

        public void setCurriculum_data(List<CurriculumDataBean> curriculum_data) {
            this.curriculum_data = curriculum_data;
        }

        public static class CurriculumDataBean {
            /**
             * id : 5
             * title : 通用人工智能路在何方(课程管理）
             * teacher : 杨学山
             * gs : 原工业和信息化部副部长
             * log : http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg
             * up_new : 0
             * len : 2256
             * type : 1
             * jd : 0
             */

            private int id;
            private String title;
            private String teacher;
            private String gs;
            private String log;
            private int up_new;
            private int len;
            private int type;
            private String jd;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }

            public String getGs() {
                return gs;
            }

            public void setGs(String gs) {
                this.gs = gs;
            }

            public String getLog() {
                return log;
            }

            public void setLog(String log) {
                this.log = log;
            }

            public int getUp_new() {
                return up_new;
            }

            public void setUp_new(int up_new) {
                this.up_new = up_new;
            }

            public int getLen() {
                return len;
            }

            public void setLen(int len) {
                this.len = len;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getJd() {
                return jd;
            }

            public void setJd(String jd) {
                this.jd = jd;
            }
        }
    }
}
