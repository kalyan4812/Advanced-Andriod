package com.example.advancedandriod_1.QUIZ_APP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.example.advancedandriod_1.QUIZ_APP.QuizContract.*;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="MYQUIZ";
    private static final int DATABASE_VERSION=1;
    private SQLiteDatabase db;
    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        final String SQL_CRREATE_QUESTION_TABLE="CREATE TABLE "+ QuestionsTable.TABLE_NAME+" ( "+QuestionsTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuestionsTable.COLUMN_QUESTION+" TEXT, "+
                QuestionsTable.COLUMN_OPTION1+" TEXT, "+
                QuestionsTable.COLUMN_OPTION2+" TEXT, "+
                QuestionsTable.COLUMN_OPTION3+" TEXT, "+
                QuestionsTable.COLUMN_ANSWER_NR+" INTEGER"+")";
        db.execSQL(SQL_CRREATE_QUESTION_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        QuizQuestion q1=new QuizQuestion("AMMM","A","B","C",1);
        addQuestion(q1);
        QuizQuestion q2=new QuizQuestion(" b is correct","A","B","C",2);
        addQuestion(q2);

    }
    private void addQuestion(QuizQuestion question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS "+QuestionsTable.TABLE_NAME);
        onCreate(db);

    }
    public ArrayList<QuizQuestion> getAllQuestions() {
        ArrayList<QuizQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                QuizQuestion question = new QuizQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
