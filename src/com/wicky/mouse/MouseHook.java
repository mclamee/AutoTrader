package com.wicky.mouse;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.MSG;
/**
 * @author penngo(http://www.blogjava.net/pengo/)
 */
public class MouseHook {
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_MBUTTONDOWN = 519;
    public static final int WM_MBUTTONUP = 520;
    public User32 lib;
    private static HHOOK hhk;
    private MouseHookListener hookListener;
    private HMODULE hMod;
    private boolean isWindows = false;
    private boolean isStart = false;
    public MouseHook() {
        isWindows = Platform.isWindows();
        if(isWindows){
            lib = User32.INSTANCE;
            hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        }
        
    }

    public void addMouseHookListener(MouseHookListener hookListener) {
        this.hookListener = hookListener;
    }

    public void startWindowsHookEx() {
        MouseHookProc hookProc = new MouseHookProc(){
            public LRESULT callback(int nCode, WPARAM wParam,
                    MouseHookStruct lParam) {
                if (nCode >= 0) {
                    switch (wParam.intValue()) {
                    case MouseHook.WM_MOUSEMOVE:
                        hookListener.mouseMove(nCode, wParam, lParam);
                        break;
                    case MouseHook.WM_LBUTTONDOWN:
                        hookListener.lButtonDown(nCode, wParam, lParam);
                        break;
                    case MouseHook.WM_LBUTTONUP:
                        hookListener.lButtonUp(nCode, wParam, lParam);
                        break;
                    case MouseHook.WM_RBUTTONDOWN:
                        hookListener.rButtonDown(nCode, wParam, lParam);
                        break;
                    case MouseHook.WM_RBUTTONUP:
                        hookListener.rButtonUp(nCode, wParam, lParam);
                        break;
                    case MouseHook.WM_MBUTTONDOWN:
                        hookListener.mButtonDown(nCode, wParam, lParam);
                        break;
                    case MouseHook.WM_MBUTTONUP:
                        hookListener.mButtonUp(nCode, wParam, lParam);
                        break;
                    }
                }
                return lib.CallNextHookEx(hhk, nCode, wParam,
                        lParam.getPointer());
            }
        };
        if(isWindows){
            isStart = true;
            lib.SetWindowsHookEx(WinUser.WH_MOUSE_LL, hookProc, hMod, 0);
            MSG msg = new MSG();
            lib.GetMessage(msg, null, 0, 0);
        }
    }

    public void stopWindowsHookEx() {
        if(isWindows && isStart){
            lib.UnhookWindowsHookEx(hhk);
            isStart = false;
        }
    }
}