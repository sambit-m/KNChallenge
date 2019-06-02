package m.sambit.kisannetworkchallenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import m.sambit.kisannetworkchallenge.R;
import m.sambit.kisannetworkchallenge.room.MessageList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageListsViewHolder> {

    private Context mCtx;
    private List<MessageList> messageLists;

    public MessageAdapter(Context mCtx, List<MessageList> messageLists) {
        this.mCtx = mCtx;
        this.messageLists = messageLists;
    }

    @Override
    public MessageListsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_message, parent, false);
        return new MessageListsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageListsViewHolder holder, int position) {
        MessageList messageList = messageLists.get(position);
        holder.nameView.setText(messageList.getName());
        holder.otpView.setText(messageList.getOtp());
        holder.dateTimeView.setText(messageList.getDateTime());
    }

    @Override
    public int getItemCount() {
        return messageLists.size();
    }

    class MessageListsViewHolder extends RecyclerView.ViewHolder{

        TextView nameView, otpView, dateTimeView;

        public MessageListsViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name_view);
            otpView = itemView.findViewById(R.id.otp_view);
            dateTimeView = itemView.findViewById(R.id.time_view);
        }
    }
}