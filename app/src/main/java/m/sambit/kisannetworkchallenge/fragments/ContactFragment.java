package m.sambit.kisannetworkchallenge.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import m.sambit.kisannetworkchallenge.R;
import m.sambit.kisannetworkchallenge.adapter.ContactAdapter;
import m.sambit.kisannetworkchallenge.model.Contacts;

/**
 * The contact list is shown using {@link RecyclerView} using {@link ContactAdapter}
 * Model class {@link Contacts} is used.
 */
public class ContactFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private ArrayList<Contacts> contacts;
    private ContactAdapter contactAdapter;

    public ContactFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);


        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        contacts = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = obj.getJSONArray("contacts");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String firstName = jsonObject.optString("first_name");
                String lastName = jsonObject.optString("last_name");
                String phone = jsonObject.optString("phone");
                contacts.add(new Contacts(firstName, lastName, phone));
            }

            contactAdapter = new ContactAdapter(contacts);
            recyclerView.setAdapter(contactAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }

    private void openDialog(){

    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("contacts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
