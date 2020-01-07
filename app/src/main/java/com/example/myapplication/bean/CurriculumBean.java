package com.example.myapplication.bean;

import java.util.List;

public class CurriculumBean {

    /**
     * status : 1
     * data : {"curriculum_data":{"id":1,"title":"通用人工智能路在何方","teacher":"杨学山","gs":"原工业和信息化部副部长","content":"2018年，金融行业漏洞数量增长趋势势头不减，不论是漏洞的规模、出现的速度还是整体数量都给企业漏洞管理带来极大挑战。以互联网金融为例，2018年，国家互联网应急中心（CNCERT）对430个互联网金融APP进行检测，共发现安全漏洞1,005个，其中高危漏洞240个，明文数据传输漏洞数量最多有50个(占高危漏洞数量的20.8%)，其次是网页视图（Webview）明文存储密码漏洞有48个（占20.0%）和源代码反编译漏洞有31个（占12.9%）。这些安全漏洞可能威胁交易授权和数据保护，存在数据泄露风险，其中部分安全漏洞影响应用程序的文件保护，不能有效阻止应用程序被逆向或者反编译，进而使应用暴露出多种安全风险。","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","len":2256,"video_url_code":"http://images.ciotimes.com/yangxueshan.mp4","audio_url_code":""},"file_data":[{"id":1,"name":"打造数据驱动的企业.pdf","url":"http://images.ciotimes.com/o_1dq6dauml14qn1dg012s6rpubuvi.pdf","size":78848,"add_time":1577165104,"curriculum_id":1}],"have_evaluat":"1","record_data":{"fraction":80,"is_pass":1}}
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
         * curriculum_data : {"id":1,"title":"通用人工智能路在何方","teacher":"杨学山","gs":"原工业和信息化部副部长","content":"2018年，金融行业漏洞数量增长趋势势头不减，不论是漏洞的规模、出现的速度还是整体数量都给企业漏洞管理带来极大挑战。以互联网金融为例，2018年，国家互联网应急中心（CNCERT）对430个互联网金融APP进行检测，共发现安全漏洞1,005个，其中高危漏洞240个，明文数据传输漏洞数量最多有50个(占高危漏洞数量的20.8%)，其次是网页视图（Webview）明文存储密码漏洞有48个（占20.0%）和源代码反编译漏洞有31个（占12.9%）。这些安全漏洞可能威胁交易授权和数据保护，存在数据泄露风险，其中部分安全漏洞影响应用程序的文件保护，不能有效阻止应用程序被逆向或者反编译，进而使应用暴露出多种安全风险。","log":"http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg","len":2256,"video_url_code":"http://images.ciotimes.com/yangxueshan.mp4","audio_url_code":""}
         * file_data : [{"id":1,"name":"打造数据驱动的企业.pdf","url":"http://images.ciotimes.com/o_1dq6dauml14qn1dg012s6rpubuvi.pdf","size":78848,"add_time":1577165104,"curriculum_id":1}]
         * have_evaluat : 1
         * record_data : {"fraction":80,"is_pass":1}
         */

        private CurriculumDataBean curriculum_data;
        private String have_evaluat;
        private RecordDataBean record_data;
        private List<FileDataBean> file_data;

        public CurriculumDataBean getCurriculum_data() {
            return curriculum_data;
        }

        public void setCurriculum_data(CurriculumDataBean curriculum_data) {
            this.curriculum_data = curriculum_data;
        }

        public String getHave_evaluat() {
            return have_evaluat;
        }

        public void setHave_evaluat(String have_evaluat) {
            this.have_evaluat = have_evaluat;
        }

        public RecordDataBean getRecord_data() {
            return record_data;
        }

        public void setRecord_data(RecordDataBean record_data) {
            this.record_data = record_data;
        }

        public List<FileDataBean> getFile_data() {
            return file_data;
        }

        public void setFile_data(List<FileDataBean> file_data) {
            this.file_data = file_data;
        }

        public static class CurriculumDataBean {
            /**
             * id : 1
             * title : 通用人工智能路在何方
             * teacher : 杨学山
             * gs : 原工业和信息化部副部长
             * content : 2018年，金融行业漏洞数量增长趋势势头不减，不论是漏洞的规模、出现的速度还是整体数量都给企业漏洞管理带来极大挑战。以互联网金融为例，2018年，国家互联网应急中心（CNCERT）对430个互联网金融APP进行检测，共发现安全漏洞1,005个，其中高危漏洞240个，明文数据传输漏洞数量最多有50个(占高危漏洞数量的20.8%)，其次是网页视图（Webview）明文存储密码漏洞有48个（占20.0%）和源代码反编译漏洞有31个（占12.9%）。这些安全漏洞可能威胁交易授权和数据保护，存在数据泄露风险，其中部分安全漏洞影响应用程序的文件保护，不能有效阻止应用程序被逆向或者反编译，进而使应用暴露出多种安全风险。
             * log : http://images.ciotimes.com/o_1dkcd0g6eah7c9i1n251aueadhu.jpg
             * len : 2256
             * video_url_code : http://images.ciotimes.com/yangxueshan.mp4
             * audio_url_code :
             */

            private int id;
            private String title;
            private String teacher;
            private String gs;
            private String content;
            private String log;
            private int len;
            private String video_url_code;
            private String audio_url_code;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public String getVideo_url_code() {
                return video_url_code;
            }

            public void setVideo_url_code(String video_url_code) {
                this.video_url_code = video_url_code;
            }

            public String getAudio_url_code() {
                return audio_url_code;
            }

            public void setAudio_url_code(String audio_url_code) {
                this.audio_url_code = audio_url_code;
            }
        }

        public static class RecordDataBean {
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            /**
             * fraction : 80
             * is_pass : 1
             */
            private int id;
            private int fraction;
            private int is_pass;

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
        }

        public static class FileDataBean {
            /**
             * id : 1
             * name : 打造数据驱动的企业.pdf
             * url : http://images.ciotimes.com/o_1dq6dauml14qn1dg012s6rpubuvi.pdf
             * size : 78848
             * add_time : 1577165104
             * curriculum_id : 1
             */

            private int id;
            private String name;
            private String url;
            private int size;
            private int add_time;
            private int curriculum_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }

            public int getCurriculum_id() {
                return curriculum_id;
            }

            public void setCurriculum_id(int curriculum_id) {
                this.curriculum_id = curriculum_id;
            }
        }
    }
}
