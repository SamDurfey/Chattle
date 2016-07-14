package com.epicodus.chattle.ui;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.epicodus.chattle.Constants;
import com.epicodus.chattle.R;
import com.epicodus.chattle.adapters.ConversationListAdapter;
import com.epicodus.chattle.models.Conversation;
import com.epicodus.chattle.models.Message;
import com.epicodus.chattle.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mUserReference; // This is what I've just written. Continuing setting up database references.
    private ArrayAdapter<User> userArrayAdapter;
    private ArrayList<User> usersArray;
//    private String userChoice;

    @Bind(R.id.signOutButton) Button mSignOutButton;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.spinner) Spinner mUserSpinner;

    private ConversationListAdapter mAdapter;

    public Message message1;
    public Message message2;
    public Message message3;
    public Conversation conversation1;
    public Conversation conversation2;
    public User user1;
    public User user2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mUserReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_USER);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSignOutButton.setOnClickListener(this);
        message1 = new Message("This is a cool message", "sender1", "recipient");
        message2 = new Message("This is a SUPER MESSAGE", "recipient", "sender1");
        message3 = new Message("This message is going to be alone", "sender2", "recipient");
        ArrayList<Message> messageList1 = new ArrayList<>();
        ArrayList<Message> messageList2 = new ArrayList<>();
        messageList1.add(message1);
        messageList1.add(message2);
        messageList2.add(message3);
        ArrayList<Conversation> conversationList = new ArrayList<>();
        conversation1 = new Conversation(messageList1, "recipient", "sender1");
        conversation2 = new Conversation(messageList2, "recipient", "sender2");
        conversationList.add(conversation1);
        conversationList.add(conversation2);

        user1 = new User("sam", "flerf@efd.ccc", "dddddddd", "5");
        user2 = new User("ass", "gitit@ass.ass", "skronk", "6");
        usersArray.add(user1);
        usersArray.add(user2);

        mAdapter = new ConversationListAdapter(getApplicationContext(), conversationList);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        //SPINNAH


        userArrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_spinner_item, usersArray);
        mUserSpinner.setAdapter(userArrayAdapter);
        mUserSpinner.setOnItemClickListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected (AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == mSignOutButton) {
            logout();
        }
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}
