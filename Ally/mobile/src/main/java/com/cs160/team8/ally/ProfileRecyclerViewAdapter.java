package com.cs160.team8.ally;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs160.team8.ally.ProfilesFragment.OnProfileFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Visitor} and makes a call to the
 * specified {@link OnProfileFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder> {

    private final List<Visitor> mValues;
    private final OnProfileFragmentInteractionListener mListener;

    public ProfileRecyclerViewAdapter(List<Visitor> items, OnProfileFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Visitor visitor = mValues.get(position);
        holder.mItem = mValues.get(position);
        holder.mPhotoView.setImageBitmap(visitor.photo);
        holder.mNameView.setText(visitor.name);
        String relationshipAge = visitor.relationship + ", " + visitor.age;
        holder.mRelationshipAgeView.setText(relationshipAge);

        holder.mPushProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onPushProfileInteraction(holder.mItem);
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
        public final TextView mNameView;
        public final TextView mRelationshipAgeView;
        public final ImageView mPhotoView;
        public final ImageButton mPushProfileButton;
        public Visitor mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPhotoView = (ImageView) view.findViewById(R.id.profile_photo);
            mNameView = (TextView) view.findViewById(R.id.profile_name);
            mRelationshipAgeView = (TextView) view.findViewById(R.id.profile_relationship_age);
            mPushProfileButton = (ImageButton) view.findViewById(R.id.push_profile);
        }

        @Override
        public String toString(){
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
