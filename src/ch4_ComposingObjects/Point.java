package ch4_ComposingObjects;

import net.jcip.annotations.Immutable;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/17 12:40 PM
 */
@Immutable
public  class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}