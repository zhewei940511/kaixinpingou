package com.laojiashop.laojia.entity;

import java.util.List;

public class SearchKeywordsBean {

    private List<UserWordBean> user_word;
    private List<HotWordBean> hot_word;
    public List<UserWordBean> getUser_word() {
        return user_word;
    }

    public void setUser_word(List<UserWordBean> user_word) {
        this.user_word = user_word;
    }

    public List<HotWordBean> getHot_word() {
        return hot_word;
    }

    public void setHot_word(List<HotWordBean> hot_word) {
        this.hot_word = hot_word;
    }
    public static class UserWordBean
    {
        private String word;
        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }
    public static class HotWordBean {
        /**
         * word : 系统热词1
         */

        private String word;

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }
    }
}
