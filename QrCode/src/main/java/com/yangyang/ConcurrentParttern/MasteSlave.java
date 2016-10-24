package com.yangyang.ConcurrentParttern;

public class MasteSlave {

    public static void main(String[] args) {

        //接口日志所在的目录
        String logBaseDir = args[0];

        //忽略的操作名列表
        String excludeOperationNames =  "";

        //制定要统计在内的操作名列表
        String includeOperationNames = "*";

        //制定要统计在内的目标设备名
        String destinationSysName = "*";

        int argc = args.length;
        if(argc > 2) excludeOperationNames = args[1];
        if(argc > 3) excludeOperationNames = args[2];
        if(argc > 4) destinationSysName = args[3];

        //TODO
    }
    //master
    private static class Master{
        private final String logBaseDir;
        private final String excludeOperationNames;
        private final String includeOperationNames;
        private final String destinationSysName;

        public Master(String logBaseDir, String excludeOperationNames, String includeOperationNames, String destinationSysName) {
            this.logBaseDir = logBaseDir;
            this.excludeOperationNames = excludeOperationNames;
            this.includeOperationNames = includeOperationNames;
            this.destinationSysName = destinationSysName;
        }

        private static final int NUMBER_OF_FILES_FOR_EACH_DISPATCH=5;
        private static final int WORK_COUNT = Runtime.getRuntime().availableProcessors();



    }


}
