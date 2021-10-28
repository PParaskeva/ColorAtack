package com.exampe.coloratack.android.gameFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exampe.coloratack.android.R
import com.exampe.coloratack.android.gameFragment.ui.GameFragmentLayout
import com.exampe.coloratack.enums.Color
import com.exampe.coloratack.mvp.contracts.GameContract
import com.exampe.coloratack.mvp.presenters.GamePresenter
import com.exampe.coloratack.pokos.Row
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GameFragment : Fragment(), GameContract.View {

    private var mGameFragmentLayout: GameFragmentLayout? = null
    private var mPresenter: GameContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_game, container, false)
        mGameFragmentLayout = v.findViewById(R.id.gameFragmentLayout)


        setUpGameFragmentLayout()
        mPresenter = GamePresenter(this)
        return v
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.start()
        mPresenter?.startGameLoop()
    }

    override fun onPause() {
        super.onPause()
        mPresenter?.stopGameLoop()
    }

    private fun setUpGameFragmentLayout() {
        mGameFragmentLayout?.apply {
            onCellClickListener = { position, row ->
                mPresenter?.onCellClick(position, row)
            }
        }
    }

    override fun setBoard(rows: List<Row>) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                mGameFragmentLayout?.rows = rows
            }
        }
    }

    override fun setScore(score: Int) {

    }

    override fun setSelectionColor(color: Color) {

    }


    override fun setPresenter(presenter: GameContract.Presenter) {
        mPresenter = presenter
    }

    companion object {
        @JvmStatic
        fun newInstance() = GameFragment()
    }
}