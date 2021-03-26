package com.project.whattowatch.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.whattowatch.R

class BottomSheetDialog : BottomSheetDialogFragment() {
    var dialogClickListener: ItemClickListener? = null

    companion object {
        fun newInstance(): BottomSheetDialog {
            val bundle = Bundle()
            val fragment = BottomSheetDialog()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setclickListener(view)
    }

    private fun setclickListener(view: View) {
        view.findViewById<View>(R.id.btn_popular).setOnClickListener {
            onClickAction(resources.getString(R.string.popular))
        }
        view.findViewById<View>(R.id.btn_upcoming).setOnClickListener {
            onClickAction(resources.getString(R.string.upcoming))
        }
        view.findViewById<View>(R.id.btn_top_rated).setOnClickListener {
            onClickAction(resources.getString(R.string.top_rated))
        }
        view.findViewById<View>(R.id.btn_now_playing).setOnClickListener {
            onClickAction(resources.getString(R.string.now_playing))
        }
    }

    private fun onClickAction(category: String) {
        dismiss()
        dialogClickListener?.onItemClickListener(category)
    }

    interface ItemClickListener {
        fun onItemClickListener(category: String)
    }
}