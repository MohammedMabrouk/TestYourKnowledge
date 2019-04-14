package mabrouk.mohamed.testyourknowledge.data.api

data class QuestionRequest(
    val numOfQuestions : Int,
    val category : String,
    val difficulty : String,
    val questionsType : String
)