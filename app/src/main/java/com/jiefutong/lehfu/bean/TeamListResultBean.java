package com.jiefutong.lehfu.bean;

import java.util.List;

/**
 * author：hj
 * time: 2018/3/7 0007 09:11
 * description:
 */


public class TeamListResultBean {

    /**
     * status : 1
     * info : {"list":[{"id":"48","truename":"李四","tel":"18695870000","pid":"53","register_time":"1516690936"},{"id":"50","truename":"张三","tel":"13611180000","pid":"53","register_time":"1516774726"}]}
     */

    private int status;
    private InfoEntity info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public static class InfoEntity {
        /**
         * id : 48
         * truename : 李四
         * tel : 18695870000
         * pid : 53
         * register_time : 1516690936
         */

        private List<ListEntity> list;

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            private String id;
            private String truename;
            private String tel;
            private String pid;
            private String register_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTruename() {
                return truename;
            }

            public void setTruename(String truename) {
                this.truename = truename;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getRegister_time() {
                return register_time;
            }

            public void setRegister_time(String register_time) {
                this.register_time = register_time;
            }
        }
    }
}
