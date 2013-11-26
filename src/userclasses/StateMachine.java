/**
 * Your application code goes here
 */
package userclasses;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.Log;
import com.codename1.location.Location;
import com.codename1.location.LocationListener;
import com.codename1.location.LocationManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import generated.StateMachineBase;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.codename1.util.MathUtil;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {

    private static Location startLocation = null;

    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }

    /**
     * this method should be used to initialize variables instead of the
     * constructor/class scope to avoid race conditions
     */
    @Override
    protected void initVars(Resources res) {

    }

    @Override
    protected void postMain(Form f) {
        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        startLocation = LocationManager.getLocationManager().getCurrentLocationSync();
        LocationManager.getLocationManager().setLocationListener(new LocationListener() {

            public void locationUpdated(Location location) {
                
                Log.p("Location: " + location.getLatitude() + " " + location.getLongitude());
                findTextArea().setText("" + location.getLatitude());
                findTextArea1().setText("" + location.getLongitude());
                findTextArea2().setText("" + distanceBetween(location, startLocation) + "m");
                if (distanceBetween(location, startLocation) > 1) {
                    Display.getInstance().notifyStatusBar("You have moved more than " +distanceBetween(location, startLocation)+" metres" , "Moving", "Using location services we have detected that you have moved", true, true, null);
                }
            }

            public void providerStateChanged(int newState) {

            }

            private double distanceBetween(Location location, Location startLocation) {
                return MathUtil.acos(Math.sin(location.getLatitude()) * Math.sin(startLocation.getLatitude()) + Math.cos(location.getLatitude()) * Math.cos(startLocation.getLatitude()) * Math.cos(startLocation.getLongitude() - location.getLongitude())) * 6371;
            }
        });
        dlg.dispose();
        
        UITimer t = new UITimer(new Runnable() {

            public void run() {
                Log.p("Running...");
            }
        });
        t.schedule(2000, true, f);
    }
}
