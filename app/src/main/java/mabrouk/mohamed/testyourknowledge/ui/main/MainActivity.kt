package mabrouk.mohamed.testyourknowledge.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import mabrouk.mohamed.testyourknowledge.data.api.QuestionRequest
import mabrouk.mohamed.testyourknowledge.ui.questions.QuestionsViewModel
import java.util.*


class MainActivity : AppCompatActivity() {

    // TODO: check observable value when receiving response

    val TAG: String = MainActivity::class.java.simpleName + "TAG"

    lateinit var categorySpinner: Spinner
    lateinit var difficultySpinner: Spinner
    lateinit var questionTypeSpinner: Spinner
    lateinit var numberOfQuestionsSpinner: Spinner
    lateinit var startBtn: Button

    lateinit var qViewModel: QuestionsViewModel
    lateinit var qRequest: QuestionRequest

    var categoryString = ""
    var difficultyString = ""
    var typeString = ""
    var numberOfQuestions = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mabrouk.mohamed.testyourknowledge.R.layout.activity_main)

        // init
        initUi()
        qViewModel = ViewModelProviders.of(this).get(QuestionsViewModel::class.java)


        qViewModel.numberOfQuestions
            .observe(this, Observer { value -> Log.d(TAG, "observer - x value: $value") })

        Log.v(TAG, "on Start() - x value: " + qViewModel.numberOfQuestions.value)




        startBtn.setOnClickListener {

            // get values from spinners
            categoryString = if (categorySpinner.selectedItemPosition > 0) {
                resources.getStringArray(mabrouk.mohamed.testyourknowledge.R.array.categories_array)[categorySpinner.selectedItemPosition]
            } else {
                ""
            }

            difficultyString = if (difficultySpinner.selectedItemPosition > 0) {
                resources.getStringArray(mabrouk.mohamed.testyourknowledge.R.array.difficulty_array)[difficultySpinner.selectedItemPosition]
            } else {
                ""
            }

            typeString = if (questionTypeSpinner.selectedItemPosition > 0) {
                resources.getStringArray(mabrouk.mohamed.testyourknowledge.R.array.question_type_array)[questionTypeSpinner.selectedItemPosition]
            } else {
                ""
            }

            numberOfQuestions =
                resources.getStringArray(mabrouk.mohamed.testyourknowledge.R.array.number_of_questions_array)[numberOfQuestionsSpinner.selectedItemPosition]

            // fill request data
            qRequest = QuestionRequest(
                numberOfQuestions.toInt(),
                categoryString,
                difficultyString,
                typeString
            )

            Log.d(
                TAG,
                "category: $categoryString , difficulty: $difficultyString , type: $typeString , number of questions: $numberOfQuestions"
            )

//            qViewModel.setCurrentQuestionNumber(qViewModel.getCurrentQuestionNumber().value!! + 1)
//            Log.v(TAG, "on click() - x value: " + qViewModel.getCurrentQuestionNumber().value)

            Log.v(TAG, "on click() - calling request")
            qViewModel.getQuestions(qRequest)
        }


    }

    private fun initUi() {
        categorySpinner = findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_spinner_category)
        difficultySpinner = findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_spinner_difficulty)
        questionTypeSpinner = findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_spinner_questions_type)
        numberOfQuestionsSpinner =
            findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_spinner_num_question)
        startBtn = findViewById(mabrouk.mohamed.testyourknowledge.R.id.activity_main_start_btn)


        ArrayAdapter.createFromResource(
            this,
            mabrouk.mohamed.testyourknowledge.R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            categorySpinner.adapter = adapter
        }


        ArrayAdapter.createFromResource(
            this,
            mabrouk.mohamed.testyourknowledge.R.array.difficulty_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            difficultySpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            mabrouk.mohamed.testyourknowledge.R.array.question_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            questionTypeSpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            mabrouk.mohamed.testyourknowledge.R.array.number_of_questions_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            numberOfQuestionsSpinner.adapter = adapter
        }

    }

}
