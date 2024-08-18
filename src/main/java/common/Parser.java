package common;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private Parser() {}

    public static List<String> fromStringToList(String input) {
        return Arrays.stream(input.split(","))
               .map(String::trim)
               .toList();
    }

    public static String fromListToString(List<String> input) {
        return String.join(",", input);
    }
}
