public class MFU {
    Object printLock = new Object();
    Object scanLock = new Object();

    public void print(String doc) {
        synchronized (printLock) {
            System.out.println("Начало печати " + doc);
            for (int i = 0; i < 10; i++) {
                System.out.println(doc + " pr " + i);
            }
            System.out.println("Конец печати " + doc);
        }
    }

    public void scan(String doc) {
        synchronized (scanLock) {
            System.out.println("Начало сканирования " + doc);
            for (int i = 0; i < 10; i++) {
                System.out.println(doc + " sc " + i);
            }
            System.out.println("Конец сканирования " + doc);
        }
    }
    public void copy(String doc) {
        synchronized (scanLock) {
            synchronized (printLock) {
                System.out.println("Начало копирования " + doc);
                for (int i = 0; i < 10; i++) {
                    System.out.println(doc + " sc " + i);
                }
                System.out.println("Конец сканирования " + doc);

                System.out.println("Начало печати " + doc);
                for (int i = 0; i < 10; i++) {
                    System.out.println(doc + " pr " + i);
                }
                System.out.println("Конец копирования " + doc);
            }
        }
    }

    public static void main(String[] args) {
        final MFU mfu = new MFU();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 1");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 2");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 3");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.copy("Doc 4");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 5");
            }
        }).start();
    }
}
