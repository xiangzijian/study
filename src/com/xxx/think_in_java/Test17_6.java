package com.xxx.think_in_java;

import java.util.*;

public class Test17_6 {
    public static class MySortedSet<E> implements SortedSet<E>{
        private List<E> arrayList;
        private Comparator<? super E> comparator;
        public MySortedSet(ArrayList arrayList) {
            this.arrayList=arrayList;

        }

        @Override
        public Spliterator spliterator() {
            return arrayList.spliterator();
        }

        @Override
        public Comparator <? super E> comparator() {
            return comparator;
        }

        @Override
        public SortedSet<E> subSet(E fromElement, E toElement) {
            MySortedSet<E> resultSet=new MySortedSet<E>(new ArrayList());
            boolean sub=false;
            for (E e:arrayList) {
                if(toElement.equals(e)){
                    break;
                }
                if(sub){
                    resultSet.add(e);
                    continue;
                }
                if(fromElement.equals(fromElement)){
                    resultSet.add(e);
                    sub=true;
                }
            }
            return resultSet;
        }

        @Override
        public SortedSet headSet(Object toElement) {
            return null;
        }

        @Override
        public SortedSet tailSet(Object fromElement) {
            return null;
        }

        @Override
        public E first() {
            if(arrayList.size()>0){
                return arrayList.get(0);
            }
            return null;
        }

        @Override
        public E last() {
            if(arrayList.size()>0){
                return arrayList.get(arrayList.size()-1);
            }
            return null;
        }

        @Override
        public int size() {
            return arrayList.size();
        }

        @Override
        public boolean isEmpty() {
            return arrayList.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return arrayList.contains(o);
        }

        @Override
        public Iterator iterator() {
            return arrayList.iterator();
        }

        @Override
        public Object[] toArray() {
            return arrayList.toArray();
        }

        @Override
        public Object[] toArray(Object[] a) {
            return arrayList.toArray(a);
        }

        @Override
        public boolean add(E o) {

            return arrayList.add(o);
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(Collection c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }
    }
}
