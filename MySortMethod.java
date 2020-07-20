/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysortmethod;

import java.util.Arrays;

/**
 *
 * @author PC-1
 */
public class MySortMethod {
    
    /**
     * Method that will generate a randomized sample of data based values passed as parameters
     * @param minValue The smallest value that could exist in the sample.
     * @param maxValue the largest value that could exist in the sample.
     * @param size The desired size of the sample.
     * @return 
     */
    public static int[] generateData(int minValue, int maxValue, int size)
    {
        int min = minValue;
        int max = maxValue;
        int[] sample = new int[size];
        
        for(int i=0; i<size; i++)
        {
            int newData = (int)(Math.floor((Math.random()*((max-min)+1))+min));
            sample[i] = newData;
        }
        return sample;
    }
    
    /**
     * The algorithm will take the highest values found in newArray and add them to myArray.
     * The algorithm will then call itself recursively to continue this process until myArray is a sorted copy of the original array passed as newArray
     * @param newArray Array that will represent the original as the recursion occurs, it will  be shrinking.
     * @param myArray Array that will begin as an empty array and hold sorted values of newArray as recursion occurs
     * @param stopSize Holds the length of the original array and will be the variable that determines when recursion should stop
     * @return 
     */
    public static int[] recursiveSort(int[] newArray, int[] myArray, int stopSize)
    {
        
        int maxValue = 0;           // initial max Value, used to find the highiest value in a given aray
        int maxValueCount = 0;      // initial frequency of maxValue.
        int size = newArray.length; // variable to hold size of newArray to reference later on.
        
        System.out.println("");
        System.out.println("Arrays before method begins");
        System.out.println(Arrays.toString(newArray));
        System.out.println(Arrays.toString(myArray));
        
        
        if(myArray.length != stopSize)
        {
            //Find max value.
            for(int i = 0; i < size; i++)
            {
                if(newArray[i]>maxValue)
                {
                    maxValue = newArray[i];
                }
            }

            //Find the number of occurences of max value
            for(int i = 0; i < size; i++)
            {
                if(newArray[i]==maxValue)
                {
                    maxValueCount++;
                }
            }
            
            System.out.println("Arrays after method");
            System.out.println("Max Value = " + maxValue + " Occurences: " + maxValueCount);

            //THE FOLLOWING BLOCK OF CODE IS TO OVERWRITE MYARRAY WITH THE ARRAY WE WANT TO PASS TO THE NEXT RECURSIVE CALL.
            //instantiate a new temporary array, and add the values from myArray, and the new max values to it.
            //temp array size will be the size of myArray plus the value of maxValueCount.
            //this ensures that tempArray will be the right size to hold the origianl values from MYARRAY as well as the newly found max values in NEWARRAY
            int[] tempArray = new int[(myArray.length + maxValueCount)]; 
            int counter1 = 0;
            int counter2 = 0;
            for(int i = 0; i < tempArray.length; i++)
            {
                //add the maxValues found in newArray to tempArray first
                //this values should be smaller than any found in myArray.
                if(counter1==myArray.length)
                {
                    if(counter2!=maxValueCount)
                    {
                        tempArray[i] = maxValue;
                        counter2++;
                    }
                }
                //add the values from myArray to tempArray
                if(counter1!=myArray.length)
                {
                    tempArray[i] = myArray[counter1];
                    counter1++;
                }
            }
            //overwrite the reference for myArray with tempArray, that way the old array can be garbage collected
            //and myArray can be passed to the next recruseive call
            myArray = tempArray;

            //The following code block is resize newArray so that it has the maxValues found removed from it.
            //create another temporary array called newNewArray with the max values found in newArray removed from it.
            int[] newNewArray = new int[size - maxValueCount];

            int j = 0;      //Initialize a counter
            //add all the values in newArray that are less than maxValue to newNewArray.
            for(int i : newArray)
            {
                if(i != maxValue)
                {
                    newNewArray[j] = i;
                    j++;
                }
            }
            //reference newArray to newNewArray to allow the old values of newArray to be garbage collected.
            newArray = newNewArray;

            System.out.println(Arrays.toString(newArray));
            System.out.println(Arrays.toString(myArray));

            //This is the recursive call.
            //All the work previously was done to set up variables to pass to the recursive call indefinitely, until new
            myArray = recursiveSort(newArray, myArray, 10);
        }
        return myArray;
    }
    
    /**
     * MY MAIN METHOD!!!!!
     * BIG BOLD LETTERS TO MAKE THIS MORE NOTICABLE
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] sample1 = generateData(0, 10, 10);
        System.out.print("Original: " + sample1.toString());
        for(int i : sample1)
        {
            System.out.print(i + ", ");
        }
        
        //The final method will accept only a single array parameter, since we can find these other variables ourselves easily
        //for now well just declare these in the main method.
        int[] empty = new int[0];
        int[] mySortedArray = recursiveSort(sample1, empty, 10);
        System.out.println("Sorted: ");
        System.out.println(Arrays.toString(mySortedArray));
    }
    
}
