package com.lab.finallab.recyclerView;/**
 * Created by Manuka yasas,
 * University Sliit
 * Email manukayasas99@gmail.com
 **/

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lab.finallab.R;
import com.lab.finallab.database.MessageMaster;
import com.lab.finallab.model.Message;

import java.util.List;

/**
 * Created by Manuka yasas,
 * University Sliit
 * Email manukayasas99@gmail.com
 **/
public class MessagesRecycler extends RecyclerView.Adapter<MessagesRecycler.MessageViewHolder> {

    private Context context;
    private List<Message> messages;

    public MessagesRecycler(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, final int position) {
        holder.text1.setText(messages.get(position).get_id());
        holder.text2.setText(messages.get(position).getSubject());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.lab.finallab.ui.Message.class);
                intent.putExtra(com.lab.finallab.ui.Message.MESSAGE_ID, messages.get(position).get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (messages != null)
            return messages.size();
        else
            return 0;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
