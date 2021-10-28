package com.exampe.coloratack.android.gameFragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exampe.coloratack.android.R
import com.exampe.coloratack.android.gameFragment.ui.BoardRow
import com.exampe.coloratack.pokos.Row

class BoardAdapter(
    var onCellClickListener: ((position: Int, row: Row) -> Unit)?
) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    var rows: List<Row> = listOf()
        set(value) {
            val results = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int = field.size
                override fun getNewListSize(): Int = value.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    field[oldItemPosition].id == value[newItemPosition].id

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean = field[oldItemPosition] == value[newItemPosition]
            })

            field = value

            results.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.board_itemview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val row = rows[position]
            boardRow?.row = row
        }
    }

    override fun getItemCount(): Int {
        return rows.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val boardRow: BoardRow? = itemView.findViewById(R.id.boardRow)

        init {
            boardRow?.onCellClickListener = onCellClickListener
        }
    }
}