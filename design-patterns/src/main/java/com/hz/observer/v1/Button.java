package com.hz.observer.v1;

/**
 * @Auther zehua
 * @Date 2020/11/8 7:31
 */
public class Button {
    private PopupWindow popupWindow;

    public Button() {
        this.popupWindow = new PopupWindow();
    }

    public void click() {
        popupWindow.popup();
    }

}
