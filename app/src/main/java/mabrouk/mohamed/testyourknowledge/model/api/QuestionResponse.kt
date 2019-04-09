package mabrouk.mohamed.testyourknowledge.model.api

data class QuestionResponse(
    val response_code: Int,
    val results: List<Result>
)