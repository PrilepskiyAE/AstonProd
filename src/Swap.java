public class Swap {

    /**
     * обмен двух значений местами в массиве
     * @param array массив
     * @param first первое число на обмен
     * @param second второе число на обмен
     * @return возвращает новый массив
     */
    public static int[] swap(int[] array,int first,int second){
        int temporary=array[first];
        array[first]=array[second];
        array[second]=temporary;
        return  array;
    }
}
