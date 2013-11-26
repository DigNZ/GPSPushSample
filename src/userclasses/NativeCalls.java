/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package userclasses;

import com.codename1.system.NativeInterface;

/**
 *
 * @author Nick
 */
public interface NativeCalls extends NativeInterface {
    public void createNotification(String title,String message);
}
