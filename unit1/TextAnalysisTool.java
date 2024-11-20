import java.util.*;

public class TextAnalysisTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: User Input
        String text;
        do {
            System.out.println("Enter a paragraph or lengthy text (cannot be empty):");
            text = scanner.nextLine().trim();
        } while (text.isEmpty()); // Ensure input is not empty

        // Step 2: Character Count
        int charCount = text.length();
        System.out.println("Total number of characters: " + charCount);

        // Step 3: Word Count
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        System.out.println("Total number of words: " + wordCount);

        // Step 4: Most Common Character
        char mostCommonChar = findMostCommonCharacter(text);
        System.out.println("Most common character: " + mostCommonChar);

        // Step 5: Character Frequency
        char targetChar;
        while (true) {
            System.out.println("Enter a character to find its frequency:");
            String input = scanner.nextLine().trim();
            if (input.length() == 1) {
                targetChar = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid input. Please enter a single character.");
            }
        }
        int charFrequency = findCharacterFrequency(text, targetChar);
        System.out.println("Frequency of '" + targetChar + "': " + charFrequency);

        // Step 6: Word Frequency
        String targetWord;
        do {
            System.out.println("Enter a word to find its frequency (cannot be empty):");
            targetWord = scanner.nextLine().trim();
        } while (targetWord.isEmpty());
        int wordFrequency = findWordFrequency(words, targetWord);
        System.out.println("Frequency of \"" + targetWord + "\": " + wordFrequency);

        // Step 7: Unique Words
        int uniqueWordCount = findUniqueWordCount(words);
        System.out.println("Number of unique words: " + uniqueWordCount);

        scanner.close();
    }

    // Helper methods remain the same as before...
    private static char findMostCommonCharacter(String text) {
        Map<Character, Integer> charMap = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) { // Consider only letters and digits
                charMap.put(c, charMap.getOrDefault(c, 0) + 1);
            }
        }

        char mostCommon = '\0';
        int maxFrequency = 0;
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostCommon = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }
        return mostCommon;
    }

    private static int findCharacterFrequency(String text, char targetChar) {
        int count = 0;
        char target = Character.toLowerCase(targetChar);
        for (char c : text.toLowerCase().toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }

    private static int findWordFrequency(String[] words, String targetWord) {
        int count = 0;
        String target = targetWord.toLowerCase();
        for (String word : words) {
            if (word.toLowerCase().equals(target)) {
                count++;
            }
        }
        return count;
    }

    private static int findUniqueWordCount(String[] words) {
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word.toLowerCase());
        }
        return uniqueWords.size();
    }
}
