package com.wicky.mouse;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;

public interface MouseHookProc extends HOOKPROC{
    LRESULT callback(int nCode, WPARAM wParam, MouseHookStruct lParam);
}