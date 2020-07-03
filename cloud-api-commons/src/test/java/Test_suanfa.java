import java.util.concurrent.atomic.AtomicStampedReference;

public class Test_suanfa {


    public static void main(String[] args){
        //二分查找
//        int []array = new int[]{1,2,3,4,5,6,7,89};
//        int i = Test_suanfa.binarySearch(array, 4);
//        System.out.println(i);

        //冒泡
//        int []array = new int[]{1,100,3,55,5,6,7,89};
//        int[] maopao = Test_suanfa.maopao(array);
//        for (int i = 0; i < maopao.length; i++) {
//            if(i== maopao.length-1){
//                System.out.print(maopao[i]);
//            }else {
//                System.out.print(maopao[i]+",");
//            }
//        }
        //插入排序
//        int []array = new int[]{1,100,3,55,5,6,7,89};
//        int[] charupaixu = Test_suanfa.charupaixu(array);
//                for (int i = 0; i < charupaixu.length; i++) {
//            if(i== charupaixu.length-1){
//                System.out.print(charupaixu[i]);
//            }else {
//                System.out.print(charupaixu[i]+",");
//            }
//        }
        //快速排序
        int []array = new int[]{1,100,3,55,5,6,7,89,3};
        int[] charupaixu = Test_suanfa.quick(array,0,7);
        for (int i = 0; i < charupaixu.length; i++) {
            if(i== charupaixu.length-1){
                System.out.print(charupaixu[i]);
            }else {
                System.out.print(charupaixu[i]+",");
            }
        }


    }



  //二分查找算法
    public static int binarySearch(int []array,int a){
        int xiao = 0;
        int zj;
        int da = array.length-1;
        while (xiao<=da){
            zj = (da-xiao)/2+xiao;
            if(array[zj]==a){
                return zj;
            }else if (array[zj]>a){
                da = zj-1;
            }else {
                xiao = zj+1;
            }
        }
        return -1;
    }

    //冒泡排序
    public static int [] maopao(int []array){
        for (int i = 0; i <array.length-1 ; i++) {
            for (int j = 0; j <array.length-1-i ; j++) {
                if (array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        return array;
    }
    //插入排序

    public static int [] charupaixu(int []array){
        for (int i = 1; i <array.length ; i++) {
              //定义需要插入的值
                int insertval = array[i];
                //定义前一个数的下标
                int index = i-1;
                //如果符合需要插入的值小于前一个值则进行向前连续比较直到比较到第一位数
                while (index>=0&&insertval<array[index]){
                    //将前一个值下标+1向右移动
                    array[index+1] = array[index];
                    index -- ;
                }

                array[index+1] = insertval;
        }
        return array;
    }


    public static  int [] quick(int [] arr,int low,int high){

        int start = low;

        int end = high;

        int key = arr[low];

        while (end >start){
            while (end<=start && arr[end]>= key){
                end --;
                if (arr[end]<=key){
                    int temp = arr[end];
                    arr[end] = arr[start];
                    arr[start] = temp;
                }
            }


            while (end>start && arr[start]<=key){
                start++;
                if(arr[start]>=key){
                    int temp = arr[start];
                    arr[start] = arr[end];
                    arr[end] = temp;
                }
            }
        }
        if(start>low){
            quick(arr,low,start-1);
        }

        if(end<high){
            quick(arr,end+1,high);
        }
        return arr;
    }




    public void  test(){
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,0);
    }



}
