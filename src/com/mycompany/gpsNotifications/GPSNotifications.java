package com.mycompany.gpsNotifications;


import com.codename1.ui.Display;
import com.codename1.ui.Form;
import userclasses.StateMachine;

public class GPSNotifications {
   
    private Form current;
    private static Object contextValue;

    public void init(Object context) {
        contextValue = context;
    }

    public static Object getContext() {
        return contextValue;
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        new StateMachine("/theme");        
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
