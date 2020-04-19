package ru.rickSanchez.company.homework_5;

public class Main {
    static final int size = 10000000;
    static final int halfSize = size/2;
    //1) Создаем одномерный длинный массив, например:
    static float[] arr = new float[size];

    public static void main(String[] args) {
        //Отличие первого метода от второго:
        //Первый просто бежит по массиву и вычисляет значения.
        arrCalculation(arr);
        //Второй разбивает массив на два массива, в двух потоках
        // высчитывает новые значения и потом склеивает эти массивы
        // обратно в один.
        splitArray(arr);
    }

    public static void splitArray(float[] array) {
        long before = 0;
        //2) Заполняем этот массив единицами:
        for(int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        //3) Засекаем время выполнения:
        before = System.currentTimeMillis();
            //4)Разбиваем массив на два массива, в двух потоках
            // высчитывает новые значения и потом склеивает эти массивы
            // обратно в один.
            float[] arrSplit_a = new float[halfSize];
            float[] arrSplit_b = new float[halfSize];
            //первый массив
            System.arraycopy(array,0,arrSplit_a,0,array.length/2);
            //второй массив
            System.arraycopy(array,array.length/2,arrSplit_b,0,array.length/2);

            Thread thread_1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < arrSplit_a.length; j++) {
                        arrSplit_a[j] = (float)(array[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                    }
                }
            });
        try {
            thread_1.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread_2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j < arrSplit_a.length; j++) {
                        arrSplit_a[j] = (float)(array[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                    }
                }
            });
        try {
            thread_2.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }


        //склеиваем обратно
            System.arraycopy(arrSplit_a,0,array,0,arrSplit_a.length);
            System.arraycopy(arrSplit_b,0,array,array.length/2,arrSplit_b.length);

        //6) В консоль выводим время работы:
        System.out.println("Время выполнения метода splitArray = " + (System.currentTimeMillis()-before) + " мс.");
    }




    public static void arrCalculation(float[] array){
        long before = 0;
        //2) Заполняем этот массив единицами:
        for(int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        //3) Засекаем время выполнения:
        before = System.currentTimeMillis();
        //4) Проходим по всему массиву и для каждой ячейки считаем новое
        //значение по формуле:
        for(int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        //5) Проверяется время окончания метода:
        //6) В консоль выводим время работы:
        System.out.println("Время выполнения метода arrCalculation = " + (System.currentTimeMillis()-before) + " мс.");
    }
}
