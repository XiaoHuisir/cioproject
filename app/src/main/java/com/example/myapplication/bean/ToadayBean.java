package com.example.myapplication.bean;

import java.util.List;

public class ToadayBean {


    /**
     * status : 1
     * data : {"today":[{"curriculum_id":3,"title":"测试（少于5道题）","type":1,"teacher":"测试测试测试","gs":"测试测试","log":"http://images.ciotimes.com/o_1dt34qe141ce81uepg5k4h411cvb.jpg","len":21,"update_time":1578537277}],"seven_day":[{"curriculum_id":12,"title":"新年快乐（国家中心、湖北中心、测试）","type":1,"teacher":"maye","gs":"国家信息中心专职讲师","log":"http://images.ciotimes.com/o_1dtd7ielh1vg31rhtfnf19nc1qreb.png","len":2,"update_time":1577765315}],"day_ago":[{"curriculum_id":12,"title":"新年快乐（国家中心、湖北中心、测试）","type":1,"teacher":"maye","gs":"国家信息中心专职讲师","log":"http://images.ciotimes.com/o_1dtd7ielh1vg31rhtfnf19nc1qreb.png","len":2,"update_time":1577765315}]}
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
        private List<TodayBean> today;
        private List<SevenDayBean> seven_day;
        private List<DayAgoBean> day_ago;

        public List<TodayBean> getToday() {
            return today;
        }

        public void setToday(List<TodayBean> today) {
            this.today = today;
        }

        public List<SevenDayBean> getSeven_day() {
            return seven_day;
        }

        public void setSeven_day(List<SevenDayBean> seven_day) {
            this.seven_day = seven_day;
        }

        public List<DayAgoBean> getDay_ago() {
            return day_ago;
        }

        public void setDay_ago(List<DayAgoBean> day_ago) {
            this.day_ago = day_ago;
        }

        public static class TodayBean {
            /**
             * curriculum_id : 3
             * title : 测试（少于5道题）
             * type : 1
             * teacher : 测试测试测试
             * gs : 测试测试
             * log : http://images.ciotimes.com/o_1dt34qe141ce81uepg5k4h411cvb.jpg
             * len : 21
             * update_time : 1578537277
             */

            private int curriculum_id;
            private String title;
            private int type;
            private String teacher;
            private String gs;
            private String log;
            private int len;
            private int update_time;

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

            public int getLen() {
                return len;
            }

            public void setLen(int len) {
                this.len = len;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }
        }

        public static class SevenDayBean {
            /**
             * curriculum_id : 12
             * title : 新年快乐（国家中心、湖北中心、测试）
             * type : 1
             * teacher : maye
             * gs : 国家信息中心专职讲师
             * log : http://images.ciotimes.com/o_1dtd7ielh1vg31rhtfnf19nc1qreb.png
             * len : 2
             * update_time : 1577765315
             */

            private int curriculum_id;
            private String title;
            private int type;
            private String teacher;
            private String gs;
            private String log;
            private int len;
            private int update_time;

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

            public int getLen() {
                return len;
            }

            public void setLen(int len) {
                this.len = len;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }
        }

        public static class DayAgoBean {
            /**
             * curriculum_id : 12
             * title : 新年快乐（国家中心、湖北中心、测试）
             * type : 1
             * teacher : maye
             * gs : 国家信息中心专职讲师
             * log : http://images.ciotimes.com/o_1dtd7ielh1vg31rhtfnf19nc1qreb.png
             * len : 2
             * update_time : 1577765315
             */

            private int curriculum_id;
            private String title;
            private int type;
            private String teacher;
            private String gs;
            private String log;
            private int len;
            private int update_time;

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

            public int getLen() {
                return len;
            }

            public void setLen(int len) {
                this.len = len;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }
        }
    }
}
