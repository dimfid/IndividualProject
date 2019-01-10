package Models;

import java.sql.Timestamp;

public class Message {
    public int Id;
    public String Data;
    public Timestamp Date;
    public int Sender;
    public int Receiver;

    public Message(){
    }

    public Message(int Id, String Data, Timestamp Date, int Sender, int Receiver) {
        this.Id = Id;
        this.Data = Data;
        this.Date = Date;
        this.Sender = Sender;
        this.Receiver = Receiver;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

    public int getSender() {
        return Sender;
    }

    public void setSender(int Sender) {
        this.Sender = Sender;
    }

    public int getReceiver() {
        return Receiver;
    }

    public void setReceiver(int Receiver) {
        this.Receiver = Receiver;
    }
}
