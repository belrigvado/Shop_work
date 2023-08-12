package com.example.shop.e2e;

public class Loader {
    public static final long PAGE_LOAD_INTERVAL = 1000;
    public static final int INTERNAL_RETRIES = 60;

    public static boolean load(ElementLoading elementLoading) {
        boolean isLoaded = false;
        for (int i = 0; i<INTERNAL_RETRIES; i++) {
            try {
                elementLoading.loadElement();
                isLoaded = true;
                break;
            } catch (Exception e) {}

            try {
                Thread.sleep(PAGE_LOAD_INTERVAL);
            } catch (InterruptedException ie) {}
        }

        if (isLoaded) {
            return true;
        }
        return false;
    }

    public static boolean load(CodeLoading codeLoading) {
        boolean isLoaded = false;
        for (int i = 0; i<INTERNAL_RETRIES; i++) {
            if (codeLoading.loadCode()) {
                isLoaded = true;
            }
            else {
                try {
                    Thread.sleep(PAGE_LOAD_INTERVAL);
                } catch (InterruptedException ie) {}
            }
        }

        if (isLoaded) {
            return true;
        }
        return false;
    }
}
