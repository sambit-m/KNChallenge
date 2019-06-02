package m.sambit.kisannetworkchallenge.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import m.sambit.kisannetworkchallenge.R;
import m.sambit.kisannetworkchallenge.adapter.MessageAdapter;
import m.sambit.kisannetworkchallenge.room.DatabaseClient;
import m.sambit.kisannetworkchallenge.room.MessageList;

/**
 * The message list is shown using {@link RecyclerView} using {@link MessageAdapter}
 */
public class MessageFragment extends Fragment {

    private TextView noMessageView;
    private RecyclerView recyclerView;

    public MessageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);


        noMessageView = view.findViewById(R.id.no_messages);
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        getMessages();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    private void getMessages() {
        class GetMessages extends AsyncTask<Void, Void, List<MessageList>> {

            @Override
            protected List<MessageList> doInBackground(Void... voids) {
                List<MessageList> messageLists = DatabaseClient
                        .getInstance(getContext())
                        .getAppDatabase()
                        .messageDao()
                        .getAll();
                return messageLists;
            }

            @Override
            protected void onPostExecute(List<MessageList> messageLists) {
                super.onPostExecute(messageLists);
                if(messageLists.size()>0)
                    noMessageView.setVisibility(View.GONE);
                else
                    noMessageView.setVisibility(View.VISIBLE);
                MessageAdapter adapter = new MessageAdapter(getContext(), messageLists);
                recyclerView.setAdapter(adapter);
            }
        }

        GetMessages getMessages = new GetMessages();
        getMessages.execute();
    }
}
