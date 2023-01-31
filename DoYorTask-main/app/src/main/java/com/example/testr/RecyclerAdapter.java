package com.example.testr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testr.helper.ItemTouchHelperAdapter;
import com.example.testr.helper.ItemTouchHelperViewHolder;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.ViewHolder>implements ItemTouchHelperAdapter {
    private final List<Delo> items;
    private LayoutInflater inflater;
    private String BackColorR1="#ADADAD",BackColorR11="#8E8E8E",
            BackColorR2="#5F5FA0",BackColorR22="#44408D",
            BackColorR3="#8260A0",BackColorR33="#62418C",
            BackColorR4="#C95BC1",BackColorR44="#BC3F9C",
            BackColorR5="#D63C3C",BackColorR55="#BA2525";
    private ColorFilter color;
    private float x,y;

    RecyclerAdapter(Context context,List<Delo> items)
    {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        final Delo item = items.get(position);
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие

                        break;
                    case MotionEvent.ACTION_MOVE: // движение
                          x = event.getX();
                          y = event.getY();
                          Log.i("onTouch: ", x+" "+y);
//                        String f=item.getRatingDelo().toString();
//                        Log.i("onTouch: ", f);
//                        switch (f) {
//                            case "1.0":
//                                Drawable mDrawable = holder.view1.getResources().getDrawable(R.drawable.mydrawable);
//                                mDrawable.setColorFilter(new
//                                        PorterDuffColorFilter(Color.parseColor(BackColorR1), PorterDuff.Mode.MULTIPLY));
//                                view.setBackground(mDrawable);
//                                break;
//                        }
                        break;
                    case MotionEvent.ACTION_UP:

                       break;// отпускание
                    case MotionEvent.ACTION_CANCEL:
//                        String f=item.getRatingDelo().toString();
//                        Log.i("onTouch: ", f);
//                        switch (f) {
//                            case "1.0":
//                                Drawable mDrawable = holder.view1.getResources().getDrawable(R.drawable.mydrawable);
//                                mDrawable.setColorFilter(new
//                                        PorterDuffColorFilter(Color.parseColor(BackColorR1), PorterDuff.Mode.MULTIPLY));
//                                view.setBackground(mDrawable);
//                                break;
//                        }
                    break;
                }

                return true;
            }
        });
        holder.nameView.setText(item.getNameDelo());
        Log.i("2222", item.getFinishDelo());
        holder.startView.setText("Start-"+item.getStartDelo());

        holder.finishView.setText("Finish-"+item.getFinishDelo());
//        holder.view1.setOnClickListener(onClickListener);
        holder.ratingBar.setRating(item.getRatingDelo());
        Drawable mDrawable = holder.view1.getResources().getDrawable(R.drawable.mydrawable);
        Drawable mDrawable1 = holder.nameView.getResources().getDrawable(R.drawable.mydrawable);

        int i=item.getRatingDelo().intValue();
        switch (i){
            case 1:{
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR1), PorterDuff.Mode.MULTIPLY));
                holder.view1.setBackground(mDrawable);
                mDrawable1.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR11), PorterDuff.Mode.MULTIPLY));
                holder.nameView.setBackground(mDrawable1);
                holder.startView.setBackgroundColor(Color.parseColor(BackColorR1));
                holder.finishView.setBackgroundColor(Color.parseColor(BackColorR1));
            }
            break;
            case 2:{
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR2), PorterDuff.Mode.MULTIPLY));
                holder.view1.setBackground(mDrawable);
                mDrawable1.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR22), PorterDuff.Mode.MULTIPLY));
                holder.nameView.setBackground(mDrawable1);
                holder.startView.setBackgroundColor(Color.parseColor(BackColorR2));
                holder.finishView.setBackgroundColor(Color.parseColor(BackColorR2));
            }
            break;
            case 3:{
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR3), PorterDuff.Mode.MULTIPLY));
                holder.view1.setBackground(mDrawable);
                mDrawable1.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR33), PorterDuff.Mode.MULTIPLY));
                holder.nameView.setBackground(mDrawable1);
                holder.startView.setBackgroundColor(Color.parseColor(BackColorR3));
                holder.finishView.setBackgroundColor(Color.parseColor(BackColorR3));
            }
            break;
            case 4:{
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR4), PorterDuff.Mode.MULTIPLY));
                holder.view1.setBackground(mDrawable);
                mDrawable1.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR44), PorterDuff.Mode.MULTIPLY));
                holder.nameView.setBackground(mDrawable1);
                holder.startView.setBackgroundColor(Color.parseColor(BackColorR4));
                holder.finishView.setBackgroundColor(Color.parseColor(BackColorR4));
            }
            break;
            case 5:{
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR5), PorterDuff.Mode.MULTIPLY));
                holder.view1.setBackground(mDrawable);
                mDrawable1.setColorFilter(new
                        PorterDuffColorFilter(Color.parseColor(BackColorR55), PorterDuff.Mode.MULTIPLY));
                holder.nameView.setBackground(mDrawable1);
                holder.startView.setBackgroundColor(Color.parseColor(BackColorR5));
                holder.finishView.setBackgroundColor(Color.parseColor(BackColorR5));
            }
            break;
        }
        if(item.getRatingDelo()==0.5){
            mDrawable.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR1), PorterDuff.Mode.MULTIPLY));
            holder.view1.setBackground(mDrawable);
            mDrawable1.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR11), PorterDuff.Mode.MULTIPLY));
            holder.nameView.setBackground(mDrawable1);
            holder.startView.setBackgroundColor(Color.parseColor(BackColorR1));
            holder.finishView.setBackgroundColor(Color.parseColor(BackColorR1));
        }
        else if(item.getRatingDelo()==1.5){
            mDrawable.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR2), PorterDuff.Mode.MULTIPLY));
            holder.view1.setBackground(mDrawable);
            mDrawable1.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR22), PorterDuff.Mode.MULTIPLY));
            holder.nameView.setBackground(mDrawable1);
            holder.startView.setBackgroundColor(Color.parseColor(BackColorR2));
            holder.finishView.setBackgroundColor(Color.parseColor(BackColorR2));
        }
        else if(item.getRatingDelo()==2.5){
            mDrawable.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR3), PorterDuff.Mode.MULTIPLY));
            holder.view1.setBackground(mDrawable);
            mDrawable1.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR33), PorterDuff.Mode.MULTIPLY));
            holder.nameView.setBackground(mDrawable1);
            holder.startView.setBackgroundColor(Color.parseColor(BackColorR3));
            holder.finishView.setBackgroundColor(Color.parseColor(BackColorR3));
        }
        else if(item.getRatingDelo()==3.5){
            mDrawable.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR4), PorterDuff.Mode.MULTIPLY));
            holder.view1.setBackground(mDrawable);
            mDrawable1.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR44), PorterDuff.Mode.MULTIPLY));
            holder.nameView.setBackground(mDrawable1);
            holder.startView.setBackgroundColor(Color.parseColor(BackColorR4));
            holder.finishView.setBackgroundColor(Color.parseColor(BackColorR4));
        }
        else if(item.getRatingDelo()==4.5){
            mDrawable.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR5), PorterDuff.Mode.MULTIPLY));
            holder.view1.setBackground(mDrawable);
            mDrawable1.setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor(BackColorR55), PorterDuff.Mode.MULTIPLY));
            holder.nameView.setBackground(mDrawable1);
            holder.startView.setBackgroundColor(Color.parseColor(BackColorR5));
            holder.finishView.setBackgroundColor(Color.parseColor(BackColorR5));
        }

    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Delo prev = items.remove(fromPosition);
        items.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder
    {
        final TextView nameView, startView,finishView;
        final RatingBar ratingBar;
        final View view1;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.NameDelo);
            startView = (TextView) view.findViewById(R.id.StartDelo);
            finishView = (TextView) view.findViewById(R.id.FinishDelo);
            ratingBar=(RatingBar) view.findViewById(R.id.RatingDelo);
            view1=(View) view.findViewById(R.id.Mainlayout);
        }

        @Override
        public void onItemSelected() {
//            DisplayMetrics displaymetrics = itemView.getResources().getDisplayMetrics();
//            Log.i("onItemSelected: ", "Ширина: " + displaymetrics.widthPixels + "\n" +
//                    "Высота: " + displaymetrics.heightPixels + "\n");
//            if(x>displaymetrics.widthPixels/2){
                color=itemView.getBackground().getColorFilter();
                Drawable m=itemView.getResources().getDrawable(R.drawable.mydrawable);
                m.setColorFilter(new
                        PorterDuffColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY));
                itemView.setBackground(m);
//            }
//            else if(x<displaymetrics.widthPixels/2){
//                color=itemView.getBackground().getColorFilter();
//                Drawable m=itemView.getResources().getDrawable(R.drawable.mydrawable);
//                m.setColorFilter(new
//                        PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY));
//                itemView.setBackground(m);
//            }

        }

        @Override
        public void onItemClear() {
            Drawable m=itemView.getResources().getDrawable(R.drawable.mydrawable);
            m.setColorFilter(color);
            itemView.setBackground(m);
        }
    }
}
