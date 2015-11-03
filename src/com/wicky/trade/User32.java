package com.wicky.trade;

import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public interface User32 extends StdCallLibrary {
    public HWND WindowFromPoint(POINT Point);
}