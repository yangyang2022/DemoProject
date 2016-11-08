enum PayRollDay2{

        MONDAY(PayType.WEEKDAY),TUESDAY(PayType.WEEKDAY),THUSDAY(PayType.WEEKDAY),
        SUNDAY(PayType.WEEKEND)
        ;

        private final PayType payType;

        PayRollDay2(PayType payType) {
            this.payType = payType;
        }
        double pay(double hoursworkd,double payRate){
            return payType.pay(hoursworkd, payRate);
        }
        ;
        private enum PayType{
            WEEKDAY{
                @Override
                double overtimePay(double hrs, double payrate) {
                    return hrs < HOUSR_PER_SHIFT ? 0 : (hrs - HOUSR_PER_SHIFT)*payrate/2;
                }
            },
            WEEKEND{
                @Override
                double overtimePay(double hrs, double payrate) {
                    return hrs * payrate/2;
                }
            }
            ;
            private static final int HOUSR_PER_SHIFT = 8;
            abstract double overtimePay(double hrs,double payrate);
            double pay(double hourswork,double payRate){
                double basePay = hourswork * payRate;
                return basePay + overtimePay(hourswork, payRate);
            }
        }
    }
    enum PayrollDay{
        MONDAY,TUESDAY,WEDENSDAY,THURSDAY,FRIDAY,STAURDAY,SUNDAY
        ;
        private static final int HOURSE_PER_SHIFT = 8;
        double pay(double hoursWorked,double payRate){
            double basePay = hoursWorked * payRate;
            double overtimePay ;
            switch (this){
                case STAURDAY:case SUNDAY:
                    overtimePay = hoursWorked * payRate/2;break;
                default:
                    overtimePay = hoursWorked <= HOURSE_PER_SHIFT ? 0 :
                            (hoursWorked-HOURSE_PER_SHIFT)*payRate/2;
                    break;
            }
            return basePay+overtimePay;
        }
    }