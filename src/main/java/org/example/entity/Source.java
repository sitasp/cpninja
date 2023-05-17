package org.example.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Source {
    ATCODER("atcoder", "at"),
    CODEFORCES("codeforces", "cf"), HACKEREARTH("hackerearth", "he"),
    CODECHEF("codechef", "cc"), A2OJ("a2oj", "a2"), HACKERRANK("hackerrank", "hr"), UVAOJ("uva", "uv"), SPOJ("spoj", "sp"),
    LEETCODE("leetcode", "lc");

    private final String site;
    private final String tiny;

    private Source(String site, String tiny) {
        this.site = site;
        this.tiny = tiny;
    }

    public static Source parseSourceFromURL(String url) {
        for(Source source: Source.values()) {
            Pattern pattern = Pattern.compile("https?://www\\." + source.getSite() + "\\.com/.*");
            Matcher matcher = pattern.matcher(url);

            if(matcher.find()) {
                return source;
            }
        }

        return null;
    }

    public String getSite() {
        return site;
    }

    public String getTiny() {
        return tiny;
    }
}
