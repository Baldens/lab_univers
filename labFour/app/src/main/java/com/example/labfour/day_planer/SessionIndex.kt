
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.util.HashMap

class SessionIndex(private val context: Context){
    var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var SHARED_PREF_NAME = "session"
    private var SESSION_INDEX = "index"

    private val firstName: String? = null

    @SuppressLint("CommitPrefEdits")
    fun SessionIndexs(context: Context) {
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    fun saveSession(userDetail: HashMap<String, String>) {
        SessionIndexs(context)
        val index = userDetail["index"]
        editor?.putString(SESSION_INDEX, index)
        editor?.apply()
    }

    fun getSession(): Int {
        return -1
    }

    fun removeSession() {
        editor?.putInt(SESSION_INDEX, -1)?.commit()
    }

    fun getIndex(): HashMap<String, String?> {
        SessionIndexs(context)

        val user = HashMap<String, String?>()
        user["index"] = sharedPreferences?.getString(SESSION_INDEX, null)
        return user
    }
}