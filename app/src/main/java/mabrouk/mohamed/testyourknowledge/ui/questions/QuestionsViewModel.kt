package mabrouk.mohamed.testyourknowledge.ui.questions

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import mabrouk.mohamed.testyourknowledge.data.QuestionsRepository
import mabrouk.mohamed.testyourknowledge.data.api.QuestionRequest
import mabrouk.mohamed.testyourknowledge.data.api.QuestionResponse
import mabrouk.mohamed.testyourknowledge.data.api.QuestionsService
import mabrouk.mohamed.testyourknowledge.data.api.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuestionsViewModel : ViewModel() {

    val TAG: String = QuestionsViewModel::class.java.simpleName + "TAG"


    private val service: QuestionsService =
        RetrofitClientInstance.getRetrofitInstance()!!.create(QuestionsService::class.java)

    private val questionsRepo: QuestionsRepository
    var numberOfQuestions : MutableLiveData<Int> = MutableLiveData()

    //TODO: get current question number


    init {
        numberOfQuestions.value = 0
        Log.v(TAG, "on Create() - x initial value: " + numberOfQuestions.value)
        questionsRepo = QuestionsRepository()

    }


    fun getQuestions(questionsRequest: QuestionRequest) {
        // call repository
        //currentQuestionNumber.value = (questionsRepo.getQuestions(questionsRequest).value)
        questionsRepo.getQuestions(questionsRequest)
    }



}
