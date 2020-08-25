package com.kafka.stream;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class LogProcessor implements Processor<byte[], byte[]> {

    ProcessorContext content;

    @Override
    public void init(ProcessorContext processorContext) {
        content = processorContext;
    }

    @Override
    public void process(byte[] bytes, byte[] bytes2) {
        String line = new String(bytes2);
        line = line.replaceAll(">>>", "");
        bytes2 = line.getBytes();
        content.forward(bytes, bytes2);
    }

    @Override
    public void punctuate(long l) {

    }

    @Override
    public void close() {

    }
}
