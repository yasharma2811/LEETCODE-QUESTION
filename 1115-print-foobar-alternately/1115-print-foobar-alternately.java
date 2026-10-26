import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
    private Semaphore sFoo = new Semaphore(1);
    private Semaphore sBar = new Semaphore(0);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            sFoo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            sBar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            sBar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            sFoo.release();
        }
    }
}