package com.sprite.spriteanimotion.views;

/**
 * Created by dings on 2015/8/18.
 */
public interface ItemTouchListener {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
