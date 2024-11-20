import java.util.*;

public class TextAnalysisTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: User Input
        System.out.println("Enter a paragraph or lengthy text:");
        String text = scanner.nextLine();

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
        System.out.println("Enter a character to find its frequency:");
        char targetChar = scanner.next().charAt(0);
        int charFrequency = findCharacterFrequency(text, targetChar);
        System.out.println("Frequency of '" + targetChar + "': " + charFrequency);

        // Step 6: Word Frequency
        scanner.nextLine(); // Consume leftover newline
        System.out.println("Enter a word to find its frequency:");
        String targetWord = scanner.nextLine();
        int wordFrequency = findWordFrequency(words, targetWord);
        System.out.println("Frequency of \"" + targetWord + "\": " + wordFrequency);

        // Step 7: Unique Words
        int uniqueWordCount = findUniqueWordCount(words);
        System.out.println("Number of unique words: " + uniqueWordCount);

        scanner.close();
    }

    // Helper method: Find the most common character
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

    // Helper method: Find character frequency (case-insensitive)
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

    // Helper method: Find word frequency (case-insensitive)
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

    // Helper method: Find unique word count (case-insensitive)
    private static int findUniqueWordCount(String[] words) {
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word.toLowerCase());
        }
        return uniqueWords.size();
    }
}
