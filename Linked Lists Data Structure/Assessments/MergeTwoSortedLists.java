class ListNode {
    int value;
    ListNode next;

    public ListNode() {
        this(0);
    }

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public void add(int value) {
        ListNode newNode = new ListNode(value);

        ListNode current = this;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }
}

public class MergeTwoSortedLists {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Task 1: Initialize ptr1, ptr2, and dummy node.
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        // Task 2: Compare elements pointed to by ptr1 and ptr2.
        while (ptr1 != null && ptr2 != null) {
            // Task 3: Append the smaller element to the merged list.
            if (ptr1.value < ptr2.value) {
                current.next = ptr1;
                ptr1 = ptr1.next;
            } else {
                current.next = ptr2;
                ptr2 = ptr2.next;
            }

            current = current.next;
        }

        // Task 4: Attach the remaining elements.
        if (ptr1 != null) {
            current.next = ptr1;
        } else if (ptr2 != null) {
            current.next = ptr2;
        }

        // Task 5: Return the merged list.
        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Example usage:
        // Create two sorted linked lists using the 'add' method
        ListNode l1 = new ListNode(1);
        l1.add(3);
        l1.add(5);

        ListNode l2 = new ListNode(2);
        l2.add(4);
        l2.add(6);

        // Print the original linked lists
        System.out.println("Original List 1: ");
        printList(l1);

        System.out.println("Original List 2: ");
        printList(l2);

        // Merge the two sorted lists
        ListNode mergedList = mergeTwoLists(l1, l2);

        // Print the merged list
        System.out.println("Merged List: ");
        printList(mergedList);
    }
}
