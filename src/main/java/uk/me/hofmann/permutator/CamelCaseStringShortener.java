package uk.me.hofmann.permutator;

/**
 * Shortens strings: CamelCaseStringShortener -> CCSH
 * First character is always appended, regardless of its case: camelCaseStringShortener -> cCSH
 */
public class CamelCaseStringShortener {

    public static String shorten(String toShorten) {
        char[] toShortenChars = toShorten.toCharArray();
        if (toShortenChars.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(toShortenChars[0]);


        for (int i = 1; i < toShortenChars.length; i++) {
            char current = toShortenChars[i];
            if (Character.isUpperCase(current)) {
                sb.append(current);
            }
        }

        return sb.toString();
    }
}
