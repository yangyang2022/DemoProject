import org.junit.Test;

public class Demo {

    private String getStr(String str){
        int count = 1;
        str+=" ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length()-1;i++) {
            if(str.charAt(i) == str.charAt(i+1)){
                count++;
            }else {
                if(count==1){
                    sb.append(str.charAt(i));
                }else {
                    sb.append(str.charAt(i)).append(count);
                    count = 1;
                }
            }
        }
        System.out.println(sb.toString());
        return "";
    }
    private void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private void swapInt(int[] arr){
        for (int low = 0,high = arr.length-1; low<high;) {
            while ((arr[low]+1) %2 == 0 ) low++;
            while (arr[high] %2 == 0) high--;
            if(low < high) swap(arr,low,high);
        }
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i]+" ,");
        }
        System.out.println("");
    }
    @Test
    public void testDemo() {
        //getStr("aaabccddf");
        int arr[] = {2,2,2,2};
        swapInt(arr);

        //String name = "aaabbcdde";
        //System.out.println(Stream.of(name.split("")).collect(Collectors.groupingBy(e->e,Collectors.counting())));

    }
}
