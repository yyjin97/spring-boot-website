package com.spring.springweb.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Criteria {

    private int pageNum;
    private int amount;

    private int type;
    private String keyword;

    public Criteria() {
        this(1, 10, 0, "");
    }

    public Criteria(int pageNum, int amount) { this(pageNum, amount, 0, ""); }

    public Criteria(int pageNum, int amount, int type, String keyword) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.type = type;
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "pageNum=" + pageNum +
                ", amount=" + amount +
                ", type=" + type +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
