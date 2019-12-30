package com.example.myapplication.bean;

import java.util.List;

public class IndexBean {

    /**
     * status : 1
     * data : {"lb_data":[{"id":1,"image":"http://www.ciotimes.com/uploadfile/2019/0820/20190820053216672.jpg","curriculum_id":1}],"curriculum_data":[{"id":1,"title":"通用人工智能路在何方","teacher":"杨学山","gs":"原工业和信息化部","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","up_new":0,"len":2256,"jd":"0.13"}]}
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
        private List<LbDataBean> lb_data;
        private List<CurriculumDataBean> curriculum_data;

        public List<LbDataBean> getLb_data() {
            return lb_data;
        }

        public void setLb_data(List<LbDataBean> lb_data) {
            this.lb_data = lb_data;
        }

        public List<CurriculumDataBean> getCurriculum_data() {
            return curriculum_data;
        }

        public void setCurriculum_data(List<CurriculumDataBean> curriculum_data) {
            this.curriculum_data = curriculum_data;
        }

        public static class LbDataBean {
            /**
             * id : 1
             * image : http://www.ciotimes.com/uploadfile/2019/0820/20190820053216672.jpg
             * curriculum_id : 1
             */

            private int id;
            private String image;
            private int curriculum_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getCurriculum_id() {
                return curriculum_id;
            }

            public void setCurriculum_id(int curriculum_id) {
                this.curriculum_id = curriculum_id;
            }
        }

        public static class CurriculumDataBean {
            /**
             * id : 1
             * title : 通用人工智能路在何方
             * teacher : 杨学山
             * gs : 原工业和信息化部
             * log : http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg
             * up_new : 0
             * len : 2256
             * jd : 0.13
             */

            private int id;
            private String title;
            private String teacher;
            private String gs;
            private String log;
            private int up_new;
            private int len;
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

            public String getJd() {
                return jd;
            }

            public void setJd(String jd) {
                this.jd = jd;
            }
        }
    }
}
