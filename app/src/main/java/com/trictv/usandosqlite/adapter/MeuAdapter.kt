package com.trictv.usandosqlite.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.trictv.usandosqlite.R
import com.trictv.usandosqlite.database.DatabaseHandler
import com.trictv.usandosqlite.entity.Cadastro

class MeuAdapter(val context: Context, val cursor : Cursor) : BaseAdapter()
{

    override fun getCount(): Int {
        return cursor.count
    }

    override fun getItem(indice: Int): Any? {
        cursor.moveToPosition(indice)

        val cadastro: Cadastro = Cadastro(
            cursor.getInt(DatabaseHandler.COLUMN_ID.toInt()),
            cursor.getString(DatabaseHandler.COLUMN_NOME.toInt()),
            cursor.getString(DatabaseHandler.COLUMN_TELEFONE.toInt())
        )

        return  cadastro
    }

    override fun getItemId(indice: Int): Long {
        cursor.moveToPosition(indice)
        return cursor.getLong(DatabaseHandler.COLUMN_ID.toInt())
    }

    @SuppressLint("ServiceCast")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.elemento_lista, null)



        val tvNomeElementoLista = v.findViewById<TextView>(R.id.tvNomeElementoLista)
        val tvTelefone = v.findViewById<TextView>(R.id.tvTelefoneElementoLista)

        cursor.moveToPosition(position)
        tvNomeElementoLista.text = cursor.getString(DatabaseHandler.COLUMN_NOME.toInt())
        tvTelefone.text = cursor.getString(DatabaseHandler.COLUMN_TELEFONE.toInt())

        return v
    }

}