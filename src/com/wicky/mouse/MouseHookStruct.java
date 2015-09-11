package com.wicky.mouse;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.POINT;
/**
 * 屏幕取色工具
 * 
 * @author penngo(http://www.blogjava.net/pengo/)
 */
public class MouseHookStruct extends Structure
{
    public static class ByReference extends MouseHookStruct implements Structure.ByReference {};
    public POINT pt;
    public HWND hwnd;
    public int wHitTestCode;
    public ULONG_PTR dwExtraInfo;
    
    @Override
    protected List getFieldOrder() {
        return Arrays.asList(new String[] { "pt", "hwnd", "wHitTestCode", "dwExtraInfo" });
    }
    
}