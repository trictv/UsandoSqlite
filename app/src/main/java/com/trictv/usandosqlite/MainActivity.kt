package com.trictv.usandosqlite

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.trictv.usandosqlite.database.DatabaseHandler
import com.trictv.usandosqlite.databinding.ActivityMainBinding
import com.trictv.usandosqlite.entity.Cadastro

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var banco: DatabaseHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banco = DatabaseHandler.getInstance(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    fun btIncluirOnClick(view: View) {
        val cadastro =
            Cadastro(0, binding.etNome.text.toString(), binding.etTelefone.text.toString())

        banco.inserir(cadastro)

        Toast.makeText(
            this, "Inclusão efetuada com Sucesso",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun btAlterarOnClick(view: View) {

        val cadastro = Cadastro(
            binding.etCod.text.toString().toInt(),
            binding.etNome.text.toString(),
            binding.etTelefone.text.toString()
        )

        banco.alterar(cadastro)

        Toast.makeText(
            this, "Alteração efetuada com Sucesso",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun btExcluirOnClick(view: View) {
        val cod = binding.etCod.text.toString()
        if (cod.isEmpty())
            Toast.makeText(
                this, "Informe o código do registro",
                Toast.LENGTH_SHORT
            ).show()
        else
        {
            banco.excluir(cod.toInt())


            Toast.makeText(
                this, "Exclusão efetuada com Sucesso",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun btPesquisarOnClick(view: View) {
        if (binding.etCod.text.toString().isEmpty()) {
            Toast.makeText(
                this, "Informe o código do registro",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val registro: Cadastro? = banco.pesquisar(binding.etCod.text.toString().toInt())


        if (registro != null) {
            binding.etNome.setText(registro.nome)
            binding.etTelefone.setText(registro.telefone)
        } else {
            binding.etNome.setText("")
            binding.etTelefone.setText("")

            Toast.makeText(
                this,
                "Registro não encontrado",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun btListarOnClick(view: View) {
        val intent = Intent(this, ListarActivity::class.java)
        startActivity(intent)

//        val registro: Cursor = banco.listar()
//
//        val saida = StringBuilder()
//
//        while (registro.moveToNext()) {
//            val nome: String = registro.getString(DatabaseHandler.COLUMN_NOME.toInt())
//            val telefone: String = registro.getString(DatabaseHandler.COLUMN_TELEFONE.toInt())
//
//            saida.append(" ${nome} - ${telefone}\n")
//        }
//
//        Toast.makeText(
//            this,
//            saida.toString(),
//            Toast.LENGTH_SHORT
//        ).show()



    }
}