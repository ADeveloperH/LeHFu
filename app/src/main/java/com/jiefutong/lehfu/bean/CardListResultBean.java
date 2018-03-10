package com.jiefutong.lehfu.bean;

import java.util.List;

/**
 * author：hj
 * time: 2018/3/7 0007 15:08
 * description:
 */


public class CardListResultBean {


    /**
     * status : 1
     * info : {"clist":[{"id":"18","uid":"53","card_no":"6225623500000000","bind_id":"c2a0dc3d256341c3a3d0b6bcdb145eb1","tel":"13299000007","expired":"0922","cvv2":"909","bank_name":"广发银行","statement":"21","repayment":"10","year":"22","month":"09","is_del":"0"}],"blist":[{"id":"19","uid":"53","card_no":"6225623500000000","bind_id":"4649a265a79b44439be343ad881ee544","tel":"13299000007","expired":"0819","cvv2":"404","bank_name":"招商银行","statement":"19","repayment":"7","year":"19","month":"08","is_del":"0"}]}
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
         * id : 18
         * uid : 53
         * card_no : 6225623500000000
         * bind_id : c2a0dc3d256341c3a3d0b6bcdb145eb1
         * tel : 13299000007
         * expired : 0922
         * cvv2 : 909
         * bank_name : 广发银行
         * statement : 21
         * repayment : 10
         * year : 22
         * month : 09
         * is_del : 0
         */

        private List<ClistEntity> clist;
        /**
         * id : 19
         * uid : 53
         * card_no : 6225623500000000
         * bind_id : 4649a265a79b44439be343ad881ee544
         * tel : 13299000007
         * expired : 0819
         * cvv2 : 404
         * bank_name : 招商银行
         * statement : 19
         * repayment : 7
         * year : 19
         * month : 08
         * is_del : 0
         */

        private List<BlistEntity> blist;

        public List<ClistEntity> getClist() {
            return clist;
        }

        public void setClist(List<ClistEntity> clist) {
            this.clist = clist;
        }

        public List<BlistEntity> getBlist() {
            return blist;
        }

        public void setBlist(List<BlistEntity> blist) {
            this.blist = blist;
        }

        public static class ClistEntity {
            private String id;
            private String uid;
            private String card_no;
            private String bind_id;
            private String tel;
            private String expired;
            private String cvv2;
            private String bank_name;
            private String statement;
            private String repayment;
            private String year;
            private String month;
            private String is_del;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getBind_id() {
                return bind_id;
            }

            public void setBind_id(String bind_id) {
                this.bind_id = bind_id;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getExpired() {
                return expired;
            }

            public void setExpired(String expired) {
                this.expired = expired;
            }

            public String getCvv2() {
                return cvv2;
            }

            public void setCvv2(String cvv2) {
                this.cvv2 = cvv2;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getStatement() {
                return statement;
            }

            public void setStatement(String statement) {
                this.statement = statement;
            }

            public String getRepayment() {
                return repayment;
            }

            public void setRepayment(String repayment) {
                this.repayment = repayment;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }
        }

        public static class BlistEntity {
            private String id;
            private String uid;
            private String card_no;
            private String bind_id;
            private String tel;
            private String expired;
            private String cvv2;
            private String bank_name;
            private String statement;
            private String repayment;
            private String year;
            private String month;
            private String is_del;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getBind_id() {
                return bind_id;
            }

            public void setBind_id(String bind_id) {
                this.bind_id = bind_id;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getExpired() {
                return expired;
            }

            public void setExpired(String expired) {
                this.expired = expired;
            }

            public String getCvv2() {
                return cvv2;
            }

            public void setCvv2(String cvv2) {
                this.cvv2 = cvv2;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getStatement() {
                return statement;
            }

            public void setStatement(String statement) {
                this.statement = statement;
            }

            public String getRepayment() {
                return repayment;
            }

            public void setRepayment(String repayment) {
                this.repayment = repayment;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }
        }
    }
}
