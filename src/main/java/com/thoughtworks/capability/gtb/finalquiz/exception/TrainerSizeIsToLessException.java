package com.thoughtworks.capability.gtb.finalquiz.exception;

// GTB：- 业务异常应该继承自RuntimeException
public class TrainerSizeIsToLessException extends Exception {
    public TrainerSizeIsToLessException() {
        super("讲师数量不足");
    }
}
