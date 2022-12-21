import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("src/input.txt");
        Scanner sc = new Scanner(inputStream);
        int sum = 0;
        ArrayList<String> allLinesInList = new ArrayList<>();
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            String[] lineInHalf = splitStringInHalf(nextLine);


            char winner = findCommonCharOutOf2(lineInHalf[0], lineInHalf[1]);
            int winnerInInt = turnCharIntoNumber(winner);
            sum += winnerInInt;
            allLinesInList.add(nextLine);
        }
        sc.close();
        System.out.println(sum);


        int howManyTimesShouldILoop = allLinesInList.size() / 3;
        int specialLoopCounter = 0;
        int sum2 = 0;
        for (int i = 0; i < howManyTimesShouldILoop; i++) {
            List<String> stringList = new ArrayList<>();
            stringList.add(allLinesInList.get(specialLoopCounter));
            stringList.add(allLinesInList.get(specialLoopCounter + 1));
            stringList.add(allLinesInList.get(specialLoopCounter + 2));
            specialLoopCounter += 3;

            sum2+= turnCharIntoNumber(findCommonCharOutOf3(stringList));
        }
        System.out.println(sum2);
    }

    public static String[] splitStringInHalf(String string) {
        char[] allCharactersArray = string.toCharArray();
        StringBuilder firstHalf = new StringBuilder();
        StringBuilder secondHalf = new StringBuilder();
        int lengthOfString = string.length();

        //Starts at 0
        for (int i = 0; i < lengthOfString / 2; i++) {
            firstHalf.append(allCharactersArray[i]);
        }

        //Starts at half
        for (int i = lengthOfString / 2; i < lengthOfString; i++) {
            secondHalf.append(allCharactersArray[i]);
        }
        String[] strings = new String[2];
        strings[0] = firstHalf.toString();
        strings[1] = secondHalf.toString();

        return strings;
    }

    public static int turnCharIntoNumber(char character) {
        int number = 0;

        if (Character.isLowerCase(character)) {
            number = character - '0' - 48;
        } else if (Character.isUpperCase(character)) {
            number = character - '0' + 10;
        }
        return number;
    }

    public static char findCommonCharOutOf3(List<String> stringList) {
        String shortestString = stringList
                .stream()
                .min(Comparator.comparingInt(String::length))
                .get();

        char winner = 0;

        for (int i = 0; i < shortestString.length(); i++) {
            String searchForChar = String.valueOf(shortestString.charAt(i));
            if (stringList.get(1).contains(searchForChar)
                    && stringList.get(2).contains(searchForChar)
            && stringList.get(0).contains(searchForChar)) {
               winner = shortestString.charAt(i);
            }
        }
        return winner;
    }

    public static char findCommonCharOutOf2(final String string1,
                                            final String string2) {
        String longestString;
        String shortestString;
        char winner = 0;
        if (string1.length() < string2.length()) {
            shortestString = string1;
            longestString = string2;
        }
        else {
            longestString = string1;
            shortestString = string2;
        }

        int lengthShortestString = shortestString.length();
        int lengthLongestString = longestString.length();
        for (int j = 0; j < lengthShortestString; j++) {
            for (int i = 0; i < lengthLongestString; i++) {
                if (longestString.charAt(i) == shortestString.charAt(j)) {
                    winner = longestString.charAt(i);
                }
            }
        }
        return winner;
    }
}
