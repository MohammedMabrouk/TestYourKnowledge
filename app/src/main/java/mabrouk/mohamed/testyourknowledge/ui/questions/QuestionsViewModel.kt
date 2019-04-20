package mabrouk.mohamed.testyourknowledge.ui.questions

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import mabrouk.mohamed.testyourknowledge.data.QuestionsRepository
import mabrouk.mohamed.testyourknowledge.data.api.QuestionRequest
import mabrouk.mohamed.testyourknowledge.data.api.QuestionsService
import mabrouk.mohamed.testyourknowledge.data.api.RetrofitClientInstance

class QuestionsViewModel : ViewModel() {

    val TAG: String = QuestionsViewModel::class.java.simpleName + "TAG"

    private val service: QuestionsService =
        RetrofitClientInstance.getRetrofitInstance()!!.create(QuestionsService::class.java)

    private val questionsRepo: QuestionsRepository
//    var numberOfQuestions : MutableLiveData<Int> = MutableLiveData()

    //TODO: get current question number

    init {
        questionsRepo = QuestionsRepository()
    }

    fun getQuestions(questionsRequest: QuestionRequest): MutableLiveData<Int> {
        // call repository
        return questionsRepo.getQuestions(questionsRequest)
    }
}
