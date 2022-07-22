package codereview.library.service;

import codereview.library.domain.Category;
import codereview.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 도서 분류 등록
     */
    public void register(Category category) {
        categoryRepository.save(category);
    }

    /**
     * 전체 도서 분류 조회
     */
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    /**
     * 도서 분류 수정
     */
    public void update(Long id, String name) {
        Category findCategory = categoryRepository.findOne(id);

        findCategory.changeName(name);
    }

    /**
     * 도서 분류 삭제
     */
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

}
