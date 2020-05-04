package com.example.iwapp.bean;

import java.util.List;

public class EvaluatShowResultBean {

    /**
     * status : 1
     * data :
     * {"id":1,"curriculum_id":1,"fraction":100,"is_pass":1,"success":5,"error":0,"correct":100,"add_time":1577255236,"title":"通用人工智能路在何方","num":5,"evaluat_result":[{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？","fraction":20,"option":[{"id":2,"title":"约翰.麦卡锡在1956年的达特茅斯会议上首次提出人工智能","is_select":"1"},{"id":3,"title":"人工智能的发展曾几起几落","is_select":"0"}]},{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？1","fraction":20,"option":[{"id":8,"title":"正确","is_select":"1"},{"id":9,"title":"错误","is_select":"0"},{"id":10,"title":"正确1","is_select":"1"}]},{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？2","fraction":20,"option":[{"id":11,"title":"正确","is_select":"1"},{"id":12,"title":"错误","is_select":"0"}]},{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？3","fraction":20,"option":[{"id":13,"title":"正确","is_select":"1"},{"id":14,"title":"错误","is_select":"0"}]},{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？4","fraction":20,"option":[{"id":15,"title":"正确","is_select":"1"},{"id":16,"title":"错误","is_select":"0"}]}]}
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
         * id : 1
         * curriculum_id : 1
         * fraction : 100
         * is_pass : 1
         * success : 5
         * error : 0
         * correct : 100
         * add_time : 1577255236
         * title : 通用人工智能路在何方
         * num : 5
         * evaluat_result : [{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？","fraction":20,"option":[{"id":2,"title":"约翰.麦卡锡在1956年的达特茅斯会议上首次提出人工智能","is_select":"1"},{"id":3,"title":"人工智能的发展曾几起几落","is_select":"0"}]},{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？1","fraction":20,"option":[{"id":8,"title":"正确","is_select":"1"},{"id":9,"title":"错误","is_select":"0"},{"id":10,"title":"正确1","is_select":"1"}]},{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？2","fraction":20,"option":[{"id":11,"title":"正确","is_select":"1"},{"id":12,"title":"错误","is_select":"0"}]},{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？3","fraction":20,"option":[{"id":13,"title":"正确","is_select":"1"},{"id":14,"title":"错误","is_select":"0"}]},{"success":1,"title":"关于人工智能的发展历史，下面哪一个表述不正确？4","fraction":20,"option":[{"id":15,"title":"正确","is_select":"1"},{"id":16,"title":"错误","is_select":"0"}]}]
         */

        private int id;
        private int curriculum_id;
        private int fraction;
        private int is_pass;
        private int success;
        private int error;
        private int correct;
        private int add_time;
        private String title;
        private int num;
        private List<EvaluatResultBean> evaluat_result;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCurriculum_id() {
            return curriculum_id;
        }

        public void setCurriculum_id(int curriculum_id) {
            this.curriculum_id = curriculum_id;
        }

        public int getFraction() {
            return fraction;
        }

        public void setFraction(int fraction) {
            this.fraction = fraction;
        }

        public int getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(int is_pass) {
            this.is_pass = is_pass;
        }

        public int getSuccess() {
            return success;
        }

        public void setSuccess(int success) {
            this.success = success;
        }

        public int getError() {
            return error;
        }

        public void setError(int error) {
            this.error = error;
        }

        public int getCorrect() {
            return correct;
        }

        public void setCorrect(int correct) {
            this.correct = correct;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<EvaluatResultBean> getEvaluat_result() {
            return evaluat_result;
        }

        public void setEvaluat_result(List<EvaluatResultBean> evaluat_result) {
            this.evaluat_result = evaluat_result;
        }

        public static class EvaluatResultBean {
            /**
             * success : 1
             * title : 关于人工智能的发展历史，下面哪一个表述不正确？
             * fraction : 20
             * option : [{"id":2,"title":"约翰.麦卡锡在1956年的达特茅斯会议上首次提出人工智能","is_select":"1"},{"id":3,"title":"人工智能的发展曾几起几落","is_select":"0"}]
             */

            private int success;
            private String title;
            private int fraction;
            private List<OptionBean> option;

            public int getSuccess() {
                return success;
            }

            public void setSuccess(int success) {
                this.success = success;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getFraction() {
                return fraction;
            }

            public void setFraction(int fraction) {
                this.fraction = fraction;
            }

            public List<OptionBean> getOption() {
                return option;
            }

            public void setOption(List<OptionBean> option) {
                this.option = option;
            }

            public static class OptionBean {
                /**
                 * id : 2
                 * title : 约翰.麦卡锡在1956年的达特茅斯会议上首次提出人工智能
                 * is_select : 1
                 */

                private int id;
                private String title;
                private String is_select;

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

                public String getIs_select() {
                    return is_select;
                }

                public void setIs_select(String is_select) {
                    this.is_select = is_select;
                }
            }
        }
    }
}
