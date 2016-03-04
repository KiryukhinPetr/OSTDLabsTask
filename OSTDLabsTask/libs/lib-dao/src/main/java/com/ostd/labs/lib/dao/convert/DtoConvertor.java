package com.ostd.labs.lib.dao.convert;


import com.ostd.labs.lib.dao.interfaces.IDtoConverterDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pkiryukhin
 */
public abstract class DtoConvertor<EntityClass, DtoClass, PkClass extends Serializable> {

    private Class<EntityClass> entityClass;
    private Class<DtoClass> dtoClass;

    @SuppressWarnings("unchecked")
    public DtoConvertor() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<EntityClass>) genericSuperclass.getActualTypeArguments()[0];
        this.dtoClass = (Class<DtoClass>) genericSuperclass.getActualTypeArguments()[1];
    }

    protected DtoClass newDto() {
        try {
            return dtoClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected EntityClass newEntity() {
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    ///// Converting Entity to DTO

    protected abstract void toDto(EntityClass entity, DtoClass dto);
    public DtoClass toDto(EntityClass entity) {
        if (entity == null) {
            return null;
        }
        DtoClass dto = newDto();
        toDto(entity, dto);
        return dto;
    }

    public List<DtoClass> toDto(List<EntityClass> entities) {
        ArrayList<DtoClass> dtos = new ArrayList<>();
        for (EntityClass entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public DtoClass toDtoDeep(EntityClass entity) {
        return new ToDtoContext().convert(this, entity);
    }

    public List<DtoClass> toDtoDeep(List<EntityClass> entity) {
        return new ToDtoContext().convert(this, entity);
    }

    protected abstract void toDtoDeep(EntityClass entity, DtoClass dtoShallow, ToDtoContext context);

    protected static class ToDtoContext {
        private Map<Object, Object> cache = new HashMap<>();

        public <ChildEntityClass, ChildDtoClass, ChildPkClass extends Serializable> ChildDtoClass convert(
                DtoConvertor<ChildEntityClass, ChildDtoClass, ChildPkClass> convertor, ChildEntityClass entity) {
            if (entity == null) {
                return null;
            }
            ChildDtoClass dto = (ChildDtoClass)cache.get(entity);
            if (dto == null) {
                dto = convertor.toDto(entity);
                cache.put(entity, dto);
                convertor.toDtoDeep(entity, dto, this);
            }
            return dto;
        }

        public <ChildEntityClass, ChildDtoClass, ChildPkClass extends Serializable> List<ChildDtoClass> convert(
                DtoConvertor<ChildEntityClass, ChildDtoClass, ChildPkClass> convertor, List<ChildEntityClass> entities) {
            List<ChildDtoClass> dtos = new ArrayList<>();
            for (ChildEntityClass entity : entities) {
                dtos.add(convert(convertor, entity));
            }
            return dtos;
        }
    }

    ///// Converting and persisting DTO to Entity

    protected abstract void fromDto(EntityClass entity, DtoClass dto);
    protected abstract PkClass getPk(DtoClass dto);

    public EntityClass fromDto(IDtoConverterDao genericDao, DtoClass dto) {
        EntityClass entity;
        PkClass pk = getPk(dto);
        if (pk == null) {
            entity = newEntity();
        } else {
            entity = genericDao.findById(entityClass, pk);
        }
        if (entity == null) {
            entity = newEntity();
        }
        fromDto(entity, dto);
        return entity;
    }

    public EntityClass fromDtoReference(IDtoConverterDao genericDao, DtoClass dto) {
        EntityClass entity;
        PkClass pk = getPk(dto);
        if (pk == null) {
            throw new NullPointerException("DTO without id :" + dto);
        } else {
            entity = genericDao.findById(entityClass, pk);
        }
        return entity;
    }

    public EntityClass fromDtoDeep(IDtoConverterDao genericDao, DtoClass dto) {
        EntityClass entity = fromDto(genericDao, dto);
        fromDtoDeep(genericDao, entity, dto);
        genericDao.persist(entity);
        return entity;
    }

    protected void fromDtoDeep(IDtoConverterDao genericDao, EntityClass entity, DtoClass dto) {
        //Nothing by default
    }

    protected void unlinkEntity(IDtoConverterDao genericDao, EntityClass entity) {
        //Nothing by default
    }

    public void fromDtoDeep(IDtoConverterDao genericDao, List<EntityClass> entities, List<DtoClass> dtos) {
        List<EntityClass> entitiesToRemove = new ArrayList<>(entities);
        if (dtos != null) {
            for (DtoClass dto : dtos) {
                EntityClass entity = fromDtoDeep(genericDao, dto);
                if (!entities.contains(entity)) {
                    entities.add(entity);
                }
                entitiesToRemove.remove(entity);
                genericDao.persist(entity);
            }
        }
        for (EntityClass entityToRemove : entitiesToRemove) {
            unlinkEntity(genericDao, entityToRemove);
            if (entities.contains(entityToRemove)) {
                entities.remove(entityToRemove);
            }
            genericDao.removeEntity(entityToRemove);
        }
    }
}
