package com.cantinatoshio.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ClickPedidosActivity extends AppCompatActivity
{

    TextView idPedido, statusPedido;
    TextView dataPedido;
    //posteriormente, status do Pedido, pagamento e data entrarão aqui

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_pedidos);

        idPedido = findViewById(R.id.txtClickIDPedido);
        statusPedido = findViewById(R.id.txtStatusPedido);
        dataPedido = findViewById(R.id.dataPedido);

        Intent intent = getIntent();

        //pegando a variável
        String ClickDataPedido = intent.getStringExtra("dataPedido");
       // String ClickHoraPedido = intent.getStringExtra("horaPedido");
        String ClickidPedido = intent.getStringExtra("idPedido");
        String ClickStatusPedido = intent.getStringExtra("statusPedido");

        idPedido.setText(ClickidPedido);
        dataPedido.setText(ClickDataPedido);
        statusPedido.setText(ClickStatusPedido);


        //pode dar erro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}