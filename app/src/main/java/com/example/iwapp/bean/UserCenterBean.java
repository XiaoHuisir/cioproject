package com.example.iwapp.bean;

import java.util.List;

public class UserCenterBean {

    /**
     * status : 1
     * data : {"nickname":"张志达2","avatar":"http://images.ciotimes.com/49C81C3BB1E5C3E34D828F53FA27379E","zw":"副局长","mechanism_id":3,"mechanism":"国家信息中心信息资源开发部开发处","kcpx_num":6,"kwxx_num":2,"kcpx_time":6831,"kwxx_time":1,"history":[{"curriculum_id":1,"title":"通用人工智能路在何方（国家_资源_开发）","type":2,"teacher":"杨学山","gs":"原工业和信息化部副部长","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","len":1532},{"curriculum_id":24,"title":"如何在红海竞争中，占领用户心智？（国家_资源；智慧课堂）","type":2,"teacher":"李飞","gs":"","log":"http://images.ciotimes.com/o_1dt2ucvjefao7oc1bonevehcb.jpg","len":92},{"curriculum_id":23,"title":"哪些客户距离提升销售业绩最近？（国家_资源_编辑；智慧课堂）","type":2,"teacher":"李明宇","gs":"","log":"http://images.ciotimes.com/o_1dt2ubhejbqlu6s1dinahf975b.jpg","len":26},{"curriculum_id":21,"title":"中小企业销售提升12讲（国家中心；智慧课堂）","type":2,"teacher":"陈新河","gs":"","log":"http://images.ciotimes.com/o_1dt2u9rm7br113eb1bfr1f241h70b.jpg","len":29},{"curriculum_id":13,"title":"重点行业云标准体系及案例（全选所有可见；内部培训）","type":2,"teacher":"明庭辉","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt0e87kvf8kgjkvo3hlv1gj0b.png","len":86}],"end_num":1,"pass_num":0,"nopass_num":1,"file_num":1}
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
         * nickname : 张志达2
         * avatar : http://images.ciotimes.com/49C81C3BB1E5C3E34D828F53FA27379E
         * zw : 副局长
         * mechanism_id : 3
         * mechanism : 国家信息中心信息资源开发部开发处
         * kcpx_num : 6
         * kwxx_num : 2
         * kcpx_time : 6831
         * kwxx_time : 1
         * history : [{"curriculum_id":1,"title":"通用人工智能路在何方（国家_资源_开发）","type":2,"teacher":"杨学山","gs":"原工业和信息化部副部长","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","len":1532},{"curriculum_id":24,"title":"如何在红海竞争中，占领用户心智？（国家_资源；智慧课堂）","type":2,"teacher":"李飞","gs":"","log":"http://images.ciotimes.com/o_1dt2ucvjefao7oc1bonevehcb.jpg","len":92},{"curriculum_id":23,"title":"哪些客户距离提升销售业绩最近？（国家_资源_编辑；智慧课堂）","type":2,"teacher":"李明宇","gs":"","log":"http://images.ciotimes.com/o_1dt2ubhejbqlu6s1dinahf975b.jpg","len":26},{"curriculum_id":21,"title":"中小企业销售提升12讲（国家中心；智慧课堂）","type":2,"teacher":"陈新河","gs":"","log":"http://images.ciotimes.com/o_1dt2u9rm7br113eb1bfr1f241h70b.jpg","len":29},{"curriculum_id":13,"title":"重点行业云标准体系及案例（全选所有可见；内部培训）","type":2,"teacher":"明庭辉","gs":"浙江五芳斋实业股份有限公司","log":"http://images.ciotimes.com/o_1dt0e87kvf8kgjkvo3hlv1gj0b.png","len":86}]
         * end_num : 1
         * pass_num : 0
         * nopass_num : 1
         * file_num : 1
         */

        private String nickname;
        private String avatar;
        private String zw;
        private int mechanism_id;
        private String mechanism;
        private int kcpx_num;
        private int kwxx_num;
        private int kcpx_time;
        private int kwxx_time;
        private int end_num;
        private int pass_num;
        private int nopass_num;
        private int file_num;
        private List<HistoryBean> history;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getZw() {
            return zw;
        }

        public void setZw(String zw) {
            this.zw = zw;
        }

        public int getMechanism_id() {
            return mechanism_id;
        }

        public void setMechanism_id(int mechanism_id) {
            this.mechanism_id = mechanism_id;
        }

        public String getMechanism() {
            return mechanism;
        }

        public void setMechanism(String mechanism) {
            this.mechanism = mechanism;
        }

        public int getKcpx_num() {
            return kcpx_num;
        }

        public void setKcpx_num(int kcpx_num) {
            this.kcpx_num = kcpx_num;
        }

        public int getKwxx_num() {
            return kwxx_num;
        }

        public void setKwxx_num(int kwxx_num) {
            this.kwxx_num = kwxx_num;
        }

        public int getKcpx_time() {
            return kcpx_time;
        }

        public void setKcpx_time(int kcpx_time) {
            this.kcpx_time = kcpx_time;
        }

        public int getKwxx_time() {
            return kwxx_time;
        }

        public void setKwxx_time(int kwxx_time) {
            this.kwxx_time = kwxx_time;
        }

        public int getEnd_num() {
            return end_num;
        }

        public void setEnd_num(int end_num) {
            this.end_num = end_num;
        }

        public int getPass_num() {
            return pass_num;
        }

        public void setPass_num(int pass_num) {
            this.pass_num = pass_num;
        }

        public int getNopass_num() {
            return nopass_num;
        }

        public void setNopass_num(int nopass_num) {
            this.nopass_num = nopass_num;
        }

        public int getFile_num() {
            return file_num;
        }

        public void setFile_num(int file_num) {
            this.file_num = file_num;
        }

        public List<HistoryBean> getHistory() {
            return history;
        }

        public void setHistory(List<HistoryBean> history) {
            this.history = history;
        }

        public static class HistoryBean {
            /**
             * curriculum_id : 1
             * title : 通用人工智能路在何方（国家_资源_开发）
             * type : 2
             * teacher : 杨学山
             * gs : 原工业和信息化部副部长
             * log : http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg
             * len : 1532
             */

            private int curriculum_id;
            private String title;
            private int type;
            private String teacher;
            private String gs;
            private String log;
            private int len;

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
        }
    }
}
