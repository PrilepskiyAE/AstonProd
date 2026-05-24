

public class BubblesSort {
    private int[] _array;
    private int _length;

    public BubblesSort(int[] array) {
        _array=array;
        _length=array.length;
    }

    /**
     * сортировка пузырьками
     * @return возвращаем отсортированный массив
     */
    public int[] sort(){
        for(int i=0;i<_length-1;i++){
            for (int j=0;j<_length-i-1;j++){
                if(_array[j]>_array[j+1]){
                    Swap.swap(_array,j,j+1);
                }
            }
        }
        return _array;
    }
}
