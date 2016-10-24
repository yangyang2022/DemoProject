package com.yangyang.ConcurrentParttern;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

interface Channel<P> {
    P take() throws InterruptedException;
    void put(P product) throws InterruptedException;
}
class BlockingQueueChannel<P> implements Channel<P>{
    private final BlockingQueue<P> queue;

    public BlockingQueueChannel(BlockingQueue<P> queue) {
        this.queue = queue;
    }

    @Override
    public P take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public void put(P product) throws InterruptedException {
        queue.put(product);
    }
}

class AttachmentProcessor{
    private final String ATTACHMENT_STRORE_BASE_DIR = "D:\\code\\demo";
    private final Channel<File> channel = new BlockingQueueChannel<>(new ArrayBlockingQueue<File>(200));
}

public class ConsumerProducor {

    public static void main(String[] args) {
    }
}
