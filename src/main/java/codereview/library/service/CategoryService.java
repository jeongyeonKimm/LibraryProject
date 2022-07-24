package codereview.library.service;

import codereview.library.domain.BookInfo;
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
    @Transactional
    public Long register(Category category) {
        validateDuplicateCategory(category);
        categoryRepository.save(category);
        return category.getId();
    }

    /**
     * 아이디로 도서 분류 조회
     */
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
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
    @Transactional
    public void update(Long id, String name) {
        Category findCategory = categoryRepository.findOne(id);

        findCategory.changeName(name);
    }

    /**
     * 도서 분류 삭제
     */
    @Transactional
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    /**
     * 중복 도서 분류 검증
     */
    private void validateDuplicateCategory(Category category) {

        List<Category> findCategories = categoryRepository.findByName(category.getName());
        if (!findCategories.isEmpty()) {
            throw new IllegalStateException(("이미 존재하는 도서 분류 입니다."));
        }

    }
}
