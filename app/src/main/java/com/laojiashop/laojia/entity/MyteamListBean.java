package com.laojiashop.laojia.entity;

import java.util.List;

public class MyteamListBean {


        /**
         * total : 4
         * list : [{"id":59462,"headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","name":"测试初级导购","phone":"18888888881","total_number_teams":3,"number_team_added_today":0,"team_performance":"1.76w","team_new_performance_today":"0","level":4,"create_time":"2020-05-19 15:20:25"},{"id":59468,"headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","name":"团队59468","phone":"18888888884","total_number_teams":0,"number_team_added_today":0,"team_performance":"0","team_new_performance_today":"0","level":4,"create_time":"2020-05-19 16:01:01"},{"id":59469,"headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","name":"团队59469","phone":"18888888885","total_number_teams":0,"number_team_added_today":0,"team_performance":"20.07w","team_new_performance_today":"0","level":4,"create_time":"2020-05-19 16:01:03"},{"id":59471,"headimgurl":"http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg","name":"团队59471","phone":"18888888887","total_number_teams":0,"number_team_added_today":0,"team_performance":"99","team_new_performance_today":"0","level":3,"create_time":"2020-06-22 19:23:35"}]
         * pageCount : 1
         * pageSzie : 10
         */

        private int total;
        private int pageCount;
        private int pageSzie;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageSzie() {
            return pageSzie;
        }

        public void setPageSzie(int pageSzie) {
            this.pageSzie = pageSzie;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 59462
             * headimgurl : http://himg.bdimg.com/sys/portrait/item/a6607a617a69353233363233000f.jpg
             * name : 测试初级导购
             * phone : 18888888881
             * total_number_teams : 3
             * number_team_added_today : 0
             * team_performance : 1.76w
             * team_new_performance_today : 0
             * level : 4
             * create_time : 2020-05-19 15:20:25
             */

            private int id;
            private String headimgurl;
            private String name;
            private String phone;
            private int total_number_teams;
            private int number_team_added_today;
            private String team_performance;
            private String team_new_performance_today;
            private int level;
            private String create_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getTotal_number_teams() {
                return total_number_teams;
            }

            public void setTotal_number_teams(int total_number_teams) {
                this.total_number_teams = total_number_teams;
            }

            public int getNumber_team_added_today() {
                return number_team_added_today;
            }

            public void setNumber_team_added_today(int number_team_added_today) {
                this.number_team_added_today = number_team_added_today;
            }

            public String getTeam_performance() {
                return team_performance;
            }

            public void setTeam_performance(String team_performance) {
                this.team_performance = team_performance;
            }

            public String getTeam_new_performance_today() {
                return team_new_performance_today;
            }

            public void setTeam_new_performance_today(String team_new_performance_today) {
                this.team_new_performance_today = team_new_performance_today;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
}
