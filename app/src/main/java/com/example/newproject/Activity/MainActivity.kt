package com.example.newproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.Adapter.CustomAdapter
import com.example.newproject.CustomDialog
import com.example.newproject.Interface.InterfaceClickCallBack
import com.example.newproject.Item
import com.example.newproject.R
import com.example.newproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),InterfaceClickCallBack,View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {

        binding.recyler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = CustomAdapter(baseContext, arrayListOf(), this)
        binding.recyler.adapter = adapter

        binding.add.setOnClickListener(this)
        binding.delete.setOnClickListener(this)
    }

    override fun onClickCallBack(item: Any?, position: Int, from: String?) {
        if (from.equals("clicked")) {
            val selectedItem = item as Item

            val dailog = CustomDialog(this@MainActivity)
            dailog.show()
            dailog.setCancelable(false)
//            val window= dailog.window
//            window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
            val cancel: Button = dailog.findViewById(R.id.Cancel)
            val modify: Button = dailog.findViewById(R.id.modify)
            val etName: EditText = dailog.findViewById(R.id.etName)
            val etAge: EditText = dailog.findViewById(R.id.etAge)
            etName.setText(selectedItem.name)
            etAge.setText(selectedItem.age)

            modify.setOnClickListener {
                val addItem =
                    Item(etName.text.toString().trim(), etAge.text.toString().trim(), false)
                adapter.updateItem(position, addItem)
                dailog.dismiss()
            }

            cancel.setOnClickListener {
                dailog.dismiss()
            }


        }

    }


    override fun onClick(v: View?) {
        if (v == binding.add) {
            val editname = binding.name.text.toString()
            val editage = binding.age.text.toString().trim()

            if (editname.isNotEmpty() && editage.isNotEmpty()) {
                val addItem = Item(editname, editage, false)
                adapter.addItem(addItem)
                binding.name.setText("")
                binding.age.setText("")
            }

        }
        if (v == binding.delete) {
            adapter.deleteSelectedItems()
        }

    }

    }