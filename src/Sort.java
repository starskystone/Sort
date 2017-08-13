/**
 * 堆排序、快速排序、希尔排序、直接选择排序不是稳定的排序算法
 * 基数排序、冒泡排序、直接插入排序、折半插入排序、归并排序是稳定的排序算法
 */

public class Sort {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int[] arr= {4,53,2,32,543,23,6,549,8,546};
        Sort sort = new Sort();
        sort.insertSort(arr);
        System.out.println("简单插入排序：");
        sort.show(arr);

        int[] brr = {32,4,23,1,5,245,6,86,765,654,534};
        Sort sort1 = new Sort();
        sort1.mergeSort(brr,0,brr.length-1);
        System.out.println("归并排序:");
        sort1.show(brr);

        int[] crr = {43263,534,123,654,7,745,234,11,326,2352,4356};
        Sort sort2 = new Sort();
        sort2.quickSort(crr,0,crr.length-1);
        System.out.println("选择排序：");
        sort2.show(crr);

        int []drr = {234,53,74,867,5243,576,543,423,47};
        Sort sort3 = new Sort();
        sort3.heapSort(drr);
        System.out.println("堆排序：");
        sort3.show(drr);
    }

    /**
     * 显示
     * @param arr
     */
    public void show(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    /**
     * 简单插入排序 时间复杂度为O(n^2)   稳定的排序算法
     * @param arr
     */
    public void insertSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int tmp = arr[i];
            int k;
            for(k = i-1; k>=0 && arr[k]>tmp; k--){
                arr[k+1] = arr[k];
            }
            arr[k+1] = tmp;
        }
    }

    /**
     * 冒泡排序 时间复杂度O(n^2)     稳定的排序算法
     * @param arr
     */
    public void bubbleSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j < arr.length-i; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序算法改进，加一个标志位 时间复杂度最好的情况O(n)最坏的情况下时间复杂度为O(n^2)
     * @param arr
     */
    public void bubbleImproveSort(int[] arr){
        boolean tip = true;
        for(int i = 1; i < arr.length && tip; i++){
            tip = false;
            for(int j = 0; j < arr.length-i; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    tip = true;
                }
            }
        }
    }

    /**
     * 归并排序   事件复杂度O(nlogn) 稳定的排序算法
     * @param arr
     * @param low
     * @param heigh
     */
    public void mergeSort(int[] arr, int low, int heigh){
        int middle = (low + heigh)/2;

        if(low < heigh) {
            mergeSort(arr, low, middle);
            mergeSort(arr, middle+1, heigh);
            merge(arr, low, middle, heigh);
        }
    }

    /**
     * 归并排序的合并算法
     * @param arr
     * @param low
     * @param middle
     * @param heigh
     */

    public void merge(int []arr, int low ,int middle, int heigh){
        int[] temp = new int[heigh-low+1];

        int i = low;
        int j = middle+1;
        int k = 0;

        while (i <= middle && j <= heigh){
            if(arr[i] < arr[j]){
                temp[k++] =  arr[i++];
            }else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= middle){
            temp[k++] = arr[i++];
        }

        while (j <= heigh){
            temp[k++] = arr[j++];
        }

        for (int m = 0; m < temp.length; m++){
            arr[m+low] = temp[m];
        }
    }


    /**
     * 快速排序     时间复杂度为O(logn) 是不稳定的排序
     * @param arr
     * @param low
     * @param high
     */
    public void quickSort(int[] arr, int low, int high){

        if(low < high){
            int middle = getMiddle(arr,low,high);
            quickSort(arr, low, middle-1);
            quickSort(arr,middle+1, high);
        }
    }

    /**
     * 获取中轴在排序后的位置
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public int getMiddle(int[] arr,int low, int high){
        int temp = arr[low];
        while(low < high){
            while (low < high && arr[high] > temp){
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] < temp){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }


    /**
     * 堆排序  时间复杂度为O(nlogn) 空间复杂度比归并排序低
     * @param arr
     */
    public void heapSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            createHeap(arr,arr.length-1-i);
            swap(arr,0,arr.length-1-i);
        }
    }

    public void createHeap(int[] arr, int last){
        for(int i = (last-1)/2; i>= 0; i--){
            int parent = i;
            while ((parent*2+1) <= last){
                int bigger = parent*2+1;    //先把左子节点赋值个变量
                if(bigger < last){          //说明存在右子节点
                    if(arr[bigger+1] > arr[bigger]){
                        bigger = bigger+1;
                    }
                }
                if(arr[bigger] > arr[parent]){
                    swap(arr,bigger,parent);
                    parent = bigger;
                }else {
                    break;
                }

            }
        }
    }


    public void swap(int[]arr, int i, int j){
        if(i == j){
            return;
        }else {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
