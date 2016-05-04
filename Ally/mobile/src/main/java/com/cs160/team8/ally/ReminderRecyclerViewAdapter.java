package com.cs160.team8.ally;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.cs160.team8.ally.ReminderFragment.OnReminderFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Reminder} and makes a call to the
 * specified {@link ReminderFragment.OnReminderFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ReminderRecyclerViewAdapter extends RecyclerView.Adapter<ReminderRecyclerViewAdapter.ViewHolder> {

    private final List<Reminder> mValues;
    private final OnReminderFragmentInteractionListener mListener;
    private Typeface lato;

    public ReminderRecyclerViewAdapter(List<Reminder> items, OnReminderFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reminder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTimeView.setText(holder.mItem.getTime());
        holder.mTimeView.setTypeface(lato);
        holder.mTitleView.setText(holder.mItem.title);
        holder.mTitleView.setTypeface(lato);

        if (holder.mItem.active) {
            holder.mActiveSwitch.setChecked(true);
            holder.makeActive();
        } else {
            holder.mActiveSwitch.setChecked(false);
            holder.makeInactive();
        }
        holder.mActiveSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.mItem.active = isChecked;
                holder.mItem.save();

                if (isChecked) {
                    holder.makeActive();
                } else {
                    holder.makeInactive();
                }
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onReminderFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTimeView;
        public final TextView mTitleView;
        public final Switch mActiveSwitch;
        public Reminder mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTimeView = (TextView) view.findViewById(R.id.reminder_time);
            mTitleView = (TextView) view.findViewById(R.id.reminder_title);

            mActiveSwitch = (Switch) view.findViewById(R.id.reminder_active_switch);
            lato = Typeface.createFromAsset(view.getContext().getAssets(), "Lato2OFL/Lato-Regular.ttf");
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }

        public void makeActive() {
            itemView.setBackgroundColor(Color.parseColor("#fdfdfd"));
            mTimeView.setTextColor(Color.parseColor("#000000"));
            mTitleView.setTextColor(Color.parseColor("#969696"));
        }

        public void makeInactive() {
            itemView.setBackgroundColor(Color.parseColor("#E9E9E9"));
            mTimeView.setTextColor(Color.parseColor("#8A8A8A"));
            mTitleView.setTextColor(Color.parseColor("#AEAEAE"));
        }
    }
}
