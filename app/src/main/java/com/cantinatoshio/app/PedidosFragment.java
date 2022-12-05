package com.cantinatoshio.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cantinatoshio.app.api.Api;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;



public class PedidosFragment extends Fragment
{

    RecyclerView listapedidos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
      //new getData(Api.URL_CLIENTE_PEDIDOS).start();
      View view = inflater.inflate(R.layout.fragment_pedidos, container, false);
      listapedidos = view.findViewById(R.id.lst_pedidos);
      AdapterPedidos adapterPedidos = new AdapterPedidos(getContext(), new Cliente().getPedidos());

      listapedidos.setLayoutManager(new GridLayoutManager(getContext(), 1));

      listapedidos.hasFixedSize();

      listapedidos.setAdapter(adapterPedidos);

      //clique pra abrir nova janela


      return view;
    }

    //adaptador de pedidos como classe interna

    private class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.ViewHolder>
    {
        private ArrayList<Pedido> pedidos;
        private Context context;

        public AdapterPedidos(Context context, ArrayList<Pedido> pedidos)
        {
            this.pedidos = pedidos;
            this.context = context;
        }

        @NonNull
        @Override
        public AdapterPedidos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.modelo_pedido_realizado, parent, false);
            return new AdapterPedidos.ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull AdapterPedidos.ViewHolder holder, int position)
        {
            holder.txt_idPedido.setText(pedidos.get(position).getIdPedido());
            holder.txt_dataPedido.setText(pedidos.get(position).getDataPedido());
            holder.txtValorPedido.setText((int) pedidos.get(position).getValorPedido());

            holder.cardPedidos.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, ClickPedidosActivity.class);

                }
            });

        }

        @Override
        public int getItemCount()
        {
            return pedidos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            CardView cardPedidos;
            TextView txt_dataPedido, txt_idPedido, txtValorPedido;


            public ViewHolder(View view)
            {
                super(view);
                cardPedidos = itemView.findViewById(R.id.cardPedidos);
                txt_dataPedido = itemView.findViewById(R.id.txt_dataPedido);
                txt_idPedido = itemView.findViewById(R.id.txt_idPedido);
                txtValorPedido = itemView.findViewById(R.id.txtValorPedido);
            }
        }
    }


}