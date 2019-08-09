package io.github.j4cku.java11.string;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * String.repeat(int) String.lines() String.strip() String.stripLeading() String.stripTrailing() String.isBlank()
 */
public class Example {

    private static void writeHeader(final String headerText) {
        final String headerSeparator = "=".repeat(headerText.length() + 4);

        System.out.println("\n" + headerSeparator);
        System.out.println(headerText);
        System.out.println(headerSeparator);
    }


    private static void demonstrateStringLines() {
        String originalString = "Hello\nWorld\n123";

        String stringWithoutLineSeparators = originalString.replaceAll("\\n", "\\\\n");

        writeHeader("String.lines() on '" + stringWithoutLineSeparators + "'");

        originalString.lines().forEach(System.out::println);
    }

    private static void demonstrateStringStrip() {
        String originalString = "  qiagen.com  23333  ";

        writeHeader("String.strip() on '" + originalString + "'");
        System.out.println("'" + originalString.strip() + "'");
    }

    private static void demonstrateStringStripLeading() {
        String originalString = "  qiagen.com  23333  ";

        writeHeader("String.stripLeading() on '" + originalString + "'");
        System.out.println("'" + originalString.stripLeading() + "'");
    }

    private static void demonstrateStringStripTrailing() {
        String originalString = "  qiagen.com  23333  ";

        writeHeader("String.stripTrailing() on '" + originalString + "'");
        System.out.println("'" + originalString.stripTrailing() + "'");
    }

    private static void demonstrateStringIsBlank() {
        writeHeader("String.isBlank()");

        String emptyString = "";
        System.out.println("Empty string    -> " + emptyString.isBlank());

        String onlyLineSeparator = System.getProperty("line.separator");
        System.out.println("Line separator     -> " + onlyLineSeparator.isBlank());

        String tabOnly = "\t";
        System.out.println("Tab -> " + tabOnly.isBlank());

        String spacesOnly = "   ";
        System.out.println("Spaces       -> " + spacesOnly.isBlank());
    }

    private static void demonstrateIndent() {
        writeHeader("String.indent(10) on 'Qiagen.com hello'");
        String str = "Qiagen.com hello";
        System.out.println(str.indent(10));

        writeHeader("String.indent(-1) on ' Qiagen.com hello'");
        String str2 = "  Qiagen.com hello";
        System.out.println(str2.indent(-1));

        writeHeader("String.indent(-10) on ' Qiagen.com hello'");
        System.out.println(str2.indent(-10));
    }

    private static void demonstrateTransform() {
        writeHeader("String.transform() on ' mon\t day ' to Day enum");
        String str = " mon\t day ";
        Day s2 = str
            .transform(s -> s.replaceAll("\\s", ""))
            .transform(String::strip)
            .transform(String::toUpperCase)
            .transform(Day::valueOf);
        System.out.println(s2.name());
    }

    public static void demonstrateTextBlock() {
        /*
        unfortunately java 13
        var s = """
                is this a text block
                """;
        System.out.println(s);
        */
    }

    public static void main(String[] args) {
        demonstrateStringLines();
        demonstrateStringStrip();
        demonstrateStringStripLeading();
        demonstrateStringStripTrailing();
        demonstrateStringIsBlank();
        demonstrateIndent();
        demonstrateTransform();
    }

    private enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

}
