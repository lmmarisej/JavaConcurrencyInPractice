package ch10_AvoidingLivenessHazards;

import net.jcip.annotations.GuardedBy;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 9:47 PM
 */
public class CooperatingDeadlock {
    // Warning: deadlock-prone!
    class Taxi {
        @GuardedBy("this")
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        // Taxi            =》              Dispatcher
        public synchronized void setLocation(Point location) {      // 获取Taxi实例锁
            this.location = location;
            if (location.equals(destination))
                dispatcher.notifyAvailable(this);       // 会获取Dispatcher实例所
        }

        public synchronized Point getDestination() {
            return destination;
        }

        public synchronized void setDestination(Point destination) {
            this.destination = destination;
        }
    }

    class Dispatcher {
        @GuardedBy("this")
        private final Set<Taxi> taxis;
        @GuardedBy("this")
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {       // 会获取锁
            availableTaxis.add(taxi);
        }

        //  Dispatcher    =》       Taxi
        // 与setLocation方法获取锁顺序相反，因此将导致死锁出现的可能
        public synchronized Image getImage() {      // 会获取Dispatcher实例锁
            Image image = new Image();
            for (Taxi t : taxis)
                image.drawMarker(t.getLocation());      // 会获取Taxi实例锁
            return image;
        }
    }

    class Image {
        public void drawMarker(Point p) {
        }
    }
}
