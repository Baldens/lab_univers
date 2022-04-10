package com.example.labfour.day_planer

import SessionIndex
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labfour.MainActivity
import com.example.labfour.R
import com.example.labfour.day_planer.db.DB
import com.example.labfour.day_planer.db.ModelPlanner
import com.example.labfour.day_planer.db.PlannerAdapter
import org.json.JSONArray
import java.util.HashMap

class ActivityListToDo : AppCompatActivity() {
    private lateinit var sqliteHelper: DB
    private lateinit var recyclerView: RecyclerView
    private var adapter: PlannerAdapter? = null
    private lateinit var listPlanner: List<ModelPlanner>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_to_do2)

        var json: String? = GetData().readFileFromAssets(this, "list.json")
        var getJsonObject: JSONArray = JSONArray(json)

        val layout: LinearLayout = findViewById(R.id.layoutListToDo)

        sqliteHelper = DB(this)
        val stdList = sqliteHelper.getAllPlanner()
        listPlanner = stdList
        adapter?.addItems(stdList)
        for (i in 0 until stdList.size){
            val setTextView = TextView(this)
            val setTextViewDescription = TextView(this)
            setTextView.text = stdList.get(i).name
            setTextView.textSize = 17F
            setTextViewDescription.text = stdList.get(i).description
            setTextViewDescription.textSize = 13F
            val setId = stdList.get(i).id
            createNewLayoutWithToDo(layout,setTextView,setTextViewDescription,setId)
        }

    }


    @SuppressLint("ResourceType")
    private fun createNewLayoutWithToDo(
        layout: LinearLayout,
        setTextView: TextView,
        setTextViewDescription: TextView,
        id: Int
    ){
        val setLayout: LinearLayout = LinearLayout(this)
        setLayout.id = id

        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        val layoutParamsTwo: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        setLayout.setOrientation(LinearLayout.VERTICAL)
        setLayout.background = ResourcesCompat.getDrawable(getResources(), R.drawable.border, null)
        layoutParams.topMargin = 20

        setTextView.layoutParams = layoutParams
        layoutParamsTwo.topMargin = 160
        setTextViewDescription.layoutParams = layoutParams
        setTextViewDescription.layoutParams = layoutParamsTwo

        setLayout.setPadding(130,30,10,70)
        setLayout.layoutParams = layoutParams

        setLayout.addView(setTextView)
        setLayout.addView(setTextViewDescription)
        layout.addView(setLayout)

        clickLayout(setLayout)
    }

    private fun clickLayout(setLayout: LinearLayout){
        setLayout.setOnClickListener (View.OnClickListener{ view ->
            for(j in 0 until listPlanner.size){
                when(view?.id){
                    listPlanner.get(j).id -> {
                        val userDetailSet: HashMap<String,String> = HashMap();
                        userDetailSet.put("index", listPlanner.get(j).id.toString());

                        val context: Context = this
                        val sessionManagement: SessionIndex = SessionIndex(context)
                        sessionManagement.saveSession(userDetailSet)

                        val intent = Intent(this, CompletedToDoActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_button, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonAddNewToDo -> {
                val intent = Intent(this, AddToDoActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}