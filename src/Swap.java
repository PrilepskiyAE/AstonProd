
public class Swap {

    /**
     * обмен значений местами в массиве
     * @param array массив
     * @param first индекс первого значения
     * @param second индекс второго значения
     * @return возвращает новый массив
     */
    public static int[] swap(int[] array,int first,int second){
        int temporary=array[first];
        array[first]=array[second];
        array[second]=temporary;
        return  array;
    }
}
