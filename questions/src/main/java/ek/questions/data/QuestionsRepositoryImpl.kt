package ek.questions.data

import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
) : QuestionsRepository {
    override fun requestQuestions(): String = "KEK"
}