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
}

public class LinkedListPartition {

    public static ListNode partitionLinkedList(ListNode head, int x) {
        // Task 1: Initialize dummy1 and dummy2 nodes
        ListNode dummy1 = new ListNode();
        ListNode dummy2 = new ListNode();

        ListNode current1 = dummy1;
        ListNode current2 = dummy2;

        // Task 2: Traverse the original linked list
        ListNode current = head;
        while (current != null) {
            // Task 3: Is the value of 'current' less than 'x'?
            if (current.value < x) {
                current1.next = current;
                current1 = current1.next;
            } else {
                current2.next = current;
                current2 = current2.next;
            }

            current = current.next;
        }

        // Task 4: Link the tail of the first partition to the head of the second partition
        current1.next = dummy2.next;
        current2.next = null;

        // Task 5: Return the modified linked list
        return dummy1.next;
    }


    public static ListNode add(ListNode head, int value) {
        ListNode newNode = new ListNode(value);

        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        return head;
    }

    public static void main(String[] args) {
        // Example usage:
        // Create a sample linked list using the 'add' method
        ListNode head = null;
        head = add(head, 1);
        head = add(head, 4);
        head = add(head, 3);
        head = add(head, 2);
        head = add(head, 5);
        head = add(head, 2);

        int x = 3;

        // Partition the linked list based on the value of x
        ListNode result = partitionLinkedList(head, x);

        // Print the modified linked list
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}
