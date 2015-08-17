package com.sprite.spriteanimotion.utils;

import de.greenrobot.event.EventBus;

/**
 * Created by dings on 2015/8/13.
 */
public class EventUtil {

  private String mMsg;

  public EventUtil(String msg){
    mMsg = msg;
  }

  public String getMsg(){
    return mMsg;
  }
}
