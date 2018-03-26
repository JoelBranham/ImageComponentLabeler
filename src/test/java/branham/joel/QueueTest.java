package branham.joel;

import org.junit.*;
import static org.junit.Assert.*;
import branham.joel.Queue;

public class QueueTest{
	
	private Queue<Integer> q;
	
	@Before
	public void setup(){
		q = new Queue<Integer>();
	}
	
	@Test
	public void checkInitialSize(){
		assertEquals(0, q.size());
	}
	
	@Test
	public void checkEnqueueAndDequeueOfOneInteger(){
		q.enqueue(2);
		assertEquals(1, q.size());
		assertEquals(2, (int) q.dequeue());
		assertEquals(0, q.size());
	}
	
	@Test
	public void checkQueueOfIntegersInCorrectOrder(){
		int[] arr = new int[]{1, 3, 5, 6, 9};
		for (int n: arr){
			q.enqueue(n);
		}
		for (int n: arr){
			assertEquals(n, (int) q.dequeue());
		}
	}

}