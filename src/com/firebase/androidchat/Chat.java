//#CSIT6000B#  Rio He       20227977 yheas@connect.ust.hk
//#CSIT6000B#  Cedric Liang 20220395 zliangag@connect.ust.hk
//#CSIT6000B#  Kevin Wang   20225577 zwangbo@connect.ust.hk 
package com.firebase.androidchat;

public class Chat {

    private String message;
    private String author;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Chat() { }

    Chat(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
