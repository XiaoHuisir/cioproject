package com.example.myapplication.bean;

import java.util.List;

public class NoticeListBean {

    /**
     * status : 1
     * data : [{"notice_id":20,"title":"测试","content":"测试测试测试测试测试","add_time":1577936137},{"notice_id":1,"title":"系统通知","content":"信息技术正深刻地改变着人类的生产和生活方式，人类正在加速迈入信息社会。以云计算、大数据、移动互联网、物联网、人工智能和区块链技术为代表的新一代信息技术正在深刻改变各个行业的业务模式和管理模式，并带来新一轮的数字化转型。","add_time":1577350904}]
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
         * notice_id : 20
         * title : 测试
         * content : 测试测试测试测试测试
         * add_time : 1577936137
         */

        private int notice_id;
        private String title;
        private String content;
        private int add_time;

        public int getNotice_id() {
            return notice_id;
        }

        public void setNotice_id(int notice_id) {
            this.notice_id = notice_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }
    }
}
