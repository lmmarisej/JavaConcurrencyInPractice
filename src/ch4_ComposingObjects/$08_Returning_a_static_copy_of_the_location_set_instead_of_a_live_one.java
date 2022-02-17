package ch4_ComposingObjects;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 11:51 AM
 */
public class $08_Returning_a_static_copy_of_the_location_set_instead_of_a_live_one {

    @ThreadSafe
    public class DelegatingVehicleTracker {
        private final ConcurrentMap<String, Point> locations;
        private final Map<String, Point> unmodifiableMap;

        public DelegatingVehicleTracker(Map<String, Point> points) {
            locations = new ConcurrentHashMap<String, Point>(points);
            unmodifiableMap = Collections.unmodifiableMap(locations);
        }

        public Map<String, Point> getLocations() {
            return unmodifiableMap;
        }

        public Point getLocation(String id) {
            return locations.get(id);
        }

        public void setLocation(String id, int x, int y) {
            if (locations.replace(id, new Point(x, y)) == null)
                throw new IllegalArgumentException("invalid vehicle name: " + id);
        }

        // Alternate version of getLocations (Listing 4.8)
        public Map<String, Point> getLocationsAsStatic() {
            return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
        }
    }

}
