package org.example.dao;

public interface IDao<T,ID> {
   T trouverParId(ID id);
}
