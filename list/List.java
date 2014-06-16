//package list;

public interface List<E> {
	public abstract E get(int index);
	
	public abstract E set(int index, E elmement);
	
	public abstract void add(int index,  E element);
	
	public abstract E remove(int index);
	
	public abstract int indexOf(Object o);
	
	public abstract void clear();
	
	public abstract boolean contains(Object o);
	
	public abstract int size();
}
