package com.ostd.labs.lib.dao.convert;

import com.ostd.labs.lib.dao.service.GenericDAO;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pkiryukhin
 */
public class DtoConvertorTest {
    public static class ParentEntity {
        String name;
        List<ChildEntity> children = new ArrayList<>();
    }

    public static class ParentDto {
        String name;
        List<ChildDto> children = new ArrayList<>();
    }

    public static class ChildEntity {
        String name;
        ParentEntity parent;
    }

    public static class ChildDto {
        String name;
        ParentDto parent;
    }

    public static DtoConvertor<ParentEntity, ParentDto, String> parentConvertor = new DtoConvertor<ParentEntity, ParentDto, String>() {
        @Override
        public void toDto(ParentEntity entity, ParentDto dto) {
            dto.name = entity.name;
        }

        @Override
        public void fromDto(ParentEntity entity, ParentDto dto) {
            entity.name = dto.name;
        }

        @Override
        protected void toDtoDeep(ParentEntity entity, ParentDto dtoShallow, ToDtoContext context) {
            dtoShallow.children = context.convert(childConvertor, entity.children);
        }

        @Override
        protected String getPk(ParentDto dto) {
            return dto.name;
        }
    };

    public static DtoConvertor<ChildEntity, ChildDto, String> childConvertor = new DtoConvertor<ChildEntity, ChildDto, String>() {
        @Override
        public void toDto(ChildEntity entity, ChildDto dto) {
            dto.name = entity.name;
        }

        @Override
        public void fromDto(ChildEntity entity, ChildDto dto) {
            entity.name = dto.name;
        }

        @Override
        protected void toDtoDeep(ChildEntity entity, ChildDto dtoShallow, ToDtoContext context) {
            if (dtoShallow.parent == null) {
                dtoShallow.parent = context.convert(parentConvertor, entity.parent);
            }
        }

        @Override
        protected String getPk(ChildDto dto) {
            return dto.name;
        }
    };

    public static class ParentDao extends GenericDAO<ParentEntity, String> {
        @Override
        public EntityManager getEntityManager() {
            return null;
        }
    }

    @Test
    public void testToDto() throws Exception {
        ParentEntity parentEntity = new ParentEntity();
        ChildEntity childEntity = new ChildEntity();
        parentEntity.name = "p";
        childEntity.name = "c";
        parentEntity.children.add(childEntity);
        childEntity.parent = parentEntity;

        ParentDto parentDto = parentConvertor.toDto(parentEntity);
        Assert.assertEquals(parentEntity.name, parentDto.name);
        Assert.assertEquals(0, parentDto.children.size());
    }

    @Test
    public void testToDtoDeep() throws Exception {
        ParentEntity parentEntity = new ParentEntity();
        ChildEntity childEntity = new ChildEntity();
        parentEntity.name = "p";
        childEntity.name = "c";
        parentEntity.children.add(childEntity);
        childEntity.parent = parentEntity;

        ParentDto parentDto = parentConvertor.toDtoDeep(parentEntity);
        Assert.assertEquals(parentEntity.name, parentDto.name);
        Assert.assertEquals(parentEntity.children.size(), parentDto.children.size());

        ChildDto childDto = parentDto.children.get(0);
        Assert.assertEquals(childEntity.name, childDto.name);
        Assert.assertEquals(childEntity.parent.name, childDto.parent.name);
    }

    @Test
    public void testFromDto() throws Exception {
        ParentDto parentDto = new ParentDto();
        ChildDto childDto = new ChildDto();
        parentDto.name = "p";
        childDto.name = "c";
        parentDto.children.add(childDto);
        childDto.parent = parentDto;
//TODO implement
//        ParentEntity parentEntity = parentConvertor.fromDto(parentDto, new ParentDao());
//        Assert.assertEquals(parentEntity.name, parentDto.name);
//        Assert.assertEquals(0, parentEntity.children.size());
    }

}
