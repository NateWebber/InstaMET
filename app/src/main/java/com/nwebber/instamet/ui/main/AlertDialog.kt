package com.nwebber.instamet.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.nwebber.instamet.R

class AlertDialogFragment : DialogFragment() {
    private var listener: ProceedListener? = null
    interface ProceedListener {
       fun proceedSearch()
    }
     override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.bad_keyword)
                    .setPositiveButton(R.string.search,
                            DialogInterface.OnClickListener { dialog, id ->
                                listener?.proceedSearch()
                            })
                    .setNegativeButton(R.string.back,
                            DialogInterface.OnClickListener { dialog, id ->
                                //Do nothing
                            })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    companion object {
        fun newInstance(listener: ProceedListener) = AlertDialogFragment().apply {
            this.listener = listener
        }
    }
}