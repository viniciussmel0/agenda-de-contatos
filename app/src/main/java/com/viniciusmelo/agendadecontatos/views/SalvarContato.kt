package com.viniciusmelo.agendadecontatos.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viniciusmelo.agendadecontatos.AppDataBase
import com.viniciusmelo.agendadecontatos.components.Botao
import com.viniciusmelo.agendadecontatos.components.OutlinedTextFieldCustom
import com.viniciusmelo.agendadecontatos.dao.ContatoDao
import com.viniciusmelo.agendadecontatos.model.Contato
import com.viniciusmelo.agendadecontatos.ui.theme.PURPLE_500
import com.viniciusmelo.agendadecontatos.ui.theme.WHITE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatoDao: ContatoDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarContato() {

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf(false) }

    var scope = rememberCoroutineScope()
    var context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Salvar novo Contato")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PURPLE_500,
                    titleContentColor = WHITE
                )
            )
        },


    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextFieldCustom(
                value = nome,
                onValueChange = {
                    nome = it
                },
                label = {
                    Text("Nome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            OutlinedTextFieldCustom(
                value = sobrenome,
                onValueChange = {
                    sobrenome = it
                },
                label = {
                    Text("Sobrenome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            OutlinedTextFieldCustom(
                value = idade,
                onValueChange = {
                    idade = it
                },
                label = {
                    Text("Idade")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            OutlinedTextFieldCustom(
                value = celular,
                onValueChange = {
                    celular = it
                },
                label = {
                    Text("Celular")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp)
            )

            Botao(
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        if (nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || celular.isEmpty()) {
                            mensagem = false
                        } else {
                            mensagem = true
                            val contato = Contato(
                                nome = nome,
                                sobrenome = sobrenome,
                                idade = idade,
                                celular = celular
                            )
                            contatoDao = AppDataBase.getInstance(context).contatoDao()
                            contatoDao.gravar(contato)
                        }
                    }

                    scope.launch(Dispatchers.Main) {
                        if (mensagem) {
                            Toast.makeText(context, "Sucesso ao salvar o contato!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                texto = "Salvar"
            )

        }
    }

}

@Preview
@Composable
fun SalvarContatoPreview() {
    SalvarContato()
}