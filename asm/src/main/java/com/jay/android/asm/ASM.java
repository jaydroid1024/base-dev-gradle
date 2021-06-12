package com.jay.android.asm;

/**
 * @author jaydroid
 * @version 1.0
 * @date 6/3/21
 */
class ASM {

    public static void main(String[] args) {
        m1();
    }

    public static void m1() {
        System.out.println("-----> now in method  m1 ");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
