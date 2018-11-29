package com.example.daiphongpc.btrcv_chat.Adapter;

import android.content.Context;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daiphongpc.btrcv_chat.MainActivity;
import com.example.daiphongpc.btrcv_chat.Model.Messenger;
import com.example.daiphongpc.btrcv_chat.R;

import java.util.ArrayList;

public class ChatAdapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     Context context;
     ArrayList<Messenger> arrData;

    public ChatAdapterRecycler(Context context, ArrayList<Messenger> arrData) {
        this.context = context;
        this.arrData = arrData;
    }

    public ChatAdapterRecycler() {
    }

    @Override
    public int getItemViewType(int position) {
        //=1 là gửi
        if (arrData.get(position).getIs_going()==1){
            return 1;
        }else {
            return 0;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==1){
            View view=LayoutInflater.from(context).inflate(R.layout.item_send,viewGroup,false);
            return new SendViewHolder(view);
        }else {
            View view=LayoutInflater.from(context).inflate(R.layout.item_receive,viewGroup,false);
            return new ReceiveViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (arrData.get(i).getIs_going()==1){
            ((SendViewHolder)viewHolder).txtSend.setText(arrData.get(i).getContent());
            arrData.get(i).setUserName("Sơn");
            ((SendViewHolder)viewHolder).txtUserSend.setText(arrData.get(i).getUserName());
        }else {
            ((ReceiveViewHolder)viewHolder).txtRec.setText(arrData.get(i).getContent());
            arrData.get(i).setUserName("Tuyết");
            ((ReceiveViewHolder) viewHolder).txtUserRec.setText(arrData.get(i).getUserName());
        }
    }

    @Override
    public int getItemCount() {
        return arrData.size();
    }

    class SendViewHolder extends RecyclerView.ViewHolder{
        TextView txtSend,txtUserSend;
        public SendViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSend=itemView.findViewById(R.id.txt_send);
            txtUserSend=itemView.findViewById(R.id.txt_user_send);
        }
    }
    class ReceiveViewHolder extends  RecyclerView.ViewHolder{
        TextView txtRec,txtUserRec;
        public ReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRec=itemView.findViewById(R.id.txt_rec);
            txtUserRec=itemView.findViewById(R.id.txt_user_rec);
        }
    }
}
