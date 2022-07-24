package codereview.library.controller;

import codereview.library.domain.Address;
import codereview.library.domain.Category;
import codereview.library.domain.Member;
import codereview.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category/new")
    public String createForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "categories/createCategoryForm";
    }

    @PostMapping("/category/new")
    public String create(@Valid CategoryForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "categories/createCategoryForm";
        }

        Category category = new Category(form.getName());

        categoryService.register(category);

        return "redirect:/";
    }

    @GetMapping("/category")
    public String list(Model model) {
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);
        return "categories/categoryList";
    }

    @GetMapping("/category/{categoryId}/update")
    public String updateMemberForm(@PathVariable("categoryId") Long categoryId, Model model) {

        Category category = categoryService.findOne(categoryId);

        MemberForm form = new MemberForm();
        form.setPhone(category.getName());

        model.addAttribute("form", form);
        return "categories/updateCategoryForm";
    }

    @PostMapping("/category/{categoryId}/update")
    public String updateMember(@PathVariable("categoryId") Long categoryId, @ModelAttribute("form") CategoryForm form) {

        categoryService.update(categoryId, form.getName());
        return "redirect:/category";
    }

    @GetMapping("/category/{categoryId}/delete")
    public String delete(@PathVariable("categoryId") Long categoryId) {

        Category findCategory = categoryService.findOne(categoryId);

        categoryService.deleteCategory(findCategory);

        return "redirect:/category";
    }
}
