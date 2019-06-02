package m.sambit.kisannetworkchallenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import m.sambit.kisannetworkchallenge.R;
import m.sambit.kisannetworkchallenge.activity.ContactDetailsActivity;
import m.sambit.kisannetworkchallenge.model.Contacts;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    ArrayList<Contacts> contacts;

    public ContactAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contact, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.nameView.setText(contacts.get(position).getFullName());

        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactDetailsIntent = new Intent(holder.itemView.getContext(), ContactDetailsActivity.class);
                contactDetailsIntent.putExtra("name", contacts.get(position).getFullName());
                contactDetailsIntent.putExtra("phone", contacts.get(position).getPhone());
                holder.itemView.getContext().startActivity(contactDetailsIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        MaterialCardView materialCardView;

        MyViewHolder(View listItemView) {
            super(listItemView);
            nameView = listItemView.findViewById(R.id.name_view);
            materialCardView = listItemView.findViewById(R.id.contact_card);
        }
    }
}