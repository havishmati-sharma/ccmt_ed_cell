# ICSE Class 10 Question Bank Quiz

A simple Java objective-question quiz for **ICSE Class 10 Computer Applications**.

The quiz reads a question bank from a JSON file and presents the questions one at a time. Students answer each question, receive feedback, and see their final score and answer review at the end.

The question bank currently contains 30 original practice questions. By default, each quiz run asks **5** random questions from the bank, but you can change this with a command-line argument (see [Choosing How Many Questions to Ask](#choosing-how-many-questions-to-ask)).

> **Note:** These are original practice questions and are not official CISCE board questions.

---

## Topics Included

The current question bank contains:

- **10 questions on Strings**
- **10 questions on Arrays**
- **10 questions on User-defined methods**

Total:

**30 questions**

---

## Project Files

The BlueJ version requires only two files:

```text
QuestionBankQuiz/
│
├── QuestionBankQuiz.java
└── data/questions.json
```

### QuestionBankQuiz.java

The Java program that:

- Reads the question bank.
- Randomly selects a set number of questions to ask (default 5).
- Displays questions one at a time.
- Displays four answer options, numbered `1`-`4`.
- Randomly shuffles the answer options.
- Accepts the student's answer as an option number.
- Checks the answer.
- Calculates the score.
- Displays the final result.
- Shows the student's answer and correct answer.
- Displays an explanation when available.

### data/questions.json

Contains the question bank used by the Java program.

The JSON file contains the questions, answer options, correct answers, and explanations.

---

# System Requirements

You need:

- **BlueJ**
- Java/JDK supported by your BlueJ installation
- `QuestionBankQuiz.java`
- `data/questions.json`


---

# Running the Quiz in BlueJ

## Step 1 — Create a BlueJ Project

Open BlueJ.

Select:

```text
Project → New Project
```

Give the project a name such as:

```text
QuestionBankQuiz
```

---

## Step 2 — Add the Java File

Place:

```text
QuestionBankQuiz.java
```

inside the BlueJ project folder.

Alternatively, create a new Java class in BlueJ named:

```text
QuestionBankQuiz
```

and replace its contents with the supplied `QuestionBankQuiz.java` code.

---

## Step 3 — Add questions.json

Copy:

```text
questions.json
```

into the **data folder as the BlueJ project**.

The project should look like:

```text
QuestionBankQuiz/
│
├── QuestionBankQuiz.java
├── package.bluej
└── data/questions.json
```

BlueJ creates `package.bluej` automatically.

---

## Step 4 — Compile

Open the project in BlueJ.

Click:

```text
Compile
```

The class should compile without requiring any external library.

If there are no errors, the `QuestionBankQuiz` class will appear in the BlueJ project window.

---

## Step 5 — Run the Quiz

Right-click the:

```text
QuestionBankQuiz
```

class.

Select the `main` method and run it.

BlueJ will show a dialog box asking for the `args` parameter (a `String[]`). You can:

- Leave it as `{}` (empty) to ask the **default of 5 questions**, or
- Enter a value such as `{"10"}` to ask **10 questions** instead.

Click **OK**, and the quiz will start in the BlueJ terminal.

---

## Choosing How Many Questions to Ask

The quiz does not always ask all 30 questions in the bank. Instead, it randomly picks a set number of questions each time it runs, so no two attempts are necessarily the same.

By default, this number is:

```text
5
```

To ask a different number of questions, pass it as the first command-line argument:

- In **BlueJ**, when the `main` method parameter dialog appears, enter it as a `String[]`, for example `{"15"}`.
- From a **terminal**, run:

```text
java QuestionBankQuiz 15
```

This example asks 15 questions instead of the default 5.

If the argument is missing, not a number, or zero or negative, the quiz falls back to the default of 5 questions and prints a short message explaining why.

If the requested number is larger than the number of questions in the bank, the quiz simply asks every question in the bank.

---

# Playing the Quiz

The quiz displays the current progress.

For example (with the default of 5 questions):

```text
=================================
          QUESTION BANK QUIZ
=================================
Type the NUMBER next to the option you want,
not the answer text itself.

Completed: 0 | Pending: 5
Question 1 / 5

What is the output of: "ICSE".length();

[1] 3
[2] 4
[3] 5
[4] Error

Enter the option number (1-4):
```

Enter:

```text
2
```

to select the second option, `4`.

Each option is shown with a single number in square brackets, `[1]` to `[4]`. There are no letters (`A`, `B`, `C`, `D`) and no second numbering scheme to keep track of — you always type the number next to the option you want, never the answer value itself. The program accepts numbers from:

```text
1
2
3
4
```

---

# Answer Options

The answer options are randomly shuffled for each question.

For example, the same question may appear as:

```text
[1] 5
[2] Error
[3] 4
[4] 3
```

on one run and:

```text
[1] 4
[2] 3
[3] Error
[4] 5
```

on another run.

The program checks the **answer text**, rather than assuming that a particular number always corresponds to the correct answer.

---

# Final Result

After all questions have been answered, the program displays the final score.

For example:

```text
=================================
             FINAL RESULT
=================================

Score: 24 / 30
```

The program then displays each question along with:

- Your answer
- Correct answer
- Whether your answer was correct or wrong
- Explanation, when available

Example:

```text
Question 1: What is the output of: "ICSE".length();

Your answer: 4
Correct answer: 4
Result: Correct
Explanation: The string ICSE contains four characters.
```

---

# JSON Question Bank Format

The question bank uses a JSON structure similar to:

```json
{
  "version": 1,
  "title": "ICSE Class 10 Computer Applications - Strings, Arrays and User-defined Methods",
  "questions": [
    {
      "id": 1,
      "subject": "Strings",
      "question": "What is the output of: \"ICSE\".length();",
      "options": [
        "3",
        "4",
        "5",
        "Error"
      ],
      "correctAnswer": "4",
      "explanation": "The string ICSE contains four characters."
    }
  ]
}
```

The `correctAnswer` must exactly match one of the values in the `options` array.

For example:

```json
"options": [
  "3",
  "4",
  "5",
  "Error"
],
"correctAnswer": "4"
```

is valid.

---

# Creating or Changing Questions

The easiest way to create and maintain the question bank is to use the Excel question-bank file on a computer where Python is available.

The original workflow can be:

```text
questions.xlsx
       │
       ▼
excel_to_json.py
       │
       ▼
questions.json
       │
       ▼
BlueJ
       │
       ▼
QuestionBankQuiz.java
```

The Excel file and Python converter are **not required by students when taking the quiz**.

They are only needed when the question bank needs to be created or changed.

---

# Excel Question Bank Format

If the question bank is maintained in Excel, use these column headings:

```text
id
subject
question
option_a
option_b
option_c
option_d
correct_option
explanation
```

Example:

| id | subject | question | option_a | option_b | option_c | option_d | correct_option | explanation |
|---:|---|---|---|---|---|---|---|---|
| 1 | Strings | What is the length of ICSE? | 3 | 4 | 5 | Error | b | ICSE contains four characters. |

The `correct_option` value must be:

```text
a
b
c
d
```

The explanation is optional.

---

# Running the Excel-to-JSON Converter

`excel_to_json.py` reads your Excel question bank and writes a `questions.json` file in the exact format `QuestionBankQuiz.java` expects.

## Requirements

- Python 3 installed on your computer.
- The `openpyxl` package (a one-time install).

Check Python is installed:

```bash
python3 --version
```

(On Windows, this may just be `python --version` instead.)

## Step 1 — Install the dependency (one-time)

```bash
python3 -m pip install openpyxl
```

## Step 2 — Place the files together

Put these files in the same folder:

```text
excel_to_json.py
questions.xlsx
```

A ready-to-use sample spreadsheet, `questions_sample.xlsx`, is included with this project — it has 9 example questions (3 per topic) in the correct format, so you can try the converter immediately or use it as a starting template. Rename it to `questions.xlsx`, or point `--excel` at it directly.

## Step 3 — Run the converter

Open a terminal in that folder and run:

```bash
python3 excel_to_json.py --excel questions.xlsx --json questions.json
```

This reads `questions.xlsx` (its active/first sheet) and writes `questions.json` in the same folder.

**If you don't pass `--excel` / `--json`,** the converter defaults to `questions.xlsx` and `questions.json` in the current folder, so you can also just run:

```bash
python3 excel_to_json.py
```

**Optional arguments:**

| Flag | Short form | Purpose | Default |
|---|---|---|---|
| `--excel` | `-e` | Input `.xlsx` file to read | `questions.xlsx` |
| `--json` | `-j` | Output JSON file to write | `questions.json` |
| `--sheet` | — | Worksheet name to read | the active/first sheet |
| `--title` | — | Quiz title stored in the JSON | a generic ICSE title |

Convert a specific worksheet, if your Excel file has more than one sheet:

```bash
python3 excel_to_json.py --excel questions.xlsx --json questions.json --sheet Sheet1
```

Set a custom quiz title (stored in the JSON, used to identify the question bank):

```bash
python3 excel_to_json.py --excel questions.xlsx --json questions.json --title "My Custom Quiz"
```

All flags can be combined, and used in any order.

## Step 4 — Check the output

On success, you'll see something like:

```text
Converted questions: 30
Created: questions.json
```

If a row is missing an option, uses an invalid `correct_option`, or has a duplicate `id`, the converter stops and prints exactly which row caused the problem, for example:

```text
Conversion failed: correct_option at Excel row 7 must be a, b, c, or d.
```

Fix the row in Excel and run the command again.

## Step 5 — Copy the JSON into the BlueJ project

Copy the generated `questions.json` into:

```text
data/questions.json
```

inside your BlueJ project folder, replacing the old file.

---

# Updating the Question Bank

When the question bank needs to be changed:

1. Open the Excel question bank.
2. Add, remove, or modify questions.
3. Run the Excel-to-JSON converter (`python3 excel_to_json.py --excel questions.xlsx --json questions.json`).
4. Check the terminal output for the generated `questions.json`.
5. Copy the new `questions.json` into `data/questions.json` in the BlueJ project.
6. Run the quiz again.

The Java program does not need to be changed when only the questions are changed.

---

# Important: Keep the JSON File With the Java Program

The quiz expects:

```text
questions.json
```

to be available to the program.

Therefore, keep the JSON file in the same project folder when using the BlueJ version.

If the JSON file is missing, the quiz cannot load the question bank.

---

# Features

The quiz supports:

- 30 practice questions in the bank
- A configurable number of questions per quiz run (default 5)
- Random selection of questions from the bank
- Strings questions
- Arrays questions
- User-defined methods questions
- One question at a time
- Question progress
- Completed and pending question count
- Four multiple-choice answers, numbered `1`-`4` for simple, unambiguous entry
- Random answer-option ordering
- Answer validation
- Score calculation
- Final answer review
- Correct answer display
- Explanations
- JSON-based question bank
- BlueJ execution
- No external Java libraries

---

# Why JSON Is Used

The questions are kept separately from the Java program.

This means the questions can be changed without rewriting the Java quiz program.

The Java program handles the quiz logic, while:

```text
questions.json
```

contains the actual question data.

This separation makes it easier to maintain the project.

---

# Security Note

The generated JSON contains the correct answers.

For example:

```json
"correctAnswer": "4"
```

Therefore, this project is suitable for:

- Practice
- Revision
- Classroom demonstrations
- School projects
- Local quizzes

It should **not** be used as a secure online examination system.

For a secure examination system, the correct answers should be stored on a server and should not be distributed to the student's computer.

---

# Troubleshooting

## `questions.json` cannot be found

Make sure:

```text
data/questions.json
```

is present in the BlueJ project folder.

The project should contain:

```text
QuestionBankQuiz.java
data/questions.json
```

---

## The quiz says that no questions were found

Check that:

1. `data/questions.json` exists.
2. The file is not empty.
3. The JSON structure has a `questions` array.
4. The questions contain the required fields.

The beginning of the JSON should look similar to:

```json
{
  "version": 1,
  "title": "ICSE Class 10 Computer Applications - Strings, Arrays and User-defined Methods",
  "questions": [
```

---

# Recommended Workflow

For creating and maintaining the question bank:

```text
1. Create questions in Excel
          ↓
2. Run excel_to_json.py
          ↓
3. Generate questions.json
          ↓
4. Copy questions.json to BlueJ project
          ↓
5. Open project in BlueJ
          ↓
6. Compile QuestionBankQuiz.java
          ↓
7. Run the quiz
```

For students taking the quiz at school:

```text
1. Open BlueJ
          ↓
2. Open the QuestionBankQuiz project
          ↓
3. Compile
          ↓
4. Run QuestionBankQuiz
          ↓
5. Answer the questions
          ↓
6. View final score
```

---

# Project Summary

This project demonstrates how Java can be used to create a simple multiple-choice quiz while keeping the question data separate from the program logic.

The project uses:

```text
Java
+
JSON
+
BlueJ
```

The question bank covers:

```text
Strings
Arrays
User-defined methods
```

with a total of:

```text
30 practice questions
```

The questions are original practice questions and are not official CISCE board questions.