import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.*;

public class LexicalAnalyzer {
    private HashSet<String> keywords;
    private HashSet<String> operators;

    public LexicalAnalyzer() {
        initializeKeywords();
        initializeOperators();
    }

    private void initializeKeywords() {
        String[] keywordArray = { "auto", "break", "case", "const", "continue", "default", "do", "double",
                "enum", "extern", "for", "goto", "if", "inline", "long", "register", "return", "short",
                "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "volatile",
                "while", "include", "stdio.h", "int", "char" };

        keywords = new HashSet<>(Arrays.asList(keywordArray));
    }

    private void initializeOperators() {
        String[] operatorArray = { "+", "-", "*", "/", "%", "=", "==", "!=", ">", "<", ">=", "<=", "&&", "||",
                "!", "&", "|", "^", "~", "<<", ">>", "++", "--", "+=", "-=", "*=", "/=", "%=" };

        operators = new HashSet<>(Arrays.asList(operatorArray));
    }

    public void analyzeFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\s+|(?=\\W)|(?<=\\W)");
                for (String token : tokens) {
                    if (!token.isEmpty()) {
                        if (keywords.contains(token)) {
                            System.out.println("Keyword: " + token);
                        } else if (operators.contains(token)) {
                            System.out.println("Operator: " + token);
                        } else if (token.matches("[a-zA-Z][a-zA-Z0-9_]*")) {
                            System.out.println("Variable: " + token);
                        } else if (token.matches("-?\\d+(\\.\\d+)?") || token.matches("'.'")
                                || token.matches("'\\\\.'")) {
                            System.out.println("Constants: " + token);
                        }

                        else {
                            System.out.println("Special Character: " + token);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        analyzer.analyzeFile("sample.c"); // Change the filename to your C file
    }
}