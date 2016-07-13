package com.epicodus.chattle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.chattle.models.Conversation;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/13/16.
 */
public class ConversationListAdapter extends RecyclerView.Adapter<ConversationListAdapter.ConversationViewHolder> {
    private ArrayList<Conversation> mConversations = new ArrayList<>();
    private Context mContext;

    public ConversationListAdapter(Context context, ArrayList<Conversation> conversations){
        mContext = context;
        mConversations = conversations;
    }

    public class ConversationViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.userNameTextView) TextView mUserNameView;
        @Bind(R.id.messageBodyTextView) TextView mMessageBodyTextView;

        public ConversationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindConversation(Conversation conversation){
            mMessageBodyTextView.setText(conversation.getLastMessage());
            mUserNameView.setText(conversation.getYou());
        }
    }
    @Override
    public ConversationListAdapter.ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_list_item, parent, false);
        ConversationViewHolder viewHolder = new ConversationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ConversationListAdapter.ConversationViewHolder holder, int position) {
        holder.bindConversation(mConversations.get(position));
    }

    @Override
    public int getItemCount() {
        return mConversations.size();
    }
}
