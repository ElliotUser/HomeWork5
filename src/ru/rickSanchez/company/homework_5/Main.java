package ru.rickSanchez.company.homework_5;

public class Main {
    static final int size = 10;
    static final int halfSize = size/2;
    static float[] arr = new float[size];

    public static void main(String[] args) {
        arrCalculation(arr);
        splitArray(arr);
    }

    public static void splitArray(float[] array) {
        long before = 0;
        for(int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        before = System.currentTimeMillis();
            float[] arrSplit_a = new float[halfSize];
            float[] arrSplit_b = new float[halfSize];

            System.arraycopy(array,0,arrSplit_a,0,halfSize);

            System.arraycopy(array,halfSize,arrSplit_b,0,halfSize);

            Thread thread_1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < halfSize; j++) {
                        arrSplit_a[j] = (float)(arrSplit_a[j] * Math.sin(0.2f + arrSplit_a[j] / 5) * Math.cos(0.2f + arrSplit_a[j] / 5) * Math.cos(0.4f + arrSplit_a[j] / 2));

                    }
                    System.out.println("Поток №1 закончил работу.");
                }
            });

        Thread thread_2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < halfSize; j++) {
                        arrSplit_b[j] = (float)(arrSplit_b[j] * Math.sin(0.2f + arrSplit_b[j] / 5) * Math.cos(0.2f + arrSplit_b[j] / 5) * Math.cos(0.4f + arrSplit_b[j] / 2));

                    }
                    System.out.println("Поток №2 закончил работу.");
                }
            });

        thread_1.start();
        thread_2.start();
        try {
            thread_1.join();
            thread_2.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arrSplit_a,0,array,0,halfSize);
        System.arraycopy(arrSplit_b,0,array,halfSize,halfSize);

        System.out.println("Время выполнения метода splitArray() = " + (System.currentTimeMillis()-before) + " мс.");
    }




    public static void arrCalculation(float[] array){
        long before = 0;
        for(int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        before = System.currentTimeMillis();
        for(int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + array[i] / 5) * Math.cos(0.2f + array[i] / 5) * Math.cos(0.4f + array[i] / 2));
        }
        System.out.println("Время выполнения метода arrCalculation() = " + (System.currentTimeMillis()-before) + " мс.");
    }
}
