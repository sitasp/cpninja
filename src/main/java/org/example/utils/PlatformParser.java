package org.example.utils;

import org.example.entity.Source;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class PlatformParser {



    public static class CodeforcesParser {
        private static final String regexPattern = "^(https?://)codeforces\\.com/contest/\\d+/problem/\\w+/?$";
        private static final Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        public static boolean matchesPattern(String url) {
            //  "https://codeforces.com/contest/1832/problem/F";
            //  "http://codeforces.com/contest/1832/problem/F/";
            // "https://codeforces.com/contest/1832/problem/F/";
            // "http://codeforces.com/contest/1832/problem/F";
            return pattern.matcher(url).matches();
        }
        public static String extractCodeNameContest(String url) {
            //contest
            try {
                URI uri = new URI(url);
                String path = uri.getPath();
                if (path != null && !path.isEmpty()) {
                    String[] segments = path.split("/");
                    if (segments.length >= 5) {
                        String contest = segments[2];
                        String problem = segments[4];
                        return contest + "_" + problem;
                    }
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static String extractCodeNameProblemset(String url) {
            //problemset
            try {
                URI uri = new URI(url);
                String[] segments = uri.getPath().split("/");
                String problemCode = segments[segments.length - 2] + "_" + segments[segments.length - 1];

                return problemCode;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }
        public static void createCodeforcesTitle(StringBuilder sb, Source source, String url) {
            if(matchesPattern(url)) {
                sb.append(source.getTiny());
                sb.append("_");
                sb.append(extractCodeNameContest(url));
            }
            else {
                sb.append(source.getTiny());
                sb.append("_");
                sb.append(extractCodeNameProblemset(url));
            }
        }
    }




    public static class AtcoderParser {

        public static String extractCodeName(String url) {
            try {
                URI uri = new URI(url);
                String[] segments = uri.getPath().split("/");

                return segments[segments.length - 1];
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static void createAtcoderTitle(StringBuilder sb, Source source, String url) {
            sb.append(source.getTiny());
            sb.append("_");
            sb.append(extractCodeName(url));
        }
    }
}
