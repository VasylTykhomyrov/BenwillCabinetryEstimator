package com.benwillcabinets.benwillestimator.web;

import com.benwillcabinets.benwillestimator.domain.Product;
import com.benwillcabinets.benwillestimator.domain.ProjectEstimate;
import com.benwillcabinets.benwillestimator.domain.ProjectItem;
import com.benwillcabinets.benwillestimator.service.ProductService;
import com.benwillcabinets.benwillestimator.service.ProjectEstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
public class ProjectEstimatesController {
    @Autowired
    private ProjectEstimateService projectEstimateService;
    @Autowired
    private ProductService productService;

    @PostMapping("/projects")
    ProjectEstimate addProject(@RequestBody ProjectEstimate project) {
        return projectEstimateService.save(project);
    }

    @PostMapping("/projects/{id}/items")
    ProjectEstimate addProductItem(@PathVariable("id") int projectId, @RequestBody Product product) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        Product productFound = productService.findById(product.getId()).get();
        ProjectItem item = new ProjectItem();
        item.setProduct(productFound);
        project.getListOfProducts().add(item);
        projectEstimateService.save(project);
        return project;
    }

    @DeleteMapping("/projects/{id}/items/{itemId}")
    ProjectEstimate removeProductItem(@PathVariable("id") int projectId, @PathVariable("itemId") int itemId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        ProjectItem itemToDelete = null;
        for(int i=0; i < project.getListOfProducts().size(); i++){
            if(project.getListOfProducts().get(i).getId().equals(itemId)) {
                itemToDelete = project.getListOfProducts().get(i);
            }
        }
        project.getListOfProducts().remove(itemToDelete);
        projectEstimateService.save(project);
        return project;
    }

    @GetMapping("/projects/{id}/items")
    List<ProjectItem> addProductItem(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        return project.getListOfProducts();
    }
}
