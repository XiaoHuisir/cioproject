package com.example.myapplication.bean;

import java.util.List;

public class TypeIndexBean {

    /**
     * status : 1
     * data : {"curriculum_px":[{"id":1,"title":"智慧课堂","add_time":1576739493,"type":2,"is_select":1},{"id":2,"title":"内部培训","add_time":1576739493,"type":2,"is_select":0},{"id":3,"title":"其他培训","add_time":1576739493,"type":2,"is_select":0}],"curriculum_kw":[{"id":4,"title":"大数据","add_time":1576739493,"type":1,"is_select":0},{"id":5,"title":"云计算","add_time":1576739493,"type":1,"is_select":0},{"id":6,"title":"互联网","add_time":1576739493,"type":1,"is_select":0}],"curriculum_data":[{"id":1,"title":"通用人工智能路在何方","teacher":"杨学山","gs":"原工业和信息化部","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","up_new":0,"len":2256,"jd":"0.13"}]}
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
        private List<CurriculumPxBean> curriculum_px;
        private List<CurriculumKwBean> curriculum_kw;
        private List<CurriculumDataBean> curriculum_data;

        public List<CurriculumPxBean> getCurriculum_px() {
            return curriculum_px;
        }

        public void setCurriculum_px(List<CurriculumPxBean> curriculum_px) {
            this.curriculum_px = curriculum_px;
        }

        public List<CurriculumKwBean> getCurriculum_kw() {
            return curriculum_kw;
        }

        public void setCurriculum_kw(List<CurriculumKwBean> curriculum_kw) {
            this.curriculum_kw = curriculum_kw;
        }

        public List<CurriculumDataBean> getCurriculum_data() {
            return curriculum_data;
        }

        public void setCurriculum_data(List<CurriculumDataBean> curriculum_data) {
            this.curriculum_data = curriculum_data;
        }

        public static class CurriculumPxBean {
            /**
             * id : 1
             * title : 智慧课堂
             * add_time : 1576739493
             * type : 2
             * is_select : 1
             */

            private int id;
            private String title;
            private int add_time;
            private int type;
            private int is_select;

            public boolean select;

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

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getIs_select() {
                return is_select;
            }

            public void setIs_select(int is_select) {
                this.is_select = is_select;
            }
        }

        public static class CurriculumKwBean {
            /**
             * id : 4
             * title : 大数据
             * add_time : 1576739493
             * type : 1
             * is_select : 0
             */

            private int id;
            private String title;
            private int add_time;
            private int type;
            private int is_select;

            public boolean select;

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

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getIs_select() {
                return is_select;
            }

            public void setIs_select(int is_select) {
                this.is_select = is_select;
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
