package codereview.library.service;

import codereview.library.domain.Category;
import codereview.library.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void 도서분류등록() throws Exception {

        //given
        Category category = new Category("novel");

        //when
        Long categoryId = categoryService.register(category);

        //then
        assertEquals(category, categoryRepository.findOne(categoryId));
        
    }
    
    @Test
    public void 도서분류조회() throws Exception {
        
        //given
        Category category1 = new Category("novel");
        Long categoryId1 = categoryService.register(category1);
        
        Category category2 = new Category("dictionary");
        Long categoryId2 = categoryService.register(category2);
        
        //when
        List<Category> categories = categoryService.findAllCategory();

        //then
        for (Category category : categories) {
            System.out.println("category.getName() = " + category.getName());
        }
    }

    @Test
    public void 도서분류수정() throws Exception {

        //given
        Category category = new Category("novel");
        Long categoryId = categoryService.register(category);


        //when
        Category updatedCategory = categoryService.update(categoryId, "dictionary");

        //then
        assertEquals(updatedCategory, categoryRepository.findOne(categoryId));
    }

    @Test
    public void 도서분류삭제() throws Exception {

        //given
        Category category1 = new Category("novel");
        Long categoryId1 = categoryService.register(category1);

        Category category2 = new Category("dictionary");
        Long categoryId2 = categoryService.register(category2);

        //when
        categoryService.deleteCategory(category1);

        //then
        List<Category> categories = categoryService.findAllCategory();

        for (Category category : categories) {
            System.out.println("category.id = " + category.getId() + ", category.name = " + category.getName());
        }
    }
}
