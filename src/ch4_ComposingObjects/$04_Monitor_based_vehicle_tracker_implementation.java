package ch4_ComposingObjects;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.Immutable;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:50 AM
 */
public class $04_Monitor_based_vehicle_tracker_implementation {

    @ThreadSafe
    public static class MonitorVehicleTracker {
        @GuardedBy("this")
        private final Map<String, MutablePoint> locations;

        public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
            this.locations = deepCopy(locations);
        }

        public synchronized Map<String, MutablePoint> getLocations() {
            return deepCopy(locations);
        }

        public synchronized MutablePoint getLocation(String id) {
            MutablePoint loc = locations.get(id);
            return loc == null ? null : new MutablePoint(loc);
        }

        public synchronized void setLocation(String id, int x, int y) {
            MutablePoint loc = locations.get(id);
            if (loc == null)
                throw new IllegalArgumentException("No such ID: " + id);
            loc.x = x;
            loc.y = y;
        }

        private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
            Map<String, MutablePoint> result = new HashMap<>();

            for (String id : m.keySet())
                result.put(id, new MutablePoint(m.get(id)));        // 通过拷贝值构造一个新的对象

            return Collections.unmodifiableMap(result);
        }
    }

    @NotThreadSafe
    public static class MutablePoint {
        public int x, y;

        public MutablePoint() {
            x = 0;
            y = 0;
        }

        public MutablePoint(MutablePoint p) {
            this.x = p.x;
            this.y = p.y;
        }
    }
}
