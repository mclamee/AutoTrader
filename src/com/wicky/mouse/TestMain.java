package com.wicky.mouse;
import com.sun.jna.platform.win32.WinDef.WPARAM;

public class TestMain {
    public static void main(String[] args) throws Exception{
        MouseHook mouseHook = new MouseHook();
        mouseHook.addMouseHookListener(new MouseHookListener() {
            public void mouseMove(int nCode, WPARAM wParam,
                    MouseHookStruct lParam){
                int x = lParam.pt.x;
                int y = lParam.pt.y;
                System.out.println(x + " " + y);
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