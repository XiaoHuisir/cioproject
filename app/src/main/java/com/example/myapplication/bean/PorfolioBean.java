package com.example.myapplication.bean;

import java.util.List;

public class PorfolioBean {

    /**
     * status : 1
     * data : {"today":[{"curriculum_id":1,"title":"通用人工智能路在何方","type":2,"teacher":"杨学山","gs":"原工业和信息化部副部长","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","len":44,"update_time":1577409722}],"seven_day":[{"curriculum_id":12,"title":"新年快乐","type":1,"teacher":"maye","gs":"国家信息中心","log":"http://images.ciotimes.com/o_1dt0djbtq190e4602k41du51u6gb.jpg","len":75,"update_time":1577344414},{"curriculum_id":13,"title":"重点行业云标准体系及案例","type":2,"teacher":"明庭辉","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt0e87kvf8kgjkvo3hlv1gj0b.png","len":75,"update_time":1577203200}],"day_ago":[{"curriculum_id":5,"title":"通用人工智能路在何方(课程管理）","type":1,"teacher":"杨学山","gs":"原工业和信息化部副部长","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","len":200,"update_time":1576684800}]}
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
             * curriculum_id : 1
             * title : 通用人工智能路在何方
             * type : 2
             * teacher : 杨学山
             * gs : 原工业和信息化部副部长
             * log : http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg
             * len : 44
             * update_time : 1577409722
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
             * title : 新年快乐
             * type : 1
             * teacher : maye
             * gs : 国家信息中心
             * log : http://images.ciotimes.com/o_1dt0djbtq190e4602k41du51u6gb.jpg
             * len : 75
             * update_time : 1577344414
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
             * curriculum_id : 5
             * title : 通用人工智能路在何方(课程管理）
             * type : 1
             * teacher : 杨学山
             * gs : 原工业和信息化部副部长
             * log : http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg
             * len : 200
             * update_time : 1576684800
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
