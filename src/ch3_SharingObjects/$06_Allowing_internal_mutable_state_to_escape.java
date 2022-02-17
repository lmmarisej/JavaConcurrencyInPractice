package ch3_SharingObjects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2022/2/16 9:55 PM
 */
public class $06_Allowing_internal_mutable_state_to_escape {
    class UnsafeStates {
        private String[] states = new String[]{"AK", "AL"};

        public String[] getStates() {       // 暴露数组，将导致任何人都能修改数组内容
            return states;
        }
    }
}
