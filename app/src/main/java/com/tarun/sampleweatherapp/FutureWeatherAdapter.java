package com.tarun.sampleweatherapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tarun.sampleweatherapp.pojo.FutureData;

import java.util.ArrayList;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FutureData> futureDataArrayList;
    private LayoutInflater layoutInflater;
    private static final String TAG = "FutureWeatherAdapter";


    public FutureWeatherAdapter(Context context) {
        this.context = context;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.future_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: called===="+futureDataArrayList.get(position).getTemp() );
        holder.getDay().setText(futureDataArrayList.get(position).getDay());
        holder.getTemp().setText(futureDataArrayList.get(position).getTemp()+ "\u2103");
        if(Integer.parseInt(futureDataArrayList.get(position).getTemp())>23){
            Glide.with(context).load(R.drawable.sun).into(holder.getWeatherIcon());
        }else{
            Glide.with(context).load(R.drawable.rain).into(holder.getWeatherIcon());

        }

    }

    public void setData(ArrayList<FutureData> futureDataArrayList){
        this.futureDataArrayList=futureDataArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return futureDataArrayList!=null ?futureDataArrayList.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView day;
        private TextView temp;
        private ImageView weatherIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.day);
            weatherIcon=itemView.findViewById(R.id.weatherIcon);
            temp=itemView.findViewById(R.id.temp);
        }

        public TextView getDay() {
            return day;
        }

        public TextView getTemp() {
            return temp;
        }

        public ImageView getWeatherIcon() {
            return weatherIcon;
        }
    }
}
