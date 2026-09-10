# ICSE Class 10 Question Bank Quiz

A simple Java objective-question quiz for **ICSE Class 10 Computer Applications**.

The quiz reads a question bank from a JSON file and presents the questions one at a time. Students answer each question, receive feedback, and see their final score and answer review at the end.

The question bank currently contains 30 original practice questions.

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
- Displays questions one at a time.
- Displays four answer options.
- Randomly shuffles the answer options.
- Accepts the student's answer.
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

The quiz will start in the BlueJ terminal.

---

# Playing the Quiz

The quiz displays the current progress.

For example:

```text
=================================
          QUESTION BANK QUIZ
=================================

Completed: 0 | Pending: 30
Question 1 / 30

What is the output of: "ICSE".length();

1. A) 3
2. B) 4
3. C) 5
4. D) Error

Enter your answer number:
```

Enter:

```text
2
```

to select the second option.

The program accepts numbers from:

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
1. A) 5
2. B) Error
3. C) 4
4. D) 3
```

on one run and:

```text
1. A) 4
2. B) 3
3. C) Error
4. D) 5
```

on another run.

The program checks the **answer text**, rather than assuming that a particular letter or number is always correct.

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

# Updating the Question Bank

When the question bank needs to be changed:

1. Open the Excel question bank.
2. Add, remove, or modify questions.
3. Run the Excel-to-JSON converter.
4. Generate the updated `questions.json`.
5. Copy the new `questions.json` into the BlueJ project.
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

- 30 practice questions
- Strings questions
- Arrays questions
- User-defined methods questions
- One question at a time
- Question progress
- Completed and pending question count
- Four multiple-choice answers
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