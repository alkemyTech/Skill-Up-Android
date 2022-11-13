import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.datasource.RvData
import com.Alkemy.alkemybankbase.ui.adapters.HomeRVAdapter

class HomeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_home_activity)
        setUpRecyclerView()
    }

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: HomeRVAdapter = HomeRVAdapter()

    fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rvh) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.HomeRVAdapter(getMoves(), this)
        mRecyclerView.adapter = mAdapter
    }

    fun getMoves(): MutableList<RvData> {
        var moves: MutableList<RvData> = ArrayList()
        moves.add(RvData("Addison Spence", "BCA - 34298493583", "+ $154.50"))
        moves.add(RvData("Addison Spence", "BCA - 34298493583", "+ $154.50"))
        moves.add(RvData("Addison Spence", "BCA - 34298493583", "+ $154.50"))
        moves.add(RvData("Addison Spence", "BCA - 34298493583", "+ $154.50"))
        moves.add(RvData("Addison Spence", "BCA - 34298493583", "+ $154.50"))
        return moves
    }
}