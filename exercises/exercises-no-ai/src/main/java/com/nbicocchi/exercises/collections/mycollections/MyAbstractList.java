package com.nbicocchi.exercises.collections.mycollections;

public abstract class MyAbstractList extends MyAbstractCollection implements MyList {
    static class MyListIterator implements MyIterator {
        MyList list;
        int position;

        public MyListIterator(MyList list) {
            this.list = list;
            this.position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < list.size();
        }

        @Override
        public Object next() {
            return list.get(position++);
        }
    }

    @Override
    public MyIterator iterator() {
        return new MyListIterator(this);
    }
}
