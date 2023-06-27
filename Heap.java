/* The heap must satisfy the heap property. 
Every parent node in the heap must have 2^x children.
The value of x must be a parameter of the heap’s constructor. 
The heap must implement an insert method.
The heap must implement a pop max method.
The heap must be implemented in Java.
The heap must be performant.
You must use a more descriptive variable name than x in your implementation.
Think carefully about how you implement each method, and manage the underlying data. Performance is critical, so keep cycles and memory usage to a minimum. Be sure to test your heap with very small and very large values of x. As always, keep a weather eye out for sneaky edge cases. */

import java.util.Random;

public class Heap {

    int[]powerOfTwoMaxHeap;
    int heapChildren;
    int capacity;

    /* Initializes Heap with specified size and number of Child*/
    Heap(int heapSize,int noOfChild){
        powerOfTwoMaxHeap = new int[heapSize];
        this.heapChildren = (int) Math.pow(2,noOfChild);
        this.capacity=heapSize;
    }

    /* Swap two values specified at the provided index in the Heap Structure*/
    public void swap(int i,int j,int[]powerOfTwoMaxHeap){
        int temp = powerOfTwoMaxHeap[i];
        powerOfTwoMaxHeap[i] = powerOfTwoMaxHeap[j];
        powerOfTwoMaxHeap[j] =temp;
    }

    /* Compare the child node to parent node for preserving max heap property*/
    public void bubbleUp(int size){
        int parentIndex = (size-1)/this.heapChildren;
        if (powerOfTwoMaxHeap[size] > powerOfTwoMaxHeap[parentIndex]) {
                swap(size, parentIndex, powerOfTwoMaxHeap);
                bubbleUp(parentIndex);
        }
    }

    /* Compare the parent node to child node for preserving max heap property*/
    public void bubbleDown(int index,int size){
        int largestChildIndex = index;
        for(int j=1;j<=heapChildren;j++){
            int childIndex = heapChildren*index+j;
            if(childIndex<size && powerOfTwoMaxHeap[childIndex]>powerOfTwoMaxHeap[largestChildIndex]){
                largestChildIndex=childIndex;
            }
        }

        if(largestChildIndex!=index) {
            swap(index, largestChildIndex, powerOfTwoMaxHeap);
            bubbleDown(largestChildIndex, size);
        }
    }

    /* Insertion in the heap*/
    public int insert(int value,int size){
        if(size<this.capacity) {
            powerOfTwoMaxHeap[size] = value;
            bubbleUp(size);
            return size + 1;
        }else return -1;
    }

  /* Pop out the max element */
    public int popMax(int size){
        // replacing root with the last node
        if(size>0) {
            System.out.println("Popped out element is - "+powerOfTwoMaxHeap[0]);
            powerOfTwoMaxHeap[0] = powerOfTwoMaxHeap[size - 1];
            size -= 1;
            bubbleDown(0, size);
            return size;
        }
        else return -1;
    }

    /* Display the current Heap*/
    public void display(int size){
        for(int i=0;i<size;i++){
            System.out.print(powerOfTwoMaxHeap[i]+" ");
        }
        System.out.println();
    }

    /* Random number is generated for test*/
    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(1000);
    }

    public static void main(String[] args){
        int size = 200; //size of heap can be modified
        int child = 4; // number of child of a parent (2^child)
        Heap powerOfTwoMaxHeap = new Heap(size,child);

        int n=0;
        for(int i=0;i<100;i++){
            if(n==-1)return;
            n=powerOfTwoMaxHeap.insert(powerOfTwoMaxHeap.randomNumber(), n);
        }
        powerOfTwoMaxHeap.display(n);
        powerOfTwoMaxHeap.popMax(n);
        System.out.println();
        powerOfTwoMaxHeap.display(n);


    }
}
