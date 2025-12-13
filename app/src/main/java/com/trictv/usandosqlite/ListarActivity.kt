package com.trictv.usandosqlite

import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.cursoradapter.widget.SimpleCursorAdapter
import com.trictv.usandosqlite.adapter.MeuAdapter
import com.trictv.usandosqlite.database.DatabaseHandler
import com.trictv.usandosqlite.databinding.ActivityListarBinding

class ListarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListarBinding
    private lateinit var banco: DatabaseHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityListarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banco = DatabaseHandler.getInstance(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lvRegistros)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initList()
    }

    private fun initList() {

//        val lista: List<String> = listOf<String>("Brasil","Argentina", "Paraguai", "Uruguai")
//        val adapter : ArrayAdapter<String> = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            lista)
//        binding.lvRegistros.adapter = adapter


//        val cursor: Cursor = banco.listar()
//        val adapter = SimpleCursorAdapter(
//            this,
//            android.R.layout.simple_list_item_2,
//            cursor,
//            arrayOf("nome","telefone"),
//            intArrayOf(android.R.id.text1, android.R.id.text2),
//            0
//        )
        val cursor: Cursor = banco.listar()

        val adapter = MeuAdapter(this, cursor)
        binding.lvRegistros.adapter = adapter


    }

}