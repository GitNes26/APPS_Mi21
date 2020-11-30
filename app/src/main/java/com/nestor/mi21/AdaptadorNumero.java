package com.nestor.mi21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorNumero extends RecyclerView.Adapter<AdaptadorNumero.MiHolder> {
    private List<Numero> ListaNumero;

    public AdaptadorNumero(List<Numero> listaNumero) {
        this.ListaNumero = listaNumero;
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vistaNumero = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carta, parent, false);
        return new MiHolder(vistaNumero);
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
        Numero modelo = ListaNumero.get(position);
        holder.setData(modelo);
    }

    @Override
    public int getItemCount() {
        return ListaNumero.size();
    }

    public class MiHolder extends RecyclerView.ViewHolder {

        private TextView numero;
        private ImageView carta;

        public MiHolder(@NonNull View itemView) {
            super(itemView);

            numero = itemView.findViewById(R.id.numero);
            carta = itemView.findViewById(R.id.fotoCarta);
        }

        public void setData(final Numero modelo) {
            numero.setText(modelo.getNumero());

            switch (modelo.getNumero()) {
                case "1":
                    carta.setImageResource(R.drawable.espada1);
                    break;
                case "2":
                    carta.setImageResource(R.drawable.espada2);
                    break;
                case "3":
                    carta.setImageResource(R.drawable.espada3);
                    break;
                case "4":
                    carta.setImageResource(R.drawable.espada4);
                    break;
                case "5":
                    carta.setImageResource(R.drawable.espada5);
                    break;
                case "6":
                    carta.setImageResource(R.drawable.espada6);
                    break;
                case "7":
                    carta.setImageResource(R.drawable.espada7);
                    break;
                case "8":
                    carta.setImageResource(R.drawable.espada8);
                    break;
                case "9":
                    carta.setImageResource(R.drawable.espada9);
                    break;
                case "10":
                    carta.setImageResource(R.drawable.espada10);
                    break;
                case "11":
                    carta.setImageResource(R.drawable.espada11);
                    break;
            }
        }
    }
}
