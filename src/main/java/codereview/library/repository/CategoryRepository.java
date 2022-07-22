package codereview.library.repository;

import codereview.library.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    public void delete(Category category) {
        em.remove(category);
    }

}
