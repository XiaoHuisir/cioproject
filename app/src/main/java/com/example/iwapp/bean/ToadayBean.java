package com.example.iwapp.bean;

import java.util.List;

public class ToadayBean {


    /**
     * status : 1
     * data : {"today":[{"curriculum_id":0,"title":"","type":0,"teacher":"","gs":"","log":"","len":0,"update_time":0}],"seven_day":[{"curriculum_id":46,"title":"技术中台架构0018","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":164,"update_time":1578565994},{"curriculum_id":49,"title":"技术中台架构0021","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":887,"update_time":1578565980},{"curriculum_id":52,"title":"技术中台架构0024","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dtb2hqj314dkulf1os8175c1hcab.jpg","len":774,"update_time":1578565977},{"curriculum_id":3,"title":"测试（少于5道题）","type":1,"teacher":"测试测试测试","gs":"测试测试","log":"http://images.ciotimes.com/o_1dt34qe141ce81uepg5k4h411cvb.jpg","len":21,"update_time":1578537277},{"curriculum_id":39,"title":"技术中台架构0011","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":12,"update_time":1578454727},{"curriculum_id":43,"title":"技术中台架构0015","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":13,"update_time":1578453962},{"curriculum_id":48,"title":"技术中台架构0020","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":675,"update_time":1578453932},{"curriculum_id":51,"title":"技术中台架构0023","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dtb26km419h0vbl12gf17n7ofnb.jpg","len":24,"update_time":1578453924},{"curriculum_id":13,"title":"重点行业云标准体系及案例（全选所有可见；内部培训）","type":2,"teacher":"明庭辉","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dtd6vsan1ktt103ggm2e2131mb.png","len":1724,"update_time":1578452569},{"curriculum_id":1,"title":"通用人工智能路在何方（国家_资源_开发）","type":2,"teacher":"杨学山","gs":"原工业和信息化部副部长","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","len":83,"update_time":1578452228}],"day_ago":[{"curriculum_id":40,"title":"技术中台架构0012","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":91,"update_time":1577775168},{"curriculum_id":35,"title":"技术中台架构007","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":5,"update_time":1577767141},{"curriculum_id":21,"title":"中小企业销售提升12讲（国家中心；智慧课堂）","type":2,"teacher":"陈新河","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt2u9rm7br113eb1bfr1f241h70b.jpg","len":11,"update_time":1577765471},{"curriculum_id":12,"title":"新年快乐（国家中心、湖北中心、测试）","type":1,"teacher":"maye","gs":"国家信息中心专职讲师","log":"http://images.ciotimes.com/o_1dtd7ielh1vg31rhtfnf19nc1qreb.png","len":2,"update_time":1577765315},{"curriculum_id":33,"title":"技术中台架构005","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":804,"update_time":1577764690},{"curriculum_id":45,"title":"技术中台架构0017","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":12,"update_time":1577761022},{"curriculum_id":47,"title":"技术中台架构0019","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":80,"update_time":1577761020},{"curriculum_id":44,"title":"技术中台架构0016","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":30,"update_time":1577704353},{"curriculum_id":29,"title":"技术中台架构001","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5scub91u4r1ja6so5ep11b8jb.jpg","len":2,"update_time":1577704280},{"curriculum_id":32,"title":"技术中台架构004","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":6,"update_time":1577704039}]}
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
             * curriculum_id : 0
             * title :
             * type : 0
             * teacher :
             * gs :
             * log :
             * len : 0
             * update_time : 0
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
             * curriculum_id : 46
             * title : 技术中台架构0018
             * type : 2
             * teacher : maye
             * gs : 浙江五芳斋实业股份有限公司
             * log : http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg
             * len : 164
             * update_time : 1578565994
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
             * curriculum_id : 40
             * title : 技术中台架构0012
             * type : 2
             * teacher : maye
             * gs : 浙江五芳斋实业股份有限公司
             * log : http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg
             * len : 91
             * update_time : 1577775168
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
