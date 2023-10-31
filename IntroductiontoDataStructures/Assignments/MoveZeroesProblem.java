import java.util.Arrays;

public class MoveZeroesProblem {
    public void moveZeroes(int[] nums) {
        int i = 0; // Initialize the pointer i
        int j = 0; // Initialize the pointer j

        // Task 2: Iterate through the array using the `j` pointer
        while (j < nums.length) {
            // Task 3: Check if the element at index `j` is non-zero
            if (nums[j] != 0) {
                // Task 4: If the element is non-zero, swap it with the element at index `i` and increment both `i` and `j`
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            j++;
        }

        // Task 6: Fill the remaining elements from index `i` to the end of the array with zeros
        while (i < nums.length) {
            nums[i] = 0;
            i++;
        }
    }

    public static void main(String[] args) {
        MoveZeroesProblem solution = new MoveZeroesProblem();
        int[] nums = {0, 1, 0, 3, 12};

        System.out.println("Original Array: " + Arrays.toString(nums));
        solution.moveZeroes(nums);

        // Task 7: Return the Modified Array
        System.out.println("Modified Array: " + Arrays.toString(nums));
    }
}
