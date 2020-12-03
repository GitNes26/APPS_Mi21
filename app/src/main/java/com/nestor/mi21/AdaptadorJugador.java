package com.nestor.mi21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorJugador extends RecyclerView.Adapter<AdaptadorJugador.MiHolder> {

    private List<Jugador> ListaJugador;

    public AdaptadorJugador(List<Jugador> listaJugador) {
        this.ListaJugador = listaJugador;
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vistaJugador = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jugador, parent,false);
        return new MiHolder(vistaJugador);
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {

        Jugador modelo = ListaJugador.get(position);
        holder.setData(modelo);
    }

    @Override
    public int getItemCount() {
        return ListaJugador.size();
    }

    public class MiHolder extends RecyclerView.ViewHolder {

        private ImageView foto;
        private TextView id;
        private TextView nombre;
        private TextView numero;
        private TextView creado;
        private TextView modificado;


        public MiHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.fotoGamer);
            id = itemView.findViewById(R.id.ID);
            nombre = itemView.findViewById(R.id.nombre);
            numero = itemView.findViewById(R.id.numero);
            creado = itemView.findViewById(R.id.creado);
            modificado = itemView.findViewById(R.id.editado);

        }

        public void setData(final Jugador modelo) {
            foto.setImageResource(R.drawable.gamer);
            id.setText(String.valueOf(modelo.getId()));
            nombre.setText(modelo.getNombre());
            numero.setText(String.valueOf(modelo.getNumero()));
            creado.setText(modelo.getCreated_at());
            modificado.setText(modelo.getUpdated_at());
        }
    }
}
