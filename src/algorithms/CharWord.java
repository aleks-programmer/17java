package algorithms;

/**
 * Created by User on 23.04.2015.
 */
public class CharWord {
    public static boolean isWordValid(char[] arr1, char[] arr2) {
        int matches = 0;
        int arr1Count = 0;
        int arr2Count = 0;
        for(int i = 0; i < arr1.length; i++)
            if(arr1[i] != '\0')
                arr1Count++;
        for(int i = 0; i < arr2.length; i++)
            if(arr2[i] != '\0')
                arr2Count++;
        for(int i = 0; i < arr1Count; i++) {
            for (int j = i; j < arr2Count; j++)
                if (arr1[i] == arr2[j]) {
                    char temp = arr2[i];
                    arr2[i] = arr2[j];
                    arr2[j] = temp;
                    matches++;
                    break;
                }
        }
        if(matches == arr1Count)
            return true;
        return false;
    }
}
