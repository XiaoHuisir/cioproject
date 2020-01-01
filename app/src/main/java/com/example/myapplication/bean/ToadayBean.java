package com.example.myapplication.bean;

import java.util.List;

public class ToadayBean {

    /**
     * status : 1
     * data : {"seven_day":[{"curriculum_id":24,"title":"如何在红海竞争中，占领用户心智？（国家_资源；智慧课堂）","type":2,"teacher":"李飞","gs":"","log":"http://images.ciotimes.com/o_1dt2ucvjefao7oc1bonevehcb.jpg","len":102,"update_time":1577697586},{"curriculum_id":49,"title":"技术中台架构0021","type":2,"teacher":"maye","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","len":406,"update_time":1577697582},{"curriculum_id":1,"title":"通用人工智能路在何方（国家_资源_开发）","type":2,"teacher":"杨学山","gs":"原工业和信息化部副部长","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","len":2059,"update_time":1577697562},{"curriculum_id":21,"title":"中小企业销售提升12讲（国家中心；智慧课堂）","type":2,"teacher":"陈新河","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt2u9rm7br113eb1bfr1f241h70b.jpg","len":29,"update_time":1577526773},{"curriculum_id":23,"title":"哪些客户距离提升销售业绩最近？（国家_资源_编辑；智慧课堂）","type":2,"teacher":"李明宇","gs":"","log":"http://images.ciotimes.com/o_1dt2ubhejbqlu6s1dinahf975b.jpg","len":26,"update_time":1577520964},{"curriculum_id":13,"title":"重点行业云标准体系及案例（全选所有可见；内部培训）","type":2,"teacher":"明庭辉","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dtd6vsan1ktt103ggm2e2131mb.png","len":86,"update_time":1577519519},{"curriculum_id":17,"title":"梯队与成长路径（国家中心、湖北中心；智慧课堂）","type":2,"teacher":"张军飞","gs":"","log":"http://images.ciotimes.com/o_1dtd7a3d51qrjvu11isqocp17jlb.png","len":63,"update_time":1577519298}],"day_ago":[]}
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
        private List<SevenDayBean> seven_day;
        private List<?> day_ago;

        public List<SevenDayBean> getSeven_day() {
            return seven_day;
        }

        public void setSeven_day(List<SevenDayBean> seven_day) {
            this.seven_day = seven_day;
        }

        public List<?> getDay_ago() {
            return day_ago;
        }

        public void setDay_ago(List<?> day_ago) {
            this.day_ago = day_ago;
        }

        public static class SevenDayBean {
            /**
             * curriculum_id : 24
             * title : 如何在红海竞争中，占领用户心智？（国家_资源；智慧课堂）
             * type : 2
             * teacher : 李飞
             * gs :
             * log : http://images.ciotimes.com/o_1dt2ucvjefao7oc1bonevehcb.jpg
             * len : 102
             * update_time : 1577697586
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
