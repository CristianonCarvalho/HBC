package com.carvalho.education.hbc.estudolist.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carvalho.education.hbc.R;
import com.carvalho.education.hbc.entities.Estudo;
import com.carvalho.education.hbc.estudolist.SwipeableInterface;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionMoveToSwipedDirection;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.RecyclerViewAdapterUtils;
import com.carvalho.education.hbc.lib.ViewUtils;
import com.carvalho.education.hbc.lib.AbstractDataProvider;

import java.util.EventListener;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 06094547659 on 12/07/2016.
 */
public class EstudoAdapter extends RecyclerView.Adapter<EstudoAdapter.ViewHolder>
        implements SwipeableItemAdapter<EstudoAdapter.ViewHolder> {
    private static final String TAG = "MySwipeableItemAdapter";

    private List<Estudo> estudoList;
    private OnItemClickListerner onItemClickListerner;
    private View.OnClickListener mSwipeableViewContainerOnClickListener;
    private View.OnClickListener mUnderSwipeableViewButtonOnClickListener;

    private AbstractDataProvider mProvider;


    public EstudoAdapter(List<Estudo> estudoList, OnItemClickListerner onItemClickListerner) {
        this.estudoList = estudoList;
        this.onItemClickListerner = onItemClickListerner;

        setHasStableIds(true);
    }

    public EstudoAdapter(AbstractDataProvider dataProvider) {
        mProvider = dataProvider;
        mSwipeableViewContainerOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwipeableViewContainerClick(v);
            }
        };
        mUnderSwipeableViewButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUnderSwipeableViewButtonClick(v);
            }
        };

        // SwipeableItemAdapter requires stable ID, and also
        // have to implement the getItemId() method appropriately.
        setHasStableIds(true);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_stored_estudo, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EstudoAdapter.ViewHolder holder, int position) {
        Estudo currentEstudo = estudoList.get(position);
        holder.txtEstudo.setText(currentEstudo.getComment()); // alterar para foreing key
        holder.txtDate.setText(currentEstudo.getDate().toString());
        holder.setOnItemClickListerner(currentEstudo, onItemClickListerner);

        // set background resource (target view ID: container)
        final int swipeState = holder.getSwipeStateFlags();

        if ((swipeState & SwipeableInterface.STATE_FLAG_IS_UPDATED) != 0) {
                int bgResId;

                if ((swipeState & SwipeableInterface.STATE_FLAG_IS_ACTIVE) != 0) {
                    bgResId = R.drawable.bg_item_swiping_active_state;
                } else if ((swipeState & SwipeableInterface.STATE_FLAG_SWIPING) != 0) {
                    bgResId = R.drawable.bg_item_swiping_state;
                } else {
                    bgResId = R.drawable.bg_item_normal_state;
                }

                holder.mContainer.setBackgroundResource(bgResId);
        }

        // set swiping properties
        holder.setMaxLeftSwipeAmount(-0.5f);
        holder.setMaxRightSwipeAmount(0);
        holder.setSwipeItemHorizontalSlideAmount(
                currentEstudo.isPinned() ? -0.5f : 0);
    }

    @Override
    public int onGetSwipeReactionType(ViewHolder holder, int position, int x, int y) {
        if (ViewUtils.hitTest(holder.getSwipeableContainerView(), x, y)) {
            return SwipeableInterface.REACTION_CAN_SWIPE_BOTH_H;
        } else {
            return SwipeableInterface.REACTION_CAN_NOT_SWIPE_BOTH_H;
        }
    }

    @Override
    public void onSetSwipeBackground(ViewHolder holder, int position, int type) {
        int bgRes = 0;
        switch (type) {
            case SwipeableInterface.DRAWABLE_SWIPE_NEUTRAL_BACKGROUND:
                bgRes = R.drawable.bg_swipe_item_neutral;
                break;
            case SwipeableInterface.DRAWABLE_SWIPE_LEFT_BACKGROUND:
                bgRes = R.drawable.bg_swipe_item_left;
                break;
            case SwipeableInterface.DRAWABLE_SWIPE_RIGHT_BACKGROUND:
                bgRes = R.drawable.bg_swipe_item_right;
                break;
        }

        holder.itemView.setBackgroundResource(bgRes);
    }

    @Override
    public SwipeResultAction onSwipeItem(ViewHolder holder, int position, int result) {
        Log.d(TAG, "onSwipeItem(position = " + position + ", result = " + result + ")");

        switch (result) {
            // swipe left --- pin
            case SwipeableInterface.RESULT_SWIPED_LEFT:
                return new SwipeLeftResultAction(this, position);
            // other --- do nothing
            case SwipeableInterface.RESULT_SWIPED_RIGHT:
            case SwipeableInterface.RESULT_CANCELED:
            default:
                if (position != RecyclerView.NO_POSITION) {
                    return new UnpinResultAction(this, position);
                } else {
                    return null;
                }
        }
    }

    private static class SwipeLeftResultAction extends SwipeResultActionMoveToSwipedDirection {
        private EstudoAdapter mAdapter;
        private final int mPosition;
        private boolean mSetPinned;

        SwipeLeftResultAction(EstudoAdapter adapter, int position) {
            mAdapter = adapter;
            mPosition = position;
        }

        @Override
        protected void onPerformAction() {
            super.onPerformAction();
            AbstractDataProvider.Data item = mAdapter.estudoList.get(mPosition);

            if (!item.isPinned()) {
                item.setPinned(true);
                mAdapter.notifyItemChanged(mPosition);
                mSetPinned = true;
            }
        }

        @Override
        protected void onSlideAnimationEnd() {
            super.onSlideAnimationEnd();

            if (mSetPinned && mAdapter.mEventListener != null) {
                mAdapter.mEventListener.onItemPinned(mPosition);
            }
        }

        @Override
        protected void onCleanUp() {
            super.onCleanUp();
            // clear the references
            mAdapter = null;
        }
    }

    public void setEstudos(List<Estudo> estudos) {
        this.estudoList = estudos;
        notifyDataSetChanged();
    }

    public void removeEstudo(Estudo estudo) {
        estudoList.remove(estudo);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return estudoList.size();
    }

    @Override
    public SwipeResultAction onSwipeItem(ViewHolder holder, int position, int result) {
        return null;
    }


    static class ViewHolder extends AbstractSwipeableItemViewHolder {
        @Bind(R.id.txtEstudo)
        TextView txtEstudo;
        @Bind(R.id.txtDate)
        TextView txtDate;
        @Bind(R.id.container)
        LinearLayout mContainer;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        @Override
        public View getSwipeableContainerView() {
            return itemView;
        }

        public void setOnItemClickListerner(final Estudo currentEstudo, final OnItemClickListerner onItemClickListerner) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListerner.onItemClick(currentEstudo);
                    //onSwipeableViewContainerClick(view);
                }
            });
        }
    }

}
