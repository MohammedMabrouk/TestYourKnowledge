package mabrouk.mohamed.testyourknowledge.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import mabrouk.mohamed.testyourknowledge.data.api.QuestionRequest
import mabrouk.mohamed.testyourknowledge.data.api.QuestionResponse
import mabrouk.mohamed.testyourknowledge.data.api.QuestionsService
import mabrouk.mohamed.testyourknowledge.data.api.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionsRepository {

    val TAG: String = QuestionsRepository::class.java.simpleName + "TAG"


    fun getQuestions(questionsRequest: QuestionRequest): LiveData<Int> {

        val qcount: MutableLiveData<Int> = MutableLiveData<Int>()
        // get questions from server
        val service: QuestionsService =
            RetrofitClientInstance.getRetrofitInstance()!!.create(QuestionsService::class.java)

        val call: Call<QuestionResponse> = service.getQuestions(
            questionsRequest.numOfQuestions,
            questionsRequest.category,
            questionsRequest.difficulty,
            questionsRequest.questionsType
        )


        call.enqueue(object : Callback<QuestionResponse> {
            override fun onResponse(call: Call<QuestionResponse>, response: Response<QuestionResponse>) {

                if (response.isSuccessful) {
                    Log.d(TAG, "response success")
                    Log.d(TAG, "number of records: " + response.body()!!.results.size)

                    if (response.body()!!.response_code == 0) { // questions found
                        Log.d(TAG, "response received, setting size of: " + response.body()!!.results.size)
                        //qCount = 555
                        //currentQuestionNumber.postValue(response.body()!!.results.size)
                        //qCount.postValue(555)
                        //currentQuestionNumber.postValue(response.body()!!.results.size)
                        //numQuestions.postValue(response.body()!!.results.size)
                        qcount.postValue(response.body()!!.results.size)

                    } else {
                        // no questions
                        //currentQuestionNumber.postValue(response.body()!!.results.size)
                        //qCount = 0
                        //qCount.postValue(0)
                        //currentQuestionNumber.postValue(0)
                        //numQuestions.postValue(0)
                        qcount.postValue(0)
                    }

                } else {
                    Log.d(TAG, "Code: " + response.code() + " Message: " + response.errorBody())
                }
                Log.d(TAG, "Code: " + response.code())
                Log.d(TAG, "API Code: " + response.body()!!.response_code)
            }

            override fun onFailure(call: Call<QuestionResponse>, t: Throwable) {

            }
        })

        return qcount
    }


}