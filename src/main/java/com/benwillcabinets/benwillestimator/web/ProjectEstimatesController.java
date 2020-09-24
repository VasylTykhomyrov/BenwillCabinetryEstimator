package com.benwillcabinets.benwillestimator.web;

import com.benwillcabinets.benwillestimator.domain.Product;
import com.benwillcabinets.benwillestimator.domain.ProjectEstimate;
import com.benwillcabinets.benwillestimator.domain.ProjectItem;
import com.benwillcabinets.benwillestimator.refacing.RefacingInfo;
import com.benwillcabinets.benwillestimator.refacing.RefacingItem;
import com.benwillcabinets.benwillestimator.service.ProductService;
import com.benwillcabinets.benwillestimator.service.ProjectEstimateService;
import com.benwillcabinets.benwillestimator.service.RefacingItemService;
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
    @Autowired
    private RefacingItemService refacingItemService;

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

    @PostMapping("/projects/{id}/refacing-info")
    ProjectEstimate saveRefacingInfo(@PathVariable("id") int projectId, @RequestBody RefacingInfo info) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        RefacingInfo currentInfo = project.getRefacingInfo();
        if(currentInfo == null) {
            currentInfo = info;
        } else {
            currentInfo.setColour(info.getColour());
            currentInfo.setHandles(info.getHandles());
            currentInfo.setStyle(info.getStyle());
        }
        project.setRefacingInfo(currentInfo);
        projectEstimateService.save(project);
        return project;
    }

    @PostMapping("/projects/{id}/refacing-items")
    ProjectEstimate addProjectItem(@PathVariable("id") int projectId, @RequestBody RefacingItem item) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        item.setCostPSF(project.getRefacingInfo().getSFPrice());
        refacingItemService.save(item);
        project.getListOfRefacingItems().add(item);
        projectEstimateService.save(project);
        return project;
    }

    @DeleteMapping("/projects/{id}/refacing-items/{itemId}")
    ProjectEstimate removeRefacingItem(@PathVariable("id") int projectId, @PathVariable("itemId") int itemId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        RefacingItem itemToDelete = findRefacingItem(itemId, project);
        project.getListOfRefacingItems().remove(itemToDelete);
        projectEstimateService.save(project);
        return project;
    }

    private void calculateCostPSF(RefacingItem item) {
        // calculate based on colour and style
        item.setCostPSF(23);
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

    private RefacingItem findRefacingItem(int itemId, ProjectEstimate project) {
        RefacingItem itemToDelete = null;
        for(int i=0; i < project.getListOfRefacingItems().size(); i++){
            if(project.getListOfRefacingItems().get(i).getId().equals(itemId)) {
                itemToDelete = project.getListOfRefacingItems().get(i);
            }
        }
        return itemToDelete;
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
    List<ProjectItem> getProductItems(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        return project.getListOfProducts();
    }

    @GetMapping("/projects/{id}/refacing-items")
    List<RefacingItem> getRefacingItems(@PathVariable("id") int projectId) {
        ProjectEstimate project =  projectEstimateService.findById(projectId).get();
        return project.getListOfRefacingItems();
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
