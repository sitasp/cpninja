package org.example.entity;

public enum Source {
    ATCODER("atcoder"),
    CODEFORCES("codeforces"), HACKEREARTH("hackerearth"),
    CODECHEF("codechef"), A2OJ("a2oj"), HACKERRANK("hackerrank"), UVAOJ("uva"), SPOJ("spoj"),
    LEETCODE("leetcode");

    private final String site;

    private Source(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }
}
