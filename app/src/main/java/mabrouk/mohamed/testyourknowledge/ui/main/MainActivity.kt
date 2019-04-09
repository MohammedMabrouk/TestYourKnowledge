package mabrouk.mohamed.testyourknowledge.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mabrouk.mohamed.testyourknowledge.model.api.QuestionResponse
import mabrouk.mohamed.testyourknowledge.model.api.QuestionsService
import mabrouk.mohamed.testyourknowledge.model.api.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ArrayAdapter
import android.widget.Spinner
import mabrouk.mohamed.testyourknowledge.R


class MainActivity : AppCompatActivity() {

    val TAG : String = MainActivity::class.java.simpleName + "TAG"
    lateinit var categorySpinner : Spinner
    lateinit var difficultySpinner : Spinner
    lateinit var questionTypeSpinner : Spinner
    lateinit var numberOfQuestionsSpinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mabrouk.mohamed.testyourknowledge.R.layout.activity_main)


        // init
        categorySpinner = findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_spinner_category)
        difficultySpinner = findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_spinner_difficulty)
        questionTypeSpinner = findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_spinner_questions_type)
        numberOfQuestionsSpinner = findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_spinner_num_question)



        ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            categorySpinner.adapter = adapter
        }


        ArrayAdapter.createFromResource(
            this,
            R.array.difficulty_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            difficultySpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.question_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            questionTypeSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.number_of_questions_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            numberOfQuestionsSpinner.adapter = adapter
        }



























        val service: QuestionsService =
            RetrofitClientInstance.getRetrofitInstance()!!.create(QuestionsService::class.java)
        var call: Call<QuestionResponse> = service.getQuestions(
            20,
            23,
            "medium",
            "multiple"
        )


        call.enqueue(object : Callback<QuestionResponse> {
            override fun onResponse(call: Call<QuestionResponse>, response: Response<QuestionResponse>) {

                if (response.isSuccessful) {
                    Log.d(TAG, "response success")
                    Log.d(TAG, "number of records: " + response.body()!!.results.size)

                    if(response.body()!!.response_code == 0){ // questions found

                    }else{                                    // no questions

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
    }

}
