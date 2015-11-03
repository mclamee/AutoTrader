package com.wicky.mouse;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.wicky.trade.POINT;
import com.wicky.trade.User32;

public class TestMain {
    public static void main(String[] args) throws Exception{
        System.setProperty("jna.encoding", "gb18030");  
        
//        final User32 user32 = User32.INSTANCE;
        
        User32 u32 = (User32) Native.loadLibrary("user32", User32.class);
        
        MouseHook mouseHook = new MouseHook();
        mouseHook.addMouseHookListener(new MouseHookListener() {
            public void mouseMove(int nCode, WPARAM wParam,
                    MouseHookStruct lParam){
                int x = lParam.pt.x;
                int y = lParam.pt.y;
                System.out.println(x + " " + y);
                
                POINT point = new POINT();
                point.x = x;
                point.y = y;
                
                HWND hWnd = u32.WindowFromPoint(point);
                System.out.println(hWnd);
                
//                int titleLength = user32.GetWindowTextLength(hWnd) + 1;
//                char[] title = new char[titleLength];
//                user32.GetWindowText(hWnd, title, titleLength);
//                String wiTxt = Native.toString(title);
//                System.out.println(wiTxt);
                
            }
            public void lButtonDown(int nCode, WPARAM wParam,
                    MouseHookStruct lParam){
            }
            public void lButtonUp(int nCode, WPARAM wParam,
                    MouseHookStruct lParam){
            }
            public void rButtonDown(int nCode, WPARAM wParam,
                    MouseHookStruct lParam){
            }
            public void rButtonUp(int nCode, WPARAM wParam,
                    MouseHookStruct lParam){
            }
            public void mButtonDown(int nCode, WPARAM wParam,
                    MouseHookStruct lParam){
            }
            public void mButtonUp(int nCode, WPARAM wParam,
                    MouseHookStruct lParam){
            }
        });
        mouseHook.startWindowsHookEx();
        Thread.sleep(10000);
        mouseHook.stopWindowsHookEx();
    }
}