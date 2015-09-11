package com.wicky.mouse;
import com.sun.jna.platform.win32.WinDef.WPARAM;
/**
 * 屏幕取色工具
 * 
 * @author penngo(http://www.blogjava.net/pengo/)
 */
public interface MouseHookListener{
    void mouseMove(int nCode, WPARAM wParam,
            MouseHookStruct lParam);
    void lButtonDown(int nCode, WPARAM wParam,
            MouseHookStruct lParam);
    void lButtonUp(int nCode, WPARAM wParam,
            MouseHookStruct lParam);
    void rButtonDown(int nCode, WPARAM wParam,
            MouseHookStruct lParam);
    void rButtonUp(int nCode, WPARAM wParam,
            MouseHookStruct lParam);
    void mButtonDown(int nCode, WPARAM wParam,
            MouseHookStruct lParam);
    void mButtonUp(int nCode, WPARAM wParam,
            MouseHookStruct lParam);
}