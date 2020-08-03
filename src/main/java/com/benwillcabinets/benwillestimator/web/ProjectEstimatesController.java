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
    ProjectEstimate addProjectItem(@PathVariable("id") int projectId, @RequestBody Product product) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        Product productFound = productService.findById(product.getId()).get();
        ProjectItem item = new ProjectItem();
        item.setProduct(productFound);
        item.setSellProjectPrice(productFound.getSellPrice());
        item.setCostProjectPrice(productFound.getCostPrice());
        item.setQty(1);
        project.getListOfProducts().add(item);
        projectEstimateService.save(project);
        return project;
    }

    @PutMapping("/projects/{id}/items/{itemId}")
    ProjectEstimate updateProductItem(@PathVariable("id") int projectId, @PathVariable("itemId") int itemId, @RequestBody ProjectItem projectItem) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        ProjectItem itemToUpdate = findProjectItem(itemId, project);
        itemToUpdate.setCostProjectPrice(projectItem.getCostProjectPrice());
        itemToUpdate.setSellProjectPrice(projectItem.getSellProjectPrice());
        itemToUpdate.setQty(projectItem.getQty());
        projectEstimateService.save(project);
        return project;
    }

    @DeleteMapping("/projects/{id}/items/{itemId}")
    ProjectEstimate removeProductItem(@PathVariable("id") int projectId, @PathVariable("itemId") int itemId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        ProjectItem itemToDelete = findProjectItem(itemId, project);
        project.getListOfProducts().remove(itemToDelete);
        projectEstimateService.save(project);
        return project;
    }

    private ProjectItem findProjectItem(@PathVariable("itemId") int itemId, ProjectEstimate project) {
        ProjectItem itemToDelete = null;
        for(int i=0; i < project.getListOfProducts().size(); i++){
            if(project.getListOfProducts().get(i).getId().equals(itemId)) {
                itemToDelete = project.getListOfProducts().get(i);
            }
        }
        return itemToDelete;
    }

    @GetMapping("/projects/{id}/items")
    List<ProjectItem> addProductItem(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        return project.getListOfProducts();
    }

    @GetMapping("/projects/{id}")
    ProjectEstimate getProject(@PathVariable("id") int projectId) {
        return projectEstimateService.findById(projectId).get();
    }

    @GetMapping("/projects")
    List<ProjectEstimate> getAllProject() {
        return projectEstimateService.findAll();
    }
}
