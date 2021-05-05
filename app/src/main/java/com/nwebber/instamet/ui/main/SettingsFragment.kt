package com.nwebber.instamet.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nwebber.instamet.R

private const val TAG = "SettingsFragment"



class SettingsFragment : Fragment() {
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
    private val prefs: SharedPreferences by lazy{
        PreferenceManager.getDefaultSharedPreferences(this.activity)
    }

    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        recycler = view.findViewById(R.id.settings_recycler)
        recycler.layoutManager = LinearLayoutManager(context)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.optionVocab.observe(viewLifecycleOwner, {
            recycler.adapter = SettingsAdapter(it)
        })
    }

    private inner class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private val optionSwitch : Switch = itemView.findViewById(R.id.option_Switch)
        init{
            itemView.setOnClickListener(this)
        }

        fun bind(setting: Int){
            optionSwitch.text = getString(setting)
            optionSwitch.isChecked = prefs.getBoolean(optionSwitch.text as String, false)
            optionSwitch.setOnClickListener {
                Log.d(TAG, "Clicked!")
                if (optionSwitch.isChecked){
                    var optionsList = sharedViewModel.optionNameList
                    optionsList.forEach {
                        var optionString = getString(it)
                        if (optionString == optionSwitch.text){
                            with(prefs.edit()){
                                putBoolean(optionSwitch.text as String?, true)
                                apply()
                            }
                        }
                        else{
                            with(prefs.edit()){
                                Log.d(TAG, "setting a found option to false")
                                putBoolean(optionString, false)
                                apply()
                                refreshRecyler()
                            }
                        }
                    }
                }
                else{
                    with(prefs.edit()){
                        putBoolean(optionSwitch.text as String?, false)
                        apply()
                    }
                }
            }
        }

        override fun onClick(v: View?) {
            Log.d(TAG, "Clicked!")
            /*if (optionSwitch.isChecked){
               with(prefs.edit()){
                   putBoolean(optionSwitch.text as String?, true)
                   apply()
               }
            }
            else{
                with(prefs.edit()){
                    putBoolean(optionSwitch.text as String?, false)
                    apply()
                }
            }*/
        }
    }

    private inner class SettingsAdapter(private val list: List<Int>) : RecyclerView.Adapter<SettingsViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
            val view = layoutInflater.inflate(R.layout.recycler_item, parent, false)
            return SettingsViewHolder(view)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
            holder.bind(list[position])
        }

    }
    private fun refreshRecyler(){
        recycler.adapter = null;
        recycler.layoutManager = null;
        recycler.layoutManager = LinearLayoutManager(context);
        sharedViewModel.optionVocab.observe(viewLifecycleOwner, {
            recycler.adapter = SettingsAdapter(it)
        })

    }

}