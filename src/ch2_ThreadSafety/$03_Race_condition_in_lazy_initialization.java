package ch2_ThreadSafety;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 6:10 PM
 */
@NotThreadSafe
public class $03_Race_condition_in_lazy_initialization {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}

class ExpensiveObject { }