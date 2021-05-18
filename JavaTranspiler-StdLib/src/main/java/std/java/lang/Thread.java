package std.java.lang;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import eu.bebendorf.transpiler.interop.LuaImpl;
import eu.bebendorf.transpiler.interop.PHPImpl;

public class Thread implements Runnable {

    private volatile java.lang.String name;
    private int priority;
    private boolean daemon;
    private java.lang.Runnable target;
    private java.lang.ThreadGroup group;
    private java.lang.ClassLoader contextClassLoader;
    private static int threadInitNumber;
    private static synchronized int nextThreadNum() {
        return threadInitNumber++;
    }
    private final long tid;
    private static long threadSeqNumber;
    private static synchronized long nextThreadID() {
        return ++threadSeqNumber;
    }
    private volatile int threadStatus;
    public static final int MIN_PRIORITY = 1;
    public static final int NORM_PRIORITY = 5;
    public static final int MAX_PRIORITY = 10;

    @LuaImpl("return t")
    @PHPImpl("return $t;")
    public static native java.lang.Thread currentThread();

    @LuaImpl("-- yield")
    @PHPImpl("// yield")
    public static native void yield();

    @LuaImpl("-- sleep")
    @PHPImpl("// sleep")
    public static native void sleep(long millis) throws InterruptedException;

    public static void sleep(long millis, int nanos) throws InterruptedException {
        if (millis < 0)
            throw new IllegalArgumentException("timeout value is negative");
        if (nanos < 0 || nanos > 999999)
            throw new IllegalArgumentException("nanosecond timeout value out of range");
        if (nanos > 0 && millis < Long.MAX_VALUE)
            millis++;
        sleep(millis);
    }

    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public Thread() {
        this(null, null, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(java.lang.Runnable target) {
        this(null, target, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(java.lang.ThreadGroup group, java.lang.Runnable target) {
        this(group, target, "Thread-" + nextThreadNum(), 0);
    }

    public Thread(java.lang.String name) {
        this(null, null, name, 0);
    }

    public Thread(java.lang.ThreadGroup group, java.lang.String name) {
        this(group, null, name, 0);
    }

    public Thread(java.lang.Runnable target, java.lang.String name) {
        this(null, target, name, 0);
    }

    public Thread(java.lang.ThreadGroup g, java.lang.Runnable target, java.lang.String name) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = name;
        java.lang.Thread parent = currentThread();
        if (g == null) {
            g = parent.getThreadGroup();
        }
        this.group = g;
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        this.contextClassLoader = parent.getContextClassLoader();
        this.target = target;
        this.tid = nextThreadID();
    }

    public Thread(java.lang.ThreadGroup group, java.lang.Runnable target, java.lang.String name, long stackSize) {
        this(group, target, name);
    }

    public synchronized void start() {
        if (threadStatus != 0)
            throw new IllegalThreadStateException();
    }

    public void run() {
        if (target != null) {
            target.run();
        }
    }

    private void exit() {

    }

    public final void stop() {

    }

    public void interrupt() {

    }

    public static boolean interrupted() {
        return false;
    }

    public boolean isInterrupted() {
        return false;
    }

    public final boolean isAlive() {
        return true;
    }

    public final void suspend() {

    }

    public final void resume() {

    }

    public final void setPriority(int newPriority) {
        java.lang.ThreadGroup g;
        if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
            throw new IllegalArgumentException();
        }
        if((g = getThreadGroup()) != null) {
            if (newPriority > g.getMaxPriority()) {
                newPriority = g.getMaxPriority();
            }
        }
        priority = newPriority;
    }

    public final int getPriority() {
        return priority;
    }

    public final synchronized void setName(java.lang.String name) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = name;
    }

    public final java.lang.String getName() {
        return name;
    }

    public final java.lang.ThreadGroup getThreadGroup() {
        return group;
    }

    public static int activeCount() {
        return currentThread().getThreadGroup().activeCount();
    }

    public int countStackFrames() {
        return 0;
    }

    public final synchronized void join(final long millis) throws InterruptedException {
        if (millis > 0) {
            if (isAlive()) {
                final long startTime = java.lang.System.nanoTime();
                long delay = millis;
                do {
                    wait(delay);
                } while (isAlive() && (delay = millis -
                        TimeUnit.NANOSECONDS.toMillis(java.lang.System.nanoTime() - startTime)) > 0);
            }
        } else if (millis == 0) {
            while (isAlive()) {
                wait(0);
            }
        } else {
            throw new IllegalArgumentException("timeout value is negative");
        }
    }

    public final synchronized void join(long millis, int nanos) throws InterruptedException {
        if (millis < 0)
            throw new IllegalArgumentException("timeout value is negative");
        if (nanos < 0 || nanos > 999999)
            throw new IllegalArgumentException("nanosecond timeout value out of range");
        if (nanos > 0 && millis < Long.MAX_VALUE)
            millis++;
        join(millis);
    }

    public final void join() throws InterruptedException {
        join(0);
    }

    public static void dumpStack() {
        new Exception("Stack trace").printStackTrace();
    }

    public final void setDaemon(boolean on) {
        if (isAlive())
            throw new IllegalThreadStateException();
        daemon = on;
    }

    public final boolean isDaemon() {
        return daemon;
    }

    public final void checkAccess() {

    }

    public java.lang.String toString() {
        java.lang.ThreadGroup group = getThreadGroup();
        if (group != null) {
            return "Thread[" + getName() + "," + getPriority() + "," +
                    group.getName() + "]";
        } else {
            return "Thread[" + getName() + "," + getPriority() + "," +
                    "" + "]";
        }
    }

    public ClassLoader getContextClassLoader() {
        if (contextClassLoader == null)
            return null;
        return contextClassLoader;
    }

    public void setContextClassLoader(ClassLoader cl) {
        contextClassLoader = cl;
    }

    public static boolean holdsLock(Object obj) {
        return false;
    }

    public java.lang.StackTraceElement[] getStackTrace() {
        return new java.lang.StackTraceElement[0];
    }

    public static Map<java.lang.Thread, java.lang.StackTraceElement[]> getAllStackTraces() {
        java.lang.Thread[] threads = getThreads();
        java.lang.StackTraceElement[][] traces = dumpThreads(threads);
        Map<java.lang.Thread, java.lang.StackTraceElement[]> m = new HashMap<>(threads.length);
        for (int i = 0; i < threads.length; i++) {
            java.lang.StackTraceElement[] stackTrace = traces[i];
            if (stackTrace != null) {
                m.put(threads[i], stackTrace);
            }
        }
        return m;
    }

    private static java.lang.StackTraceElement[][] dumpThreads(java.lang.Thread[] threads) {
        java.lang.StackTraceElement[][] traces = new java.lang.StackTraceElement[threads.length][];
        for(int i=0; i<threads.length; i++)
            traces[i] = threads[i].getStackTrace();
        return traces;
    }

    private static native java.lang.Thread[] getThreads();

    public long getId() {
        return tid;
    }

    public enum State {
        NEW,
        RUNNABLE,
        BLOCKED,
        WAITING,
        TIMED_WAITING,
        TERMINATED
    }

    public State getState() {
        return State.WAITING;
    }

    public interface UncaughtExceptionHandler {
        void uncaughtException(Thread t, Throwable e);
    }

    private volatile java.lang.Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private static volatile java.lang.Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    public static void setDefaultUncaughtExceptionHandler(java.lang.Thread.UncaughtExceptionHandler eh) {
        defaultUncaughtExceptionHandler = eh;
    }

    public static java.lang.Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler(){
        return defaultUncaughtExceptionHandler;
    }

    public java.lang.Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return uncaughtExceptionHandler != null ? uncaughtExceptionHandler : group;
    }

    public void setUncaughtExceptionHandler(java.lang.Thread.UncaughtExceptionHandler eh) {
        checkAccess();
        uncaughtExceptionHandler = eh;
    }

    static class WeakClassKey extends WeakReference<Class<?>> {
        private final int hash;

        WeakClassKey(Class<?> cl, ReferenceQueue<Class<?>> refQueue) {
            super(cl, refQueue);
            hash = java.lang.System.identityHashCode(cl);
        }

        public int hashCode() {
            return hash;
        }

        public boolean equals(Object obj) {
            if (obj == this)
                return true;

            if (obj instanceof WeakClassKey) {
                Object referent = get();
                return (referent != null) &&
                        (referent == ((WeakClassKey) obj).get());
            } else {
                return false;
            }
        }
    }
}
