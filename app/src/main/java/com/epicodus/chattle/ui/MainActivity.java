package com.epicodus.chattle.ui;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.epicodus.chattle.Constants;
import com.epicodus.chattle.R;
import com.epicodus.chattle.adapters.ConversationListAdapter;
import com.epicodus.chattle.models.Conversation;
import com.epicodus.chattle.models.Message;
import com.epicodus.chattle.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private DatabaseReference mUserReference;
    private DatabaseReference mAllConversationsReference;
    private FirebaseAuth mAuth;
    private ArrayAdapter<User> userArrayAdapter;
    private String mCurrentUserUID;
    private User mCurrentUser;
    private ArrayList<Message> messages;
    private ArrayList<Conversation> conversationList;
    private DatabaseReference mAllUsersReference;
//    private String use

    @Bind(R.id.signOutButton) Button mSignOutButton;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.spinner) Spinner mUserSpinner;
    @Bind(R.id.newConversationButton) Button mNewConversationButton;
    @Bind(R.id.newConversationMessageBody) EditText mNewConversationMessageBody;

    private ConversationListAdapter mAdapter;

    private ValueEventListener mUserReferenceListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        mCurrentUserUID = mAuth.getCurrentUser().getUid();
        mAllUsersReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_USER);
        mAllConversationsReference = FirebaseDatabase.getInstance().getReference().child("conversation");
        mUserReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_USER).child(mCurrentUserUID);
        messages = new ArrayList<>();
        conversationList = new ArrayList<>();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSignOutButton.setOnClickListener(this);
        mUserReferenceListener = mAllConversationsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot conversationSnapshot : dataSnapshot.getChildren()){
                    String user1ID = conversationSnapshot.child("user1ID").getValue().toString();
                    String user2ID = conversationSnapshot.child("user2ID").getValue().toString();
                    for(DataSnapshot message : conversationSnapshot.child("messages").getChildren()){
                        Message newMess = new Message(message.getValue().toString());
                        messages.add(newMess);
                    }
                    String lastMessage = conversationSnapshot.child("lastMessage").toString();
                    Conversation newConversation = new Conversation(messages, user1ID, user2ID);
                    messages.clear();
                    conversationList.add(newConversation);

                }
                mAdapter = new ConversationListAdapter(getApplicationContext(), conversationList);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        //SPINNAH


        userArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_spinner_item, usersArray);
        mUserSpinner.setAdapter(userArrayAdapter);
        mUserSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //get the thing that is selected here
                String something  = mUserSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == mSignOutButton) {
            logout();}
//        } else if (view == mNewConversationButton){
//            String messageBody = mNewConversationMessageBody.getText().toString();
//            Message newMessage = new Message(messageBody);
//            ArrayList<Message> newMessageArrayList = new ArrayList<>();
//            newMessageArrayList.add(newMessage);
//
//            Conversation newConversation = new Conversation(newMessageArrayList, mCurrentUserUID, )
//        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}
