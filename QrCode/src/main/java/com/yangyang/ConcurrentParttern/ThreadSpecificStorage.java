package com.yangyang.ConcurrentParttern;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.concurrent.*;

@SuppressWarnings("unchecked")
class ThreadSpecificSecureRandom{
    public static final ThreadSpecificSecureRandom INSTANCE = new ThreadSpecificSecureRandom();
    private ThreadSpecificSecureRandom(){}
    private static final ThreadLocal<SecureRandom> SECURE_RANDOM = new ThreadLocal<SecureRandom>(){
        @Override
        protected SecureRandom initialValue() {
            SecureRandom srnd;
            try {
                srnd = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                srnd = new SecureRandom();
            }
            return srnd;
        }
    };
    public int nextInt(int upperBond){
        return SECURE_RANDOM.get().nextInt(upperBond);
    }
    public void setSeed(long seed){SECURE_RANDOM.get().setSeed(seed);}

}
public class ThreadSpecificStorage {

    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(1,
            Runtime.getRuntime().availableProcessors(), 60, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r,"VerfCodeSender");
            t.setDaemon(true);
            return t;
        }
    });
    private void sendSms(String msisdn,String verificationCode){
        System.out.println("Send verficition code: "+verificationCode+" to "+msisdn);
    }
    public void sendVerficationSms(final String msisdn){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                int verficationCode = ThreadSpecificSecureRandom.INSTANCE.nextInt(999999);
                DecimalFormat df = new DecimalFormat("000000");
                sendSms(msisdn,df.format(verficationCode));
            }
        };
        EXECUTOR.submit(task);
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadSpecificStorage ss = new ThreadSpecificStorage();
        ss.sendVerficationSms("18912345678");
        ss.sendVerficationSms("19012345678");
        ss.sendVerficationSms("19112345678");

        Thread.sleep(10000);

    }
}
