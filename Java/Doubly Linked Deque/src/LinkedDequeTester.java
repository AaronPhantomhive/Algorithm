/**
 * @author dix
 * 
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinkedDequeTester
{
	@Test
	public void testConstructor()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		assertEquals(true, myDeque.isEmpty());
	}
	
	@Test
	public void testAddToFront()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		assertEquals(true, myDeque.isEmpty());
		myDeque.addToFront("Joe");
		assertEquals(false, myDeque.isEmpty());
		assertEquals("Joe", myDeque.getFront());
	}
	
	@Test
	public void testAddToBack()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		assertEquals(true, myDeque.isEmpty());
		myDeque.addToBack("Joe");
		assertEquals(false, myDeque.isEmpty());
		assertEquals("Joe", myDeque.getBack());
	}
	
	@Test
	public void testgetFront()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		myDeque.addToFront("Joe");
		assertEquals("Joe", myDeque.getFront());
	}
	
	@Test
	public void testgetBack()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		myDeque.addToBack("Joe");
		assertEquals("Joe", myDeque.getBack());
	}
	
	@Test
	public void testremoveFront()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		myDeque.addToBack("Joe");
		myDeque.addToBack("Marry");
		assertEquals("Joe", myDeque.removeFront());
		assertEquals("Marry", myDeque.getFront());
	}
	
	@Test
	public void testremoveBack()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		myDeque.addToBack("Joe");
		myDeque.addToBack("Marry");
		assertEquals("Marry", myDeque.removeBack());
		assertEquals("Joe", myDeque.getFront());
	}
	
	@Test
	public void testisEmpty()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		assertEquals(true, myDeque.isEmpty());
		myDeque.addToBack("Joe");
		assertEquals(false, myDeque.isEmpty());
	}
	
	@Test
	public void testClear()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		assertEquals(true, myDeque.isEmpty());
		myDeque.addToBack("Joe");
		assertEquals(false, myDeque.isEmpty());
		myDeque.clear();
		assertEquals(true, myDeque.isEmpty());
	}
	
	@Test
	public void testtoString()
	{
		DequeInterface<String> myDeque = new LinkedDeque<>();
		myDeque.addToBack("Joe");
		myDeque.addToBack("Marry");
		myDeque.addToFront("Ketty");
		assertEquals("[Ketty, Joe, Marry]", myDeque.toString());
	}
}
