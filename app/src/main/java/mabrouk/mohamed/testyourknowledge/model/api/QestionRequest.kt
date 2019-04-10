package mabrouk.mohamed.testyourknowledge.model.api

data class QuestionRequest(
    val numOfQuestions : Int,
    val category : String,
    val difficulty : String,
    val questionsType : String
)