package mabrouk.mohamed.testyourknowledge.data.api

data class QuestionResponse(
    val response_code: Int,
    val results: List<Result>
)