package com.zhulinfeng.mine;

import java.util.Objects;

public class Preconditions {
    private Preconditions() {
        throw new IllegalStateException();
    }

    public static void checkNotNull(Objects o) {
        if (null == o) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }
}
