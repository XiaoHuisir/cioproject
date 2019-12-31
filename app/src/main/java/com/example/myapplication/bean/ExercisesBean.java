package com.example.myapplication.bean;

import java.util.List;

public class ExercisesBean {

    /**
     * status : 1
     * data : [{"id":23,"title":"下面哪一个不属于智能风控的功能架构模块？","type":1,"fraction":20,"option":[{"id":33,"title":"有监督学习"},{"id":34,"title":"无监督学习"},{"id":35,"title":"专家系统"},{"id":36,"title":"防火墙"}]},{"id":41,"title":"下面哪一个不属于区块链技术？","type":1,"fraction":20,"option":[{"id":57,"title":"账本技术"},{"id":58,"title":"机器学习"},{"id":59,"title":"加密"},{"id":60,"title":"智能合约"}]},{"id":42,"title":"下面哪一个表述不正确？\t","type":1,"fraction":20,"option":[{"id":53,"title":"区块链是建立信用的一种方式\t"},{"id":54,"title":"区块链可用于边缘计算中的物联网安全"},{"id":55,"title":"区块链是一种中心化的分布式账本数据库"},{"id":56,"title":"区块链最早是比特币的底层技术\t"}]},{"id":21,"title":"关于人工智能的发展历史，下面哪一个表述不正确？\t","type":1,"fraction":20,"option":[{"id":25,"title":"约翰.麦卡锡在1956年的达特茅斯会议上首次提出人工智能"},{"id":26,"title":"人工智能的发展曾几起几落"},{"id":27,"title":"目前，人工智能发展已经进入强人工智能阶段"},{"id":28,"title":"大数据推动了新一代人工智能的发展"}]},{"id":7,"title":"dasf","type":1,"fraction":20,"option":[{"id":15,"title":"正确"},{"id":16,"title":"错误"}]}]
     * msg :
     * code : 10000
     */

    private int status;
    private String msg;
    private int code;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 23
         * title : 下面哪一个不属于智能风控的功能架构模块？
         * type : 1
         * fraction : 20
         * option : [{"id":33,"title":"有监督学习"},{"id":34,"title":"无监督学习"},{"id":35,"title":"专家系统"},{"id":36,"title":"防火墙"}]
         */

        private int id;
        private String title;
        private int type;
        private int fraction;
        private List<OptionBean> option;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
             * id : 33
             * title : 有监督学习
             */

            private int id;
            private String title;

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
        }
    }
}
