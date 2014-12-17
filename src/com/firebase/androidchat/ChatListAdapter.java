//#CSIT6000B#  Rio He       20227977 yheas@connect.ust.hk
//#CSIT6000B#  Cedric Liang 20220395 zliangag@connect.ust.hk
//#CSIT6000B#  Kevin Wang   20225577 zwangbo@connect.ust.hk 
package com.firebase.androidchat;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.firebase.client.Query;

public class ChatListAdapter extends FirebaseListAdapter<Chat> {

    // The username for this client. We use this to indicate which messages originated from this user
    private String username;

    public ChatListAdapter(Query ref, Activity activity, int layout, String username) {
        super(ref, Chat.class, layout, activity);
        this.username = username;
    }

    @Override
    protected void populateView(View view, Chat chat) {
        // Map a Chat object to an entry in our listview
        String author = chat.getAuthor();
        TextView authorText = (TextView)view.findViewById(R.id.author);
        authorText.setText(author + ": ");
        // If the message was sent by this user, color it differently
        if (author.equals(username)) {
            authorText.setTextColor(Color.RED);
        } else {
            authorText.setTextColor(Color.BLUE);
        }
        ((TextView)view.findViewById(R.id.message)).setText(chat.getMessage());
    }
}
