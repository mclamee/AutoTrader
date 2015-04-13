package com.wicky.sample;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;

public class TryWithHWND {
    
   public static void main(String[] args) {
       System.setProperty("jna.encoding", "gb18030");  
       
      final User32 user32 = User32.INSTANCE;
      
      HWND fgWindow = user32.FindWindow(null, "网上股票交易系统5.0");
      
      user32.SetForegroundWindow(fgWindow);
      user32.ShowWindow(fgWindow, User32.SW_RESTORE);
      user32.SetFocus(fgWindow);
      
//      HWND fgWindow = user32.GetForegroundWindow();
//      int titleLength = user32.GetWindowTextLength(fgWindow) + 1;
//      char[] title = new char[titleLength];
//      user32.GetWindowText(fgWindow, title, titleLength);
//      System.out.println(Native.toString(title));
      
//      user32.EnumWindows(new WNDENUMPROC() {
//         int count = 0;
//         @Override
//         public boolean callback(HWND hWnd, Pointer arg1) {
//            byte[] windowText = new byte[1024];
//            user32.GetWindowTextA(hWnd, windowText, 1024);
//            String wText = Native.toString(windowText);
//            // get rid of this if block if you want all windows regardless of whether
//            // or not they have text
//            if (wText.isEmpty()) {
//               return true;
//            }
//
//            if(wText.contains("foobar2000")){
//            }
//            
//            System.out.println("Found window with text " + hWnd + ", total " + ++count
//                  + " Text: " + wText);
//            return true;
//         }
//      }, null);
   }
}