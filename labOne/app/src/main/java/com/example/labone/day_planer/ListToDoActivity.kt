package com.example.labone.day_planer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.labone.MainActivity
import com.example.labone.R
import org.json.JSONObject
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labone.day_planer.db.DB
import com.example.labone.day_planer.db.PlannerAdapter
import org.json.JSONArray


class ListToDoActivity : AppCompatActivity() {
    private lateinit var sqliteHelper: DB
    private lateinit var recyclerView: RecyclerView
    private var adapter: PlannerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_to_do)

        var json: String? = GetData().readFileFromAssets(this, "list.json")
        var getJsonObject: JSONArray = JSONArray(json)

        val layout: LinearLayout = findViewById(R.id.layoutListToDo)

        sqliteHelper = DB(this)
        val stdList = sqliteHelper.getAllPlanner()
        adapter?.addItems(stdList)
        initRecyclerView()
        /*for (i in 1..stdList.size){
            val setTextView = TextView(this)
            val setTextViewDescription = TextView(this)
            setTextView.text = stdList
            setTextView.textSize = 17F
            setTextViewDescription.text = stdList[i].description
            setTextViewDescription.textSize = 13F
            val setId = stdList.first().id
            createNewLayoutWithToDo(layout,setTextView,setTextViewDescription,setId)
        }
*/
        layout.setOnClickListener (View.OnClickListener{ view ->
            for(j in 1..getJsonObject.length()){
                println(j)
                when(view?.id){
                    j -> {
                        Toast.makeText(this, j, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    @SuppressLint("ResourceType")
    private fun createNewLayoutWithToDo(
        layout: LinearLayout,
        setTextView: TextView,
        setTextViewDescription: TextView,
        id: Int
    ){
        val setLayout: LinearLayout = LinearLayout(this)
        setLayout.id = 1

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
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlannerAdapter()
        recyclerView.adapter = adapter
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