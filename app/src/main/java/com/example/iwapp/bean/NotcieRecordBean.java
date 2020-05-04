package com.example.iwapp.bean;

import java.util.List;

public class NotcieRecordBean {

    /**
     * status : 1
     * data : {"nopass_count":10,"pass_count":11,"no_pass_data":[{"curriculum_id":39,"title":"技术中台架构0011","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":71,"is_pass":0,"fraction":20},{"curriculum_id":40,"title":"技术中台架构0012","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":62,"is_pass":0,"fraction":20},{"curriculum_id":42,"title":"技术中台架构0014","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":60,"is_pass":0,"fraction":40},{"curriculum_id":43,"title":"技术中台架构0015","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":59,"is_pass":0,"fraction":60},{"curriculum_id":44,"title":"技术中台架构0016","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":58,"is_pass":0,"fraction":40},{"curriculum_id":45,"title":"技术中台架构0017","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":57,"is_pass":0,"fraction":20},{"curriculum_id":46,"title":"技术中台架构0018","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":56,"is_pass":0,"fraction":20},{"curriculum_id":29,"title":"技术中台架构001","type":2,"log":"http://images.ciotimes.com/o_1dt5scub91u4r1ja6so5ep11b8jb.jpg","evaluat_id":54,"is_pass":0,"fraction":20},{"curriculum_id":1,"title":"通用人工智能路在何方（国家_资源_开发）","type":2,"log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","evaluat_id":53,"is_pass":0,"fraction":60},{"curriculum_id":48,"title":"技术中台架构0020","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":37,"is_pass":0,"fraction":0}],"pass_data":[{"curriculum_id":41,"title":"技术中台架构0013","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":83,"is_pass":1,"fraction":100},{"curriculum_id":32,"title":"技术中台架构004","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":70,"is_pass":1,"fraction":100},{"curriculum_id":34,"title":"技术中台架构006","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":68,"is_pass":1,"fraction":100},{"curriculum_id":35,"title":"技术中台架构007","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":67,"is_pass":1,"fraction":80},{"curriculum_id":36,"title":"技术中台架构008","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":66,"is_pass":1,"fraction":100},{"curriculum_id":37,"title":"技术中台架构009","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":65,"is_pass":1,"fraction":100},{"curriculum_id":38,"title":"技术中台架构0010","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":64,"is_pass":1,"fraction":80},{"curriculum_id":49,"title":"技术中台架构0021","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":55,"is_pass":1,"fraction":100},{"curriculum_id":47,"title":"技术中台架构0019","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":51,"is_pass":1,"fraction":80},{"curriculum_id":12,"title":"新年快乐（国家中心、湖北中心、测试）","type":1,"log":"http://images.ciotimes.com/o_1dtd7ielh1vg31rhtfnf19nc1qreb.png","evaluat_id":50,"is_pass":1,"fraction":100}]}
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
         * nopass_count : 10
         * pass_count : 11
         * no_pass_data : [{"curriculum_id":39,"title":"技术中台架构0011","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":71,"is_pass":0,"fraction":20},{"curriculum_id":40,"title":"技术中台架构0012","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":62,"is_pass":0,"fraction":20},{"curriculum_id":42,"title":"技术中台架构0014","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":60,"is_pass":0,"fraction":40},{"curriculum_id":43,"title":"技术中台架构0015","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":59,"is_pass":0,"fraction":60},{"curriculum_id":44,"title":"技术中台架构0016","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":58,"is_pass":0,"fraction":40},{"curriculum_id":45,"title":"技术中台架构0017","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":57,"is_pass":0,"fraction":20},{"curriculum_id":46,"title":"技术中台架构0018","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":56,"is_pass":0,"fraction":20},{"curriculum_id":29,"title":"技术中台架构001","type":2,"log":"http://images.ciotimes.com/o_1dt5scub91u4r1ja6so5ep11b8jb.jpg","evaluat_id":54,"is_pass":0,"fraction":20},{"curriculum_id":1,"title":"通用人工智能路在何方（国家_资源_开发）","type":2,"log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","evaluat_id":53,"is_pass":0,"fraction":60},{"curriculum_id":48,"title":"技术中台架构0020","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":37,"is_pass":0,"fraction":0}]
         * pass_data : [{"curriculum_id":41,"title":"技术中台架构0013","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":83,"is_pass":1,"fraction":100},{"curriculum_id":32,"title":"技术中台架构004","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":70,"is_pass":1,"fraction":100},{"curriculum_id":34,"title":"技术中台架构006","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":68,"is_pass":1,"fraction":100},{"curriculum_id":35,"title":"技术中台架构007","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":67,"is_pass":1,"fraction":80},{"curriculum_id":36,"title":"技术中台架构008","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":66,"is_pass":1,"fraction":100},{"curriculum_id":37,"title":"技术中台架构009","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":65,"is_pass":1,"fraction":100},{"curriculum_id":38,"title":"技术中台架构0010","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":64,"is_pass":1,"fraction":80},{"curriculum_id":49,"title":"技术中台架构0021","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":55,"is_pass":1,"fraction":100},{"curriculum_id":47,"title":"技术中台架构0019","type":2,"log":"http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg","evaluat_id":51,"is_pass":1,"fraction":80},{"curriculum_id":12,"title":"新年快乐（国家中心、湖北中心、测试）","type":1,"log":"http://images.ciotimes.com/o_1dtd7ielh1vg31rhtfnf19nc1qreb.png","evaluat_id":50,"is_pass":1,"fraction":100}]
         */

        private int nopass_count;
        private int pass_count;
        private List<NoPassDataBean> no_pass_data;
        private List<PassDataBean> pass_data;

        public int getNopass_count() {
            return nopass_count;
        }

        public void setNopass_count(int nopass_count) {
            this.nopass_count = nopass_count;
        }

        public int getPass_count() {
            return pass_count;
        }

        public void setPass_count(int pass_count) {
            this.pass_count = pass_count;
        }

        public List<NoPassDataBean> getNo_pass_data() {
            return no_pass_data;
        }

        public void setNo_pass_data(List<NoPassDataBean> no_pass_data) {
            this.no_pass_data = no_pass_data;
        }

        public List<PassDataBean> getPass_data() {
            return pass_data;
        }

        public void setPass_data(List<PassDataBean> pass_data) {
            this.pass_data = pass_data;
        }

        public static class NoPassDataBean {
            /**
             * curriculum_id : 39
             * title : 技术中台架构0011
             * type : 2
             * log : http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg
             * evaluat_id : 71
             * is_pass : 0
             * fraction : 20
             */

            private int curriculum_id;
            private String title;
            private int type;
            private String log;
            private int evaluat_id;
            private int is_pass;
            private int fraction;

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

            public String getLog() {
                return log;
            }

            public void setLog(String log) {
                this.log = log;
            }

            public int getEvaluat_id() {
                return evaluat_id;
            }

            public void setEvaluat_id(int evaluat_id) {
                this.evaluat_id = evaluat_id;
            }

            public int getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(int is_pass) {
                this.is_pass = is_pass;
            }

            public int getFraction() {
                return fraction;
            }

            public void setFraction(int fraction) {
                this.fraction = fraction;
            }
        }

        public static class PassDataBean {
            /**
             * curriculum_id : 41
             * title : 技术中台架构0013
             * type : 2
             * log : http://images.ciotimes.com/o_1dt5sec3t8s1f9q1ehk1kor12vob.jpg
             * evaluat_id : 83
             * is_pass : 1
             * fraction : 100
             */

            private int curriculum_id;
            private String title;
            private int type;
            private String log;
            private int evaluat_id;
            private int is_pass;
            private int fraction;

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

            public String getLog() {
                return log;
            }

            public void setLog(String log) {
                this.log = log;
            }

            public int getEvaluat_id() {
                return evaluat_id;
            }

            public void setEvaluat_id(int evaluat_id) {
                this.evaluat_id = evaluat_id;
            }

            public int getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(int is_pass) {
                this.is_pass = is_pass;
            }

            public int getFraction() {
                return fraction;
            }

            public void setFraction(int fraction) {
                this.fraction = fraction;
            }
        }
    }
}
