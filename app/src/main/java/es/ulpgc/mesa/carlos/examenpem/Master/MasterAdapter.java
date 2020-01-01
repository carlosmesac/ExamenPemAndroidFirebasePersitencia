package es.ulpgc.mesa.carlos.examenpem.Master;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.mesa.carlos.examenpem.Person;
import es.ulpgc.mesa.carlos.examenpem.R;


public class MasterAdapter extends RecyclerView.Adapter<MasterAdapter.ViewHolder> {
    private final View.OnClickListener clickListener;
    private ArrayList<Person> personList;
    private View.OnClickListener listener;

    public MasterAdapter(View.OnClickListener listener) {
        this.personList = new ArrayList<Person>();
        clickListener = listener;
    }

    public void addItem(Person person) {
        personList.add(person);
        notifyDataSetChanged();
    }

    public void addItems(List<Person> personList) {
        this.personList.addAll(personList);
        notifyDataSetChanged();
    }

    public void setItems(ArrayList<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.person_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(personList.get(position));
        holder.itemView.setOnClickListener(clickListener);

        Log.d("item", holder.itemView.getTag().toString());


        holder.personFullnameView.setText(personList.get(position).getSurname() + ", " +personList.get(position).getName());
        holder.personAgeView.setText(String.valueOf(personList.get(position).getAge()));
        holder.dniView.setText(String.valueOf(personList.get(position).getDni()));



    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView personFullnameView, personAgeView, dniView;

        public ViewHolder(View view) {
            super(view);
            personFullnameView = view.findViewById(R.id.fullName);
            personAgeView = view.findViewById(R.id.age);
            dniView = view.findViewById(R.id.dni);

        }
    }
}
