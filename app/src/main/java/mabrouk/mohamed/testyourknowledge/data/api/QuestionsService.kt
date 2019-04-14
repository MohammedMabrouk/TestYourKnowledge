package mabrouk.mohamed.testyourknowledge.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsService {
    /*
    * API query string
    * (1) amount : number of questions
    * (2) category : category id
    * (3) difficulty
    * (4) type : questions type multiple / boolean
    * */

    @GET(".")
    fun getQuestions(@Query("amount") numberOfQuestions: Int,
                     @Query("category") categoryId : String,
                     @Query("difficulty") difficulty : String,
                     @Query("type") type : String): Call<QuestionResponse>
}