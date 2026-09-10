import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuestionBankQuiz {

    // Default number of questions asked when no argument is supplied.
    private static final int DEFAULT_QUESTION_LIMIT = 5;

    static class QuestionBank {
        int version;
        String title;
        List<Question> questions = new ArrayList<Question>();
    }

    static class Question {
        int id;
        String subject;
        String question;
        String[] options;
        String correctAnswer;
        String explanation;
    }

    public static void main(String[] args) throws Exception {

        String jsonFile = "data/questions.json";

        int questionLimit = parseQuestionLimit(args);

        QuestionBank questionBank;

        try {
            questionBank = readQuestionBank(jsonFile);
        } catch (Exception e) {
            System.out.println("Could not read " + jsonFile);
            System.out.println("Please make sure questions.json is in the BlueJ project folder.");
            System.out.println("Error: " + e.getMessage());
            return;
        }

        if (questionBank == null
                || questionBank.questions == null
                || questionBank.questions.isEmpty()) {
            System.out.println("No questions were found in " + jsonFile);
            return;
        }

        // Shuffle the question order and then take only as many questions
        // as requested (or the whole bank, if it is smaller than the limit).
        List<Question> allQuestions = new ArrayList<Question>(questionBank.questions);
        Collections.shuffle(allQuestions);

        int totalQuestions = Math.min(questionLimit, allQuestions.size());
        List<Question> quizQuestions = allQuestions.subList(0, totalQuestions);

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        String[] userAnswers = new String[totalQuestions];

        System.out.println("=================================");
        System.out.println("          QUESTION BANK QUIZ");
        System.out.println("=================================");
        System.out.println("Type the NUMBER next to the option you want,");
        System.out.println("not the answer text itself.");

        for (int i = 0; i < totalQuestions; i++) {

            Question currentQuestion = quizQuestions.get(i);

            List<String> shuffledOptions = new ArrayList<String>();
            for (int j = 0; j < currentQuestion.options.length; j++) {
                shuffledOptions.add(currentQuestion.options[j]);
            }

            Collections.shuffle(shuffledOptions);

            System.out.println();
            System.out.println("Completed: " + i + " | Pending: "
                    + (totalQuestions - i));
            System.out.println("Question " + (i + 1) + " / "
                    + totalQuestions);
            System.out.println(currentQuestion.subject);
            System.out.println(currentQuestion.question);
            System.out.println();

            for (int j = 0; j < shuffledOptions.size(); j++) {
                System.out.println("[" + (j + 1) + "] " + shuffledOptions.get(j));
            }

            int choice = readChoice(scanner, shuffledOptions.size());
            String selectedAnswer = shuffledOptions.get(choice - 1);
            userAnswers[i] = selectedAnswer;

            if (selectedAnswer.equals(currentQuestion.correctAnswer)) {
                score++;
                System.out.println("Feedback: Correct answer!");
            } else {
                System.out.println("Feedback: Answer recorded.");
            }
        }

        System.out.println();
        System.out.println("=================================");
        System.out.println("             FINAL RESULT");
        System.out.println("=================================");
        System.out.println("Score: " + score + " / " + totalQuestions);

        for (int i = 0; i < totalQuestions; i++) {
            Question currentQuestion = quizQuestions.get(i);

            System.out.println();
            System.out.println("Question " + (i + 1) + ": "
                    + currentQuestion.question);
            System.out.println("Your answer: " + userAnswers[i]);
            System.out.println("Correct answer: " + currentQuestion.correctAnswer);

            if (userAnswers[i].equals(currentQuestion.correctAnswer)) {
                System.out.println("Result: Correct");
            } else {
                System.out.println("Result: Wrong");
            }

            if (currentQuestion.explanation != null
                    && !currentQuestion.explanation.isEmpty()) {
                System.out.println("Explanation: "
                        + currentQuestion.explanation);
            }
        }

        scanner.close();
    }

    /*
     * Reads an optional command-line argument that limits how many
     * questions are asked. If no argument is supplied, or the argument
     * is not a valid positive number, the default limit is used.
     *
     * In BlueJ, right-click the class and choose "void main(String[] args)"
     * to be prompted for the argument, e.g. enter: {"10"}
     * to ask 10 questions. Leave the field as {} to use the default of 5.
     */
    private static int parseQuestionLimit(String[] args) {

        if (args == null || args.length == 0) {
            return DEFAULT_QUESTION_LIMIT;
        }

        try {
            int limit = Integer.parseInt(args[0].trim());

            if (limit <= 0) {
                System.out.println("Number of questions must be greater than 0. "
                        + "Using default: " + DEFAULT_QUESTION_LIMIT);
                return DEFAULT_QUESTION_LIMIT;
            }

            return limit;

        } catch (NumberFormatException e) {
            System.out.println("Could not understand \"" + args[0]
                    + "\" as a number of questions. Using default: "
                    + DEFAULT_QUESTION_LIMIT);
            return DEFAULT_QUESTION_LIMIT;
        }
    }

    private static int readChoice(Scanner scanner, int optionCount) {
        while (true) {
            System.out.print("Enter the option number (1-" + optionCount + "): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number, e.g. 1, 2, 3 or 4.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= optionCount) {
                return choice;
            }

            System.out.println("Please enter a number from 1 to "
                    + optionCount + ".");
        }
    }

    /*
     * Reads the question-bank JSON without Gson or any external library.
     * This parser is intentionally limited to the question-bank structure
     * used by this project.
     */
    private static QuestionBank readQuestionBank(String fileName)
            throws Exception {

        StringBuilder json = new StringBuilder();

        BufferedReader reader = new BufferedReader(
                new FileReader(fileName));

        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
            json.append('\n');
        }

        reader.close();

        SimpleJsonParser parser = new SimpleJsonParser(json.toString());
        Object root = parser.parse();

        if (!(root instanceof java.util.Map)) {
            throw new Exception("JSON root must be an object.");
        }

        java.util.Map rootObject = (java.util.Map) root;

        QuestionBank bank = new QuestionBank();

        Object version = rootObject.get("version");
        if (version instanceof Number) {
            bank.version = ((Number) version).intValue();
        }

        Object title = rootObject.get("title");
        if (title instanceof String) {
            bank.title = (String) title;
        }

        Object questionsObject = rootObject.get("questions");

        if (!(questionsObject instanceof List)) {
            throw new Exception("JSON does not contain a questions array.");
        }

        List questionList = (List) questionsObject;

        for (int i = 0; i < questionList.size(); i++) {

            Object questionObject = questionList.get(i);

            if (!(questionObject instanceof java.util.Map)) {
                throw new Exception("Invalid question at position "
                        + (i + 1));
            }

            java.util.Map q = (java.util.Map) questionObject;

            Question question = new Question();

            Object id = q.get("id");
            if (id instanceof Number) {
                question.id = ((Number) id).intValue();
            }

            question.subject = getString(q, "subject");
            question.question = getString(q, "question");
            question.correctAnswer = getString(q, "correctAnswer");
            question.explanation = getString(q, "explanation");

            Object optionsObject = q.get("options");

            if (!(optionsObject instanceof List)) {
                throw new Exception("Question " + question.id
                        + " does not contain an options array.");
            }

            List optionList = (List) optionsObject;

            if (optionList.size() == 0) {
                throw new Exception("Question " + question.id
                        + " has no answer options.");
            }

            question.options = new String[optionList.size()];

            for (int j = 0; j < optionList.size(); j++) {
                question.options[j] = String.valueOf(optionList.get(j));
            }

            bank.questions.add(question);
        }

        return bank;
    }

    private static String getString(java.util.Map object, String key) {
        Object value = object.get(key);

        if (value == null) {
            return "";
        }

        return String.valueOf(value);
    }

    /*
     * Small JSON parser for this project's JSON format.
     * It supports objects, arrays, strings, numbers, true, false and null.
     */
    static class SimpleJsonParser {

        private String text;
        private int position;

        SimpleJsonParser(String text) {
            this.text = text;
            this.position = 0;
        }

        Object parse() throws Exception {
            skipWhitespace();

            Object value = parseValue();

            skipWhitespace();

            if (position != text.length()) {
                throw new Exception("Unexpected characters after JSON data.");
            }

            return value;
        }

        private Object parseValue() throws Exception {
            skipWhitespace();

            if (position >= text.length()) {
                throw new Exception("Unexpected end of JSON.");
            }

            char c = text.charAt(position);

            if (c == '{') {
                return parseObject();
            }

            if (c == '[') {
                return parseArray();
            }

            if (c == '"') {
                return parseString();
            }

            if (c == '-' || Character.isDigit(c)) {
                return parseNumber();
            }

            if (text.startsWith("true", position)) {
                position += 4;
                return Boolean.TRUE;
            }

            if (text.startsWith("false", position)) {
                position += 5;
                return Boolean.FALSE;
            }

            if (text.startsWith("null", position)) {
                position += 4;
                return null;
            }

            throw new Exception("Unexpected character: " + c);
        }

        private java.util.Map parseObject() throws Exception {

            java.util.Map object = new java.util.LinkedHashMap();

            expect('{');
            skipWhitespace();

            if (peek('}')) {
                position++;
                return object;
            }

            while (true) {

                skipWhitespace();

                if (text.charAt(position) != '"') {
                    throw new Exception("Expected a JSON object key.");
                }

                String key = parseString();

                skipWhitespace();
                expect(':');

                Object value = parseValue();
                object.put(key, value);

                skipWhitespace();

                if (peek('}')) {
                    position++;
                    break;
                }

                expect(',');
            }

            return object;
        }

        private List parseArray() throws Exception {

            List array = new ArrayList();

            expect('[');
            skipWhitespace();

            if (peek(']')) {
                position++;
                return array;
            }

            while (true) {

                Object value = parseValue();
                array.add(value);

                skipWhitespace();

                if (peek(']')) {
                    position++;
                    break;
                }

                expect(',');
            }

            return array;
        }

        private String parseString() throws Exception {

            expect('"');

            StringBuilder result = new StringBuilder();

            while (position < text.length()) {

                char c = text.charAt(position++);

                if (c == '"') {
                    return result.toString();
                }

                if (c == '\\') {

                    if (position >= text.length()) {
                        throw new Exception("Invalid escape sequence.");
                    }

                    char escaped = text.charAt(position++);

                    switch (escaped) {
                        case '"':
                            result.append('"');
                            break;
                        case '\\':
                            result.append('\\');
                            break;
                        case '/':
                            result.append('/');
                            break;
                        case 'b':
                            result.append('\b');
                            break;
                        case 'f':
                            result.append('\f');
                            break;
                        case 'n':
                            result.append('\n');
                            break;
                        case 'r':
                            result.append('\r');
                            break;
                        case 't':
                            result.append('\t');
                            break;
                        case 'u':
                            result.append(parseUnicodeEscape());
                            break;
                        default:
                            throw new Exception(
                                    "Invalid escape sequence: \\" + escaped);
                    }

                } else {
                    result.append(c);
                }
            }

            throw new Exception("Unterminated JSON string.");
        }

        private char parseUnicodeEscape() throws Exception {

            if (position + 4 > text.length()) {
                throw new Exception("Invalid Unicode escape.");
            }

            String hex = text.substring(position, position + 4);

            for (int i = 0; i < hex.length(); i++) {
                if (Character.digit(hex.charAt(i), 16) == -1) {
                    throw new Exception("Invalid Unicode escape: " + hex);
                }
            }

            position += 4;

            return (char) Integer.parseInt(hex, 16);
        }

        private Number parseNumber() throws Exception {

            int start = position;

            if (peek('-')) {
                position++;
            }

            while (position < text.length()
                    && Character.isDigit(text.charAt(position))) {
                position++;
            }

            if (peek('.')) {
                position++;

                while (position < text.length()
                        && Character.isDigit(text.charAt(position))) {
                    position++;
                }
            }

            if (peek('e') || peek('E')) {

                position++;

                if (peek('+') || peek('-')) {
                    position++;
                }

                while (position < text.length()
                        && Character.isDigit(text.charAt(position))) {
                    position++;
                }
            }

            String number = text.substring(start, position);

            try {
                if (number.indexOf('.') >= 0
                        || number.indexOf('e') >= 0
                        || number.indexOf('E') >= 0) {
                    return Double.valueOf(number);
                }

                return Integer.valueOf(number);

            } catch (NumberFormatException e) {
                throw new Exception("Invalid number: " + number);
            }
        }

        private void expect(char expected) throws Exception {

            skipWhitespace();

            if (position >= text.length()
                    || text.charAt(position) != expected) {
                throw new Exception("Expected '" + expected + "'.");
            }

            position++;
        }

        private boolean peek(char c) {
            return position < text.length()
                    && text.charAt(position) == c;
        }

        private void skipWhitespace() {

            while (position < text.length()
                    && Character.isWhitespace(text.charAt(position))) {
                position++;
            }
        }
    }
}
